package org.tyrest.security.dao;

import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.ModuleOperationDAO;
import org.tyrest.security.face.orm.entity.ModuleOperation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityModuleResourceDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityModuleResourceDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value="moduleOperationDAO")
public class ModuleOperationDAOImpl extends GenericDAOImpl<ModuleOperation> implements ModuleOperationDAO
{
	@Override
	public List<ModuleOperation> findByModuleCode(String moduleCode) throws Exception {
		String sqlSufix = " AND MODULE_CODE = :MODULE_CODE ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MODULE_CODE", moduleCode);
		return this.find(sqlSufix, params, null, null);
	}

}
