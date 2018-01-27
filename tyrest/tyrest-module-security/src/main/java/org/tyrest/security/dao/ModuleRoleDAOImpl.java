package org.tyrest.security.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.ModuleRoleDAO;
import org.tyrest.security.face.orm.entity.ModuleRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityModuleRoleDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityModuleRoleDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value="moduleRoleDAO")
public class ModuleRoleDAOImpl extends GenericDAOImpl<ModuleRole> implements ModuleRoleDAO
{
	/**
	 * 角色对应的所有模块资源信息
	 */
	private static final String ROLE_MODULE = "ROLE_MODULE";

	@Override
	public List<ModuleRole> findByRoleCode(String roleCode, String agencyCode) throws Exception {
		List<ModuleRole> moudleRoles = Redis.get(ROLE_MODULE,agencyCode,roleCode);
		if(!ValidationUtil.isEmpty(moudleRoles)){
			return moudleRoles;
		}
		String sqlSufix = " AND AGENCY_CODE = :AGENCY_CODE AND ROLE_CODE = :ROLE_CODE ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("ROLE_CODE", roleCode);
		moudleRoles = this.find(sqlSufix, params, null, null);
		if(!ValidationUtil.isEmpty(moudleRoles)){
			Redis.set(moudleRoles, agencyCode,roleCode);
		}
		return moudleRoles;
	}

	@Override
	public int deleteByRoleCode(String agencyCode, String roleCode) throws Exception {
		Redis.remove(ROLE_MODULE,agencyCode,roleCode);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("ROLE_CODE", roleCode);
		return this.update("DELETE FROM "+ this.tableName() +" WHERE AGENCY_CODE = :AGENCY_CODE AND ROLE_CODE = :ROLE_CODE", params);
	}

}
