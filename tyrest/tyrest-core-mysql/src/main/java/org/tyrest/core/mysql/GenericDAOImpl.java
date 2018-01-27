package org.tyrest.core.mysql;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

/**
 * <pre>
 * 
 *  freeapis
 *  File: GenericDAOImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:通用的数据访问层封装
 *  TODO
 * 
 *  Notes:
 *  $Id: GenericDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年7月18日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value = "genericDAO")
public class GenericDAOImpl<T> implements GenericDAO<T>{

	Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	/** 每次批量操作数 */
	private int batchSize = 30;

	private Class<T> entityClass = null;

	/** 设置每次操作数 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/** 获得entity manager **/
	public EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return entityClass;
	}

	@Override
	public void insert(T entity) throws Exception {
		try {
			em.persist(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void insertList(List<T> list) throws Exception {
		try {
			EntityManager entityManager = em;
			if (list == null || list.size() == 0) {
				return;
			}
			int i = 0;
			for (T o : list) {
				this.insert(o);
				if (i % batchSize == 0) {
					entityManager.flush();
				}
				i++;
			}
			logger.debug(list.get(0).getClass() + "批量增加数据" + i + "条");

		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Long id) throws Exception {
		try {
			em.remove(em.getReference(this.getEntityClass(), id));
		} catch (Exception e) {
			if (e instanceof javax.persistence.EntityNotFoundException) {
				// if the delete Object not found . do nothing
			} else {
				throw new Exception(e.getMessage(), e);
			}
		}
	}

	@Override
	public String deleteCheck(ReferenceModel... models) throws Exception {
		try {
			for (ReferenceModel rm : models) {
				StringBuilder sql = new StringBuilder(" SELECT * FROM ").append(rm.getTable()).append(" WHERE 1=1 ");
				Map<String, Object> params = new HashMap<String, Object>();
				String[] fkColumns = rm.getFkColumns();
				String[] fkValues = rm.getFkValues();

				for(int i = 0; i < fkColumns.length; i++){
					sql.append(" AND ").append(fkColumns[i]).append(" = :").append(fkColumns[i]);
					params.put(fkColumns[i], fkValues[i]);
				}
				Long checkResult = this.findCount(sql.toString(), params);
				if (checkResult != 0)
					return ("在【" + rm.getDescription() + "】中有【" + checkResult + "】条数据引用了当前数据，因此不能进行操作！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("检查是否可操作时出错，请检查参数传递是否正确！", e);
		}
		return null;
	}

	@Override
	public void update(T entity) throws Exception {
		try {
			em.merge(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void updateList(List<T> list) throws Exception {
		try {
			for (T entity : list) {
				this.update(entity);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public int update(String sql, Map<String, Object> params) throws Exception {
		try {
			Query query = em.createNativeQuery(sql);
			fillStatement(query, params);
			return query.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	private void fillStatement(Query query, Map<String, Object> params) throws Exception {
		if (!ValidationUtil.isEmpty(params)) {
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = (Entry<String, Object>) it.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}

	@Override
	public T findById(Long id) throws Exception {
		try {
			return em.find(this.getEntityClass(), id);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findFirst(String sql, Map<String, Object> params) throws Exception {
		try {
			Query query = em.createNativeQuery(enhanceSql(sql) + " LIMIT 1 ", this.getEntityClass());
			this.fillStatement(query, params);
			List<T> list = query.getResultList();
			if (!ValidationUtil.isEmpty(list)) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> find(String sql, Map<String, Object> params, String orderBy, String order) throws Exception {
		try {
			Query query = createOrderQuery(enhanceSql(sql), params, orderBy, order);
			List list = query.getResultList();
			if (ValidationUtil.isEmpty(list)) {
				list = new ArrayList<T>();
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> paginate(String sql, Map<String, Object> params, Page page, String orderBy, String order)
			throws Exception {
		try {
			Query query = createOrderQuery(enhanceSql(sql), params, orderBy, order);
			query.setFirstResult(page.getPageStartRow());
			query.setMaxResults(page.getPageRecorders());
			page.setTotalRows(this.findCount(enhanceSql(sql), params).intValue());
			List list = query.getResultList();
			if (ValidationUtil.isEmpty(list)) {
				list = new ArrayList<T>();
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public Long findCount(String sql, Map<String, Object> params) throws Exception {
		try {
			String countsql = enhanceSql(sql);
			if(!countsql.matches("^\\s*[sS][eE][lL][eE][cC][tT]\\s+[cC][oO][uU][nN][tT]\\(.+\\)\\s+.+")){
				countsql = "SELECT COUNT(1) FROM ( " + countsql + " ) COUNTTEMP";
			}
			Query query = em.createNativeQuery(countsql);
			this.fillStatement(query, params);
			return ((BigInteger) query.getResultList().get(0)).longValue();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findMaps(String sql, Map<String, Object> params) throws Exception {
		try {
			Query query = em.createNativeQuery(sql);
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			this.fillStatement(query, params);
			return query.getResultList();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> findMapsByPage(String sql, Map<String, Object> params, Page page)
			throws Exception {
		try {
			Query query = em.createNativeQuery(sql);
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			this.fillStatement(query, params);
			query.setFirstResult(page.getPageStartRow());
			query.setMaxResults(page.getPageRecorders());
			page.setTotalRows(this.findCount(sql, params).intValue());
			List list = query.getResultList();
			if (ValidationUtil.isEmpty(list)) {
				list = new ArrayList<Map<String, Object>>();
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public <A> A findObject(String sql, Map<String, Object> params) throws Exception {
		try {
			Query query = em.createNativeQuery(sql);
			this.fillStatement(query, params);
			List<A> list = (List<A>) query.getResultList();
			return ValidationUtil.isEmpty(list) ? null : list.get(0);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> List<A> findObjects(String sql, Map<String, Object> params) throws Exception {
		try {
			Query query = em.createNativeQuery(sql);
			this.fillStatement(query, params);
			return (List<A>) query.getResultList();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * TODO.创建带排序规则的查询对象
	 *
	 * @param
	 * @param params
	 * @param sql
	 * @param orderBy
	 * @param order
	 * @throws Exception
	 */
	private Query createOrderQuery(String sql, Map<String, Object> params, String orderBy, String order)
			throws Exception {
		StringBuilder internalSql = new StringBuilder(sql);
		if (!ValidationUtil.isEmpty(orderBy) && !StringUtils.containsIgnoreCase(sql, "ORDER BY")) {
			internalSql.append(" ORDER BY ").append(ImprovedNamingStrategy.INSTANCE.propertyToColumnName(orderBy)).append(" ")
					.append(QueryOrder.contains(order) ? order : QueryOrder.desc.name());
		}
		Query query = em.createNativeQuery(internalSql.toString(), this.getEntityClass());
		this.fillStatement(query, params);
		return query;
	}

	private enum QueryOrder {
		desc, asc;

		public static Boolean contains(String order) {
			boolean result = false;
			for (QueryOrder queryOrder : QueryOrder.values()) {
				if (queryOrder.name().equalsIgnoreCase(order)) {
					result = true;
					break;
				}
			}
			return result;
		}
	}
	/**
	 * TODO.对没有前缀的sql进行加强处理,如果传入sql没有select声明,则在此自动添加
	 * @param sqlSource
	 * @return
	 */
	private String enhanceSql(String sqlSource){
		//如果sql语句有select前缀,则直接返回
		if(sqlSource.matches("^\\s*[sS][eE][lL][eE][cC][tT]\\s+.+")){
			return sqlSource;
		}
		StringBuilder result = new StringBuilder(" SELECT * FROM ")
				.append(this.getEntityClass().getAnnotation(Table.class).name().toUpperCase())
				.append(" Z WHERE 1=1 ")
				.append(sqlSource);
		return result.toString();
	}

	protected String tableName(){
		return this.getEntityClass().getAnnotation(Table.class).name().toUpperCase();
	}

	protected String tableName(Class<?> entityClass){
		return entityClass.getAnnotation(Table.class).name().toUpperCase();
	}


	public static void main(String[] args) {
		System.out.println(" selEct \n coUnt(s.reu) from".matches("^\\s*[sS][eE][lL][eE][cC][tT]\\s+[cC][oO][uU][nN][tT]\\(.+\\)\\s+.+"));
	}
}

/*
 * $Log: av-env.bat,v $
 */