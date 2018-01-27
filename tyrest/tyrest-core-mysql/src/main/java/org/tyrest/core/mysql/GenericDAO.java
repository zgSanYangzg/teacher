package org.tyrest.core.mysql;

import org.tyrest.core.foundation.model.Page;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 
 *  freeapis
 *  File: GenericDAO.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 * 
 *  Notes:
 *  $Id: GenericDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis\ligang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年7月15日		ligang		Initial.
 *
 * </pre>
 */
public interface GenericDAO<T> {
	/**
	 * TODO.获取EntityManager
	 * @return
	 */
	EntityManager getEntityManager();
	/**
	 * TODO.插入一条记录
	 * @param entity
	 * @throws Exception
	 */
	void insert(T entity) throws Exception;
	/**
	 * TODO.批量插入
	 * @param list
	 * @throws Exception
	 */
	void insertList(List<T> list) throws Exception;
	/**
	 * TODO.根据主键删除记录
	 * @param id
	 * @throws Exception
	 */
	void delete(Long id) throws Exception;
	/**
	 * TODO.删除关联检查
	 * @param models
	 * @return
	 * @throws Exception
	 */
	String deleteCheck(ReferenceModel... models) throws Exception;
	/**
	 * TODO.更新一条记录
	 * @param entity
	 * @throws Exception
	 */
	void update(T entity) throws Exception;
	/**
	 * TODO.批量更新
	 * @param list
	 * @throws Exception
	 */
	void updateList(List<T> list) throws Exception;
	/**
	 * TODO.sql更新
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	int update(String sql, Map<String, Object> params) throws Exception;
	/**
	 * TODO.根据主键查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findById(Long id) throws Exception;
	/**
	 * TODO.查找一条记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	T findFirst(String sql, Map<String, Object> params) throws Exception;
	/**
	 * TODO.查询列表,不分页
	 * @param sql
	 * @param params
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws Exception
	 */
	List<T> find(String sql, Map<String, Object> params, String orderBy, String order) throws Exception;

	/**
	 * 查询单个值，用于聚合或者函数统计
	 * @param sql
	 * @param params
	 * @param <A>
	 * @return
	 * @throws Exception
	 */
	<A> A findObject(String sql, Map<String, Object> params) throws Exception;
	/**
	 * TODO.查询数据表的某一列
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	<A> List<A> findObjects(String sql, Map<String, Object> params) throws Exception;
	/**
	 * TODO.分页查询记录
	 * @param sql
	 * @param params
	 * @param page
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws Exception
	 */
	List<T> paginate(String sql, Map<String, Object> params, Page page, String orderBy, String order)
			throws Exception;
	/**
	 * TODO.查询记录数
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Long findCount(String sql, Map<String, Object> params) throws Exception;
	/**
	 * TODO.自定义查询,一般用于报表
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> findMaps(String sql, Map<String, Object> params) throws Exception;
	
	/**
	 * TODO.自定义查询,一般用于报表,分页形式
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> findMapsByPage(String sql, Map<String, Object> params, Page page) throws Exception;

}
