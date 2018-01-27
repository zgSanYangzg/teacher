package org.tyrest.security.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.PermissionDAO;
import org.tyrest.security.face.orm.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityRolePrivilegeDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityRolePrivilegeDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value="permissionDAO")
public class PermissionDAOImpl extends GenericDAOImpl<Permission> implements PermissionDAO
{

	private static final String ROLE_PRIVILEGE = "ROLE_PRIVILEGE";
	
	@Override
	public List<Permission> findByModuleCode(String agencyCode, String roleCode, String moduleCode) throws Exception {
		
		List<Permission> rolePrivileges = Redis.get(ROLE_PRIVILEGE,agencyCode,roleCode,moduleCode);
		
		if(!ValidationUtil.isEmpty(rolePrivileges)){
			return rolePrivileges;
		}
		String sqlSufix = " AND AGENCY_CODE = :AGENCY_CODE AND ROLE_CODE = :ROLE_CODE AND MODULE_CODE = :MODULE_CODE";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("ROLE_CODE", roleCode);
		params.put("MODULE_CODE", moduleCode);
		rolePrivileges = this.find(sqlSufix, params, null, null);
		if(!ValidationUtil.isEmpty(rolePrivileges)){
			Redis.set(rolePrivileges, agencyCode,roleCode,moduleCode);
		}
		return rolePrivileges;
	}

	@Override
	public int deleteByRoleCode(String agencyCode, String roleCode) throws Exception {
		//清除缓存
		Redis.removeByPrefix(ROLE_PRIVILEGE,agencyCode,roleCode);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("ROLE_CODE", roleCode);
		return this.update("DELETE FROM "+ this.tableName() +" WHERE AGENCY_CODE = :AGENCY_CODE AND ROLE_CODE = :ROLE_CODE", params);
		
	}

}
