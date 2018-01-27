package org.tyrest.snapshot.face.orm.dao;


import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.BaseSnapshot;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnapshotDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface SnapshotDAO {

	/**
	 * TODO.
	 * @param sourceEntity	原实体
	 * @param snptshotEntityClass	快照实体类型
	 * @throws Exception
	 */
	public <T extends BaseEntity> void snapshot(T mainObject,Class<? extends BaseSnapshot> snapshotEntityClass) throws Exception ;

	/**
	 * 根据主对象的主键获得其最新快照对象的主键
	 * @param mainTypeSequenceNbr
	 * @return
	 * @throws Exception
	 */
	public <T> Long getLastSnptSequenceNbr(Long mainTypeSequenceNbr,Class<T> snapshotEntityClass) throws Exception;

	
	/**
	 * 根据主对象的主键获得其最新快照对象
	 * @param mainTypeSequenceNbr
	 * @return
	 * @throws Exception
	 */
	public <T> T getLastSnptEntity(Long mainTypeSequenceNbr, Class<T> snapshotEntityClass) throws Exception;
	
	/**
	 * 根据快照主键获取快照
	 * @param snptSequenceNbr
	 * @param clzz
	 * @return
	 * @throws Exception
	 */
	public <T> T getSnptEntity(Long snptSequenceNbr, Class<T> snapshotEntityClass) throws Exception;
	
}

/*
 * $Log: av-env.bat,v $
 */