package org.tyrest.security.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.ModuleOperation;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityModuleResourceDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityModuleResourceDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public interface ModuleOperationDAO extends GenericDAO<ModuleOperation>
{

	/**
	 * 根据模块code查询对应的接口资源信息
	 * @param moduleCode
	 * @return
	 * @throws Exception
	 */
	List<ModuleOperation> findByModuleCode(String moduleCode) throws Exception;
	
}
