package org.tyrest.asi.face.orm.dao;

import java.util.List;

import org.tyrest.asi.face.orm.entity.ColumnTemplate;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ColumnTemplateDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ColumnTemplateDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ColumnTemplateDAO extends GenericDAO<ColumnTemplate> {
	
	void insertWithCache(ColumnTemplate columnTemplate) throws Exception;

	void deleteWithCache(String agencyCode, String columnCode) throws Exception;

	void updateWithCache(ColumnTemplate columnTemplate) throws Exception;

	ColumnTemplate getWithCache(String agencyCode, String columnCode) throws Exception;

	List<ColumnTemplate> findColumnTemplateByPage(String agencyCode, String columnCode, String columnName,
			Page page,String orderBy,String order) throws Exception;
}
