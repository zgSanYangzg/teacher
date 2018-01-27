package org.tyrest.security.face.service;

import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.security.face.enums.AuthType;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.model.PrincipalModel;
import org.tyrest.security.face.orm.entity.Principal;
import org.tyrest.security.face.orm.entity.UserSession;

/**
 * <pre>
 * 
 *  freeapis
 *  File: PrincipalService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:用户认证体系中的安全服务，提供主体的查询，信息修改，密码修改，创建安全信息等服务
 *  TODO
 * 
 *  Notes:
 * 	$Id: PrincipalService.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月9日		wuqiang		Initial.
 *  
 * </pre>
 */
public interface SecurityService extends BaseService<PrincipalModel, Principal> {
	/**
	 * TODO.公网用户安全信息注册,注册的方式多种多样，如手机号＋验证码＋密码注册，
	 * 邮箱＋验证码＋密码注册，此业务默认实现手机号＋验证码＋密码注册
	 * @param idType
	 * @param userType
	 * @param authModel
	 * @return
	 * @throws Exception
	 */
	PrincipalModel createSecurityInfoForRegister(IdType idType, UserType userType, AuthRequestModel authModel) throws Exception;
	
	/**
	 * TODO.当用户认证信息不存在时才创建，如果存在则直接返回,
	 * 此业务适用于短信认证或者第三方认证时的账号安全信息的创建,
	 * 相当于注册业务
	 * @param agencyCode
	 * @param loginId
	 * @param userType
	 * @param idType
	 * @return
	 * @throws Exception
	 */
	PrincipalModel createSecurityInfoIfNotExist(String agencyCode, String loginId, UserType userType, IdType idType) throws Exception;
	/**
	 * TODO.获取用户的认证信息,包含密码等安全属性
	 * @param agencyCode
	 * @param loginId
	 * @param idType
	 * @return
	 * @throws Exception
	 */
	PrincipalModel getSecurityInfo(String agencyCode, String loginId, UserType userType, IdType idType) throws Exception;
	
	/**
	 * TODO.创建用户认证信息
	 * @param principalModel
	 * @param idType
	 * @return
	 * @throws Exception
	 */
	PrincipalModel createPrincipal(PrincipalModel principalModel, IdType idType) throws Exception;
	/**
	 * TODO.删除用户的主体信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	void deletePrincipal(Long userId) throws Exception;
	/**
	 * TODO.更新主体信息.此方法只更新主体信息的普通非安全属性，
	 * 如用户名，昵称，性别等，主体的安全属性使用专门的业务方法更新
	 * @param principalModel
	 * @return
	 * @throws Exception
	 */
	PrincipalModel updatePrincipal(PrincipalModel principalModel) throws Exception;
	/**
	 * TODO.获取账号的主体信息，不包含密码等安全字段
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	PrincipalModel getPrincipal(Long userId) throws Exception;
	/**
	 * TODO.锁定／解锁主体的账号信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	PrincipalModel updateLockStatus(Long userId) throws Exception;
	/**
	 * TODO.充值主体账号的密码信息
	 * @param userId
	 * @throws Exception
	 */
	void resetPassword(Long userId) throws Exception;
	/**
	 * TODO.修改主体账号的密码信息
	 * @param requiredParams
	 * @throws Exception
	 */
	void modifyPassword(AuthRequestModel requiredParams) throws Exception;
	/**
	 * TODO.修改主体的登录手机号
	 * @param oldMobile
	 * @param newMobile
	 * @param smsId
	 * @param identifyingCode
	 * @param password
	 * @throws Exception
	 */
	void modifyMobileNumber(String oldMobile, String newMobile, String smsId, String identifyingCode, String password) throws Exception;
	/**
	 * TODO.清理测试账号的数据,按照测试账号映射的用户名清理
	 * @param userName
	 * @param operrationType
	 * @throws Exception
	 */
	void updateCleanTestData(String userName, String operrationType) throws Exception;
	/**
	 * TODO.按照手机号清理测试数据，此处的手机号必须是用户的登录账号
	 * @param phoneNum
	 * @param optionPassword
	 * @throws Exception
	 */
	void updateCleanTargetData(String phoneNum, String optionPassword) throws Exception;
	
	/**
	 * TODO.获取或者刷新token,加入refreshToken的机制是为了方便client端,免去了无意义的登录,毕竟登录需要验证很多资源
	 * 
	 * @param token
	 * @param product
	 * @return
	 * @throws Exception
	 */
	UserSession getOrRefreshSession(String token, String product) throws Exception;

	/**
	 * TODO.创建session,相当于真正的登录认证,认证成功后创建会话
	 * 
	 * @param idType
	 * @param authType
	 * @param userType
	 * @param authModel
	 * @return
	 * @throws Exception
	 */
	UserSession createSession(IdType idType, AuthType authType, UserType userType, AuthRequestModel authModel) throws Exception;

	/**
	 * 根据对象修改
	 * 
	 * @param userSessionPo
	 * @throws Exception
	 */
	public void updateSession(UserSession userSessionPo) throws Exception;

	/**
	 * 按照id删除对象
	 * 
	 * @param sessionId
	 * @param product
	 * @throws Exception
	 */
	public void deleteSession(String sessionId, String product) throws Exception;

	/**
	 * 清除用户登录缓存
	 * @param userId
	 * @throws Exception
	 */
	void cleanLoginCache(Long userId) throws Exception;

	/**
	 * 修改员工密码
	 * @param userId
	 * @param Password
	 * @param oldPassword
	 * @throws Exception
	 */
	void updatePassword(Long userId, String Password, String oldPassword) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/