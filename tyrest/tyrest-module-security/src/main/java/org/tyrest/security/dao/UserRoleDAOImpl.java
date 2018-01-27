package org.tyrest.security.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.UserRoleDAO;
import org.tyrest.security.face.orm.entity.Role;
import org.tyrest.security.face.orm.entity.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: UserRoleDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: UserRoleDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Repository(value="userRoleDAO")
public class UserRoleDAOImpl extends GenericDAOImpl<UserRole> implements UserRoleDAO
{
	
	@Override
	public List<UserRole> findUserRoles(String agencyCode, Long userId) throws Exception {
		List<UserRole> userRoles = Redis.getSingle(this.getEntityClass(), agencyCode,Long.toString(userId));
		if(ValidationUtil.isEmpty(userRoles)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("AGENCY_CODE", agencyCode);
			params.put("USER_ID", userId);
			userRoles = this.find("  AND AGENCY_CODE=:AGENCY_CODE AND USER_ID=:USER_ID ", params, "recDate", "desc");
			if(!ValidationUtil.isEmpty(userRoles)){
				Redis.setSingle(userRoles, agencyCode,Long.toString(userId));
			}
		}
		return ValidationUtil.isEmpty(userRoles) ? new ArrayList<UserRole>() : userRoles;
	}

	@Override
	public List<UserRole> findByLockStatus(String agencyCode, Long userId, String lockStatus)
			throws Exception {
		if(ValidationUtil.isEmpty(lockStatus)){
			return this.findUserRoles(agencyCode, userId);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("USER_ID", userId);
		params.put("LOCK_STATUS", lockStatus);
		return this.find(" AND AGENCY_CODE=:AGENCY_CODE AND USER_ID=:USER_ID  AND LOCK_STATUS=:LOCK_STATUS ", params, "recDate", "desc");
	}

	@Override
	public void deleteUserRoles(String agencyCode, Long userId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("USER_ID", userId);
		this.update("DELETE FROM "+ this.tableName() +" WHERE AGENCY_CODE=:AGENCY_CODE AND USER_ID=:USER_ID ",params);
		Redis.removeSingle(this.getEntityClass(), agencyCode,Long.toString(userId));
	}

	@Override
	public List<Map<String, Object>> findUserRolesInfo(String agencyCode,Long userId) throws Exception{
		String sql = "SELECT a.ROLE_CODE as roleCode," +
				" b.ROLE_NAME as roleName FROM "+this.tableName()+" a INNER JOIN "
				+ Role.class.getAnnotation(Table.class).name() +" b " +
				" WHERE a.ROLE_CODE = b.ROLE_CODE " +
				" AND a.AGENCY_CODE=:AGENCY_CODE AND a.USER_ID=:USER_ID";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("USER_ID", userId);
		return this.findMaps(sql,params);
	}

}
