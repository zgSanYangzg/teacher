package org.tyrest.asi.face.orm.dao;

import java.util.List;
import java.util.Map;

import org.tyrest.asi.face.orm.entity.GroupColumn;
import org.tyrest.core.mysql.GenericDAO;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: GroupColumnDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupColumnDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface GroupColumnDAO extends GenericDAO<GroupColumn> {
	
	public Map<String, GroupColumn> findGroupColumnsMap(String agencyCode, String groupCode)
			throws Exception;

	void insertWithCache(GroupColumn groupColumn) throws Exception;

	void deleteWithCache(String agencyCode, String groupCode,String columnCode) throws Exception;

	void updateWithCache(GroupColumn groupColumn) throws Exception;

	GroupColumn findGroupColumn(String agencyCode, String groupCode, String columnCode)
			throws Exception;

	List<GroupColumn> findGroupColumns(String agencyCode, String groupCode) throws Exception;
}
