package org.tyrest.security.face.service;

import java.util.List;

import org.tyrest.security.face.orm.entity.LoginInfo;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LoginService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface LoginService {
	/**
	 * TODO.根据用户ID获取登录信息，一个系统用户可能对应多个登录账号
	 * 
	 * @param agencyCode
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<LoginInfo> getLoginInfos(String agencyCode, Long userId) throws Exception;

	/**
	 * TODO.验证登录账号是否可用
	 * 
	 * @param agencyCode
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public boolean isLoginIdAvailable(String agencyCode, String loginId) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */