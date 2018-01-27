package org.tyrest.asi.face.orm.dao;

import java.util.List;

import org.tyrest.asi.face.orm.entity.Group;
import org.tyrest.core.mysql.GenericDAO;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: GroupDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface GroupDAO extends GenericDAO<Group> {
	
	void insertWithCache(Group group) throws Exception;

	void deleteWithCache(String agencyCode, String groupCode) throws Exception;

	void updateWithCache(Group group) throws Exception;

	Group findGroup(String agencyCode, String groupCode) throws Exception;

	List<Group> findGroupByParentCode(String agencyCode,String parentCode) throws Exception;
	
	boolean hasChildrenGroup(String agencyCode,String parentCode) throws Exception;
}
