package org.tyrest.security.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.orm.entity.Principal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PrincipalDAOImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PrincipalDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Repository(value="principalDAO")
public class PrincipalDAOImpl extends GenericDAOImpl<Principal> implements PrincipalDAO
{
	@Override
	public void deleteByUserId(Long userId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("USER_ID",userId);
		this.update("DEELTE FROM "+ this.tableName() +" WHERE USER_ID = :USER_ID",params);
		Redis.removeSingle(this.getEntityClass(),userId.toString());
	}

	@Override
	public void update(Principal principal) throws Exception {
		super.update(principal);
		Redis.removeSingle(this.getEntityClass(), principal.getUserId().toString());
	}

	@Override
	public Principal findByUserId(Long userId) throws Exception {
		Principal principal = Redis.getSingle(this.getEntityClass(), userId.toString());
		if(ValidationUtil.isEmpty(principal)){
			StringBuilder sql = new StringBuilder(" AND USER_ID = :USER_ID ");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("USER_ID",userId);
			principal = this.findFirst(sql.toString(), params);
			if(!ValidationUtil.isEmpty(principal)){
				Redis.setSingle(principal,principal.getUserId().toString());
			}
		}
		return principal;
	}

	@Override
	public void cleanTestData(Long userId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> operrationSqls = new ArrayList<>();
		operrationSqls.add(" DELETE FROM public_user_address WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM public_user_wechat_info WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM security_login_history WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM security_login_info WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM security_principal WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM security_principal_snpt WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM security_public_user WHERE USER_ID =:USER_ID");
		operrationSqls.add(" DELETE FROM systemctl_comment WHERE USER_ID =:USER_ID");
		params.put("USER_ID",userId);
		for(String operrationSql:operrationSqls){
			this.update(operrationSql,params);
		}
	}
}
