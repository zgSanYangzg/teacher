package org.tyrest.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.security.face.orm.dao.LoginInfoDAO;
import org.tyrest.security.face.orm.entity.LoginInfo;
import org.tyrest.security.face.service.LoginService;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LoginServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginInfoDAO loginInfoDAO;

	@Override
	public List<LoginInfo> getLoginInfos(String agencyCode, Long userId) throws Exception {
		return loginInfoDAO.findLoginInfos(agencyCode, userId);
	}
	
	@Override
	public boolean isLoginIdAvailable(String agencyCode, String loginId) throws Exception {
		return loginInfoDAO.isLoginIdAvailable(agencyCode, loginId);
	}
}

/*
 * $Log: av-env.bat,v $
 */