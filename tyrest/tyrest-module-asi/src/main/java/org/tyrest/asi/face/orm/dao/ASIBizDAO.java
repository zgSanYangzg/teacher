package org.tyrest.asi.face.orm.dao;

import org.tyrest.asi.face.orm.entity.ASIBiz;
import org.tyrest.core.mysql.GenericDAO;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ASIBizDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIBizDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ASIBizDAO extends GenericDAO<ASIBiz> {
	
	void insertWithCache(ASIBiz asiBiz) throws Exception;

	void deleteWithCache(String agencyCode, String entityType) throws Exception;

	void updateWithCache(ASIBiz asiBiz) throws Exception;

	ASIBiz findASIBiz(String agencyCode, String entityType) throws Exception;

}
