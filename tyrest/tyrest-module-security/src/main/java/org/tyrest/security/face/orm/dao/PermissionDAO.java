package org.tyrest.security.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.Permission;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityRolePrivilegeDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityRolePrivilegeDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public interface PermissionDAO extends GenericDAO<Permission>
{

	/**
	 * 查询角色对应的模块权限信息
	 * @param agencyCode
	 * @param roleCode
	 * @param moduleCode
	 * @return
	 * @throws Exception 
	 */
	List<Permission> findByModuleCode(String agencyCode, String roleCode, String moduleCode) throws Exception;

	/**
	 * 删除对应的角色权限信息
	 * @param agencyCode
	 * @param roleCode
	 * @return
	 * @throws Exception 
	 */
	int deleteByRoleCode(String agencyCode, String roleCode) throws Exception;
	
}
