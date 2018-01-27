package org.tyrest.security.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.LoginHistoryDAO;
import org.tyrest.security.face.orm.entity.LoginHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LoginHistoryDAOImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginHistoryDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\ligang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月7日		ligang		Initial.
 *
 * </pre>
 */
@Repository(value = "loginHistoryDAO")
public class LoginHistoryDAOImpl extends GenericDAOImpl<LoginHistory>implements LoginHistoryDAO {

	public LoginHistory getLastLoginFromHistory(String loginId, String agencyCode, String product) throws Exception {
		StringBuilder sqlSufix = new StringBuilder(
				"AND LOGIN_ID = :LOGIN_ID AND AGENCY_CODE =:AGENCY_CODE AND ACTION_BY_PRODUCT = :PRODUCT ORDER BY SSO_SESSION_CREATION DESC");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("LOGIN_ID", loginId);
		params.put("AGENCY_CODE", agencyCode);
		params.put("PRODUCT", product);
		return findFirst(sqlSufix.toString(), params);
	}

	@Override
	public List<LoginHistory> findLoginHistorys(Long userId) throws Exception {
		String sqlSufix = " AND USER_ID = :USER_ID ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", userId);
		return this.find(sqlSufix, params, null, null);
	}

	@Override
	public LoginHistory findLastLoginBySessionId(String sessionId, String product) throws Exception {
		StringBuilder sqlSufix = new StringBuilder(
				"AND SSO_SESSION_ID = :SSO_SESSION_ID AND ACTION_BY_PRODUCT = :PRODUCT ORDER BY SSO_SESSION_CREATION DESC");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SSO_SESSION_ID", sessionId);
		params.put("PRODUCT", product);
		return findFirst(sqlSufix.toString(), params);
	}
}

/*
 * $Log: av-env.bat,v $
 */