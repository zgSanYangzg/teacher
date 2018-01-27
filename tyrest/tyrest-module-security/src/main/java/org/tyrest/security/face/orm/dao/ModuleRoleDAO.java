package org.tyrest.security.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.ModuleRole;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityModuleRoleDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityModuleRoleDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public interface ModuleRoleDAO extends GenericDAO<ModuleRole>
{

	/**
	 * 通过角色编号查询角色模块关系
	 * @param roleCode
	 * @param agencyCode
	 * @return
	 * @throws Exception 
	 */
	List<ModuleRole> findByRoleCode(String roleCode, String agencyCode) throws Exception;

	/**
	 * 删除所有的角色模块关系
	 * @param agencyCode
	 * @param roleCode
	 * @throws Exception 
	 */
	int deleteByRoleCode(String agencyCode, String roleCode) throws Exception;
	
}
