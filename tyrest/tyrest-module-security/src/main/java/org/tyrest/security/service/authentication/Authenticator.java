package org.tyrest.security.service.authentication;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.exceptions.UserIdOrPasswordNotValidException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.CommonUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.security.face.orm.dao.LoginHistoryDAO;
import org.tyrest.security.face.orm.dao.LoginInfoDAO;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.orm.entity.LoginHistory;
import org.tyrest.security.face.orm.entity.LoginInfo;
import org.tyrest.security.face.orm.entity.Principal;
import org.tyrest.security.face.orm.entity.UserSession;
import org.tyrest.security.face.enums.AuthType;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.model.PrincipalModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <pre>
 * 
 *  freeapis
 *  File: Authenticator.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: Authenticator.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月7日		wuqiang		Initial.
 * 
 * </pre>
 */
@Component
public class Authenticator {

	private static final Logger logger = LoggerFactory.getLogger(Authenticator.class);
	
	private static final String BEAN_NAME_SUFIX = "Authenticator";
	
	@Autowired
	protected LoginInfoDAO loginInfoDAO;

	@Autowired
	protected PrincipalDAO principalDAO;
	
	@Autowired
	protected LoginHistoryDAO loginHistoryDAO;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	/**
	 * TODO.登录认证创建会话
	 * @param idType	登录账号类型
	 * @param authType	认证方式
	 * @param userType	用户类型
	 * @param authModel	认证信息
	 * @return
	 * @throws Exception
	 */
	public UserSession authenticate(IdType idType, AuthType authType, UserType userType,
			AuthRequestModel authModel) throws Exception {
		
		String product = RequestContext.getProduct();
		String requestIP = RequestContext.getRequestIP();
		String userAgent = RequestContext.getUserAgent();
		
		if(ValidationUtil.isEmpty(product)
				|| ValidationUtil.isEmpty(requestIP)
				|| ValidationUtil.isEmpty(userAgent)){
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_AUTHINFO_NOT_VALID);
		}
		UserSession session = new UserSession();
		if(UserType.ANONYMOUS.equals(userType)){
			session.setSsoSessionId(CommonUtil.getUUID());
			session.setAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
			session.setUserId(sequenceGenerator.getNextValue());
			session.setLoginId(UserType.ANONYMOUS.name());
			session.setSsoUserName(UserType.ANONYMOUS.getLabel());
			session.setSsoSessionStatus(CoreConstants.COMMON_N);
			session.setUserType(UserType.ANONYMOUS.getValue());
			session.setActionByIp(requestIP);
			session.setActionByAgent(userAgent);
			session.setActionByProduct(product); 
			session.setSsoSessionCreation(new Date());
			session.setSsoSessionExpiration(String.valueOf(userType.getExpireDuration()));
		}else{
			PrincipalModel currentPrincipal = getAuthenticator(authType).doAuthenticate(idType, userType, authModel);
			session.setSsoSessionId(CommonUtil.getUUID());
			session.setAgencyCode(currentPrincipal.getAgencyCode());
			session.setUserId(currentPrincipal.getUserId());
			session.setLoginId(currentPrincipal.getLoginId());
			session.setSsoUserName(currentPrincipal.getLoginId());
			session.setSsoSessionStatus(currentPrincipal.getLockStatus());
			session.setUserType(currentPrincipal.getUserType());
			session.setPassword(currentPrincipal.getPassword());
			session.setSalt(currentPrincipal.getSalt());
			session.setActionByIp(requestIP);
			session.setActionByAgent(userAgent);
			session.setActionByProduct(product); 
			session.setSsoSessionCreation(new Date());
			session.setSsoSessionExpiration(String.valueOf(userType.getExpireDuration()));
		}
		return session;
	}

	private static Authenticator getAuthenticator(AuthType authType) throws Exception {
		return SpringContextHelper.getBean(authType.name() + BEAN_NAME_SUFIX);
	}

	protected PrincipalModel doAuthenticate(IdType idType, UserType userType, AuthRequestModel authModel) throws Exception{
		logger.error("this method must be implemented by subClass of Authenticator!");
		throw new BusinessException("this method must be implemented by subClass of Authenticator!");
	}
	
	public  UserSession refreshSession(String token,String product) throws Exception{
		UserSession session = null;
		LoginHistory lastLoginRecord = loginHistoryDAO.findLastLoginBySessionId(token, product);
		if (!ValidationUtil.isEmpty(lastLoginRecord)) {
			UserType currentUserType = UserType.getUserType(lastLoginRecord.getUserType());
			//如果是匿名用户,则直接返回刷新后的session
			if(currentUserType.equals(UserType.ANONYMOUS)){
				session = new UserSession();
				session.setSsoSessionId(CommonUtil.getUUID());
				session.setAgencyCode(lastLoginRecord.getAgencyCode());
				session.setUserId(lastLoginRecord.getUserId());
				session.setLoginId(lastLoginRecord.getLoginId());
				session.setSsoUserName(lastLoginRecord.getLoginId());
				session.setUserType(lastLoginRecord.getUserType());
				session.setSsoSessionStatus(CoreConstants.COMMON_ACTIVE);
				session.setSsoSessionExpiration(String.valueOf(currentUserType.getExpireDuration()));
				session.setActionByIp(lastLoginRecord.getActionByIp());
				session.setActionByAgent(lastLoginRecord.getActionByAgent());
				session.setActionByProduct(lastLoginRecord.getActionByProduct());
				session.setSsoSessionCreation(new Date());
			}else{
				String currentLoginId = lastLoginRecord.getLoginId();
				// 获取当前用户的登录账号信息
				LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfo(CoreConstants.CODE_SUPER_ADMIN, currentLoginId);
				Principal currentPrincipal = null;
				if(!ValidationUtil.isEmpty(currentLoginInfo)){
					currentPrincipal = principalDAO.findByUserId(currentLoginInfo.getUserId());
				}
				// 如果登录账号存在,并且登录账号状态正常,才创建新的session
				if (!ValidationUtil.isEmpty(currentLoginInfo)
						&& !ValidationUtil.isEmpty(currentPrincipal)
						&& CoreConstants.COMMON_N.equals(currentLoginInfo.getLockStatus())) {
					session = new UserSession();
					session.setSsoSessionId(CommonUtil.getUUID());
					session.setAgencyCode(currentLoginInfo.getAgencyCode());
					session.setUserId(currentLoginInfo.getUserId());
					session.setLoginId(currentLoginInfo.getLoginId());
					session.setSsoUserName(currentLoginInfo.getLoginId());
					session.setUserType(currentPrincipal.getUserType());
					session.setPassword(currentPrincipal.getPassword());
					session.setSalt(currentPrincipal.getSalt());
					session.setSsoSessionStatus(CoreConstants.COMMON_ACTIVE);
					session.setSsoSessionExpiration(String.valueOf(currentUserType.getExpireDuration()));
					session.setActionByIp(lastLoginRecord.getActionByIp());
					session.setActionByAgent(lastLoginRecord.getActionByAgent());
					session.setActionByProduct(lastLoginRecord.getActionByProduct());
					session.setSsoSessionCreation(new Date());
				}
			}
		}
		return session;
	}
}

/*
 * $Log: av-env.bat,v $
 */