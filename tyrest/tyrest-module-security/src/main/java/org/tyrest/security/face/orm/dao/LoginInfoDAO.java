package org.tyrest.security.face.orm.dao;

import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.orm.entity.LoginInfo;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LoginInfoDAO.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginInfoDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
public interface LoginInfoDAO extends GenericDAO<LoginInfo>
{
	
	public void deleteByLoginId(String agencyCode, String loginId) throws Exception;
	
	public LoginInfo findLoginInfo(String agencyCode, String loginId) throws Exception;
	/**
	 * TODO.通过登录账号获取登录信息
	 * @param agencyCode
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public LoginInfo findLoginInfoForLogin(String agencyCode, String loginId, UserType userType, IdType idType) throws Exception;
	/**
	 * TODO.通过系统用户ID获取登录信息,一个系统用户可能有多个登录账号
	 * @param agencyCode
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<LoginInfo> findLoginInfos(String agencyCode, Long userId) throws Exception;
	/**
	 * TODO.验证登录账号是否可用的通用方法,如验证公网用户手机号 ,后台用户登录ID
	 * @param agencyCode
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public boolean isLoginIdAvailable(String agencyCode, String loginId) throws Exception;
	
}

/*
*$Log: av-env.bat,v $
*/