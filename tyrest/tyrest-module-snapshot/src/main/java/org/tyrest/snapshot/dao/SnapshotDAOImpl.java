package org.tyrest.snapshot.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;

import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.BaseSnapshot;
import org.tyrest.snapshot.face.orm.dao.SnapshotDAO;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  快照<实体对象，实体快照对象>
 * 
 *  Notes:
 *  $Id: SnapshotDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */

@Repository(value = "snapshotDAO")
public class SnapshotDAOImpl implements SnapshotDAO {

	Logger logger = LoggerFactory.getLogger(SnapshotDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	SequenceGenerator sequenceGenerator;

	public <T extends BaseEntity> void snapshot(T mainObject,Class<? extends BaseSnapshot> snapshotEntityClass) throws Exception {
		BaseSnapshot snapshotBean = Bean.copyExistPropertis(mainObject, snapshotEntityClass.newInstance());
		snapshotBean.setMasterRecDate(mainObject.getRecDate());
		snapshotBean.setMasterSequenceNbr(mainObject.getSequenceNBR());
		Bean.setPropertyValue("recDate", new Date(), snapshotBean);
		snapshotBean.setSequenceNBR(sequenceGenerator.getNextValue());
		try {
			em.persist(snapshotBean);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		// 以主类 主键为key 的缓存
		Redis.setSingle(snapshotBean, snapshotBean.getMasterSequenceNbr().toString());
	}

	/**
	 * 根据主对象的主键获得其最新快照对象的主键
	 * 
	 * @param mainTypeSequenceNbr
	 * @return
	 * @throws Exception
	 */
	public <T> Long getLastSnptSequenceNbr(Long mainTypeSequenceNbr, Class<T> snapshotEntityClass) throws Exception {
		Long snptSequenceNBR = null;
		T newSnapshot = this.getLastSnptEntity(mainTypeSequenceNbr, snapshotEntityClass);
		if (!ValidationUtil.isEmpty(newSnapshot) && BaseSnapshot.class.isAssignableFrom(snapshotEntityClass)) {
			snptSequenceNBR = ((BaseSnapshot) newSnapshot).getSequenceNBR();
		}
		return snptSequenceNBR;
	}

	/**
	 * 根据主对象的主键获得其最新快照对象
	 * 
	 * @param mainTypeSequenceNbr
	 * @return
	 * @throws Exception
	 */
	public <T> T getLastSnptEntity(Long mainTypeSequenceNbr, Class<T> snapshotEntityClass) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT * FROM ");
		sql.append(snapshotEntityClass.getAnnotation(Table.class).name());
		sql.append(" WHERE MASTER_SEQUENCE_NBR =:MASTER_SEQUENCE_NBR ORDER BY SEQUENCE_NBR DESC LIMIT 1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MASTER_SEQUENCE_NBR", mainTypeSequenceNbr);
		return this.findFirstSnapshot(sql.toString(), params, snapshotEntityClass);
	}

	@Override
	public <T> T getSnptEntity(Long snptSequenceNbr, Class<T> snapshotEntityClass) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT * FROM ");
		sql.append(snapshotEntityClass.getAnnotation(Table.class).name());
		sql.append(" WHERE SEQUENCE_NBR =:SEQUENCE_NBR ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SEQUENCE_NBR", snptSequenceNbr);
		return this.findFirstSnapshot(sql.toString(), params, snapshotEntityClass);
	}
	
	// 获取快照查询结果第一条记录（最新一条记录）
	@SuppressWarnings("unchecked")
	private <S> S findFirstSnapshot(String sql, Map<String, Object> params, Class<S> snapshotEntityClass) throws Exception {
		try {
			Query query = em.createNativeQuery(sql, snapshotEntityClass);
			
			if (!ValidationUtil.isEmpty(params)) {
				Iterator<Entry<String, Object>> it = params.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) it.next();
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			List<S> list = query.getResultList();
			if (!ValidationUtil.isEmpty(list)) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */