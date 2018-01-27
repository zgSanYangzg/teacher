package org.tyrest.snapshot.face.orm.dao;

import java.util.List;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.snapshot.face.orm.entity.SnapshotGroup;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotGroupDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnapshotGroupDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface SnapshotGroupDAO extends GenericDAO<SnapshotGroup> {

	
	public List<SnapshotGroup> queryForList(String groupCode,String businessCode,String snapshotType) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */