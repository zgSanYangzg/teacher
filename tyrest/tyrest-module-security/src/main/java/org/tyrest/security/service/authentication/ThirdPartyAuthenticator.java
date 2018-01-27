package org.tyrest.security.service.authentication;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.UserIdOrPasswordNotValidException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.model.PrincipalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.tyrest.security.face.service.SecurityService;

/**
 * <pre>
 * 
 *  freeapis
 *  File: ThirdPartyAuthenticator.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ThirdPartyAuthenticator.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月12日		wuqiang		Initial.
 *  
 * </pre>
 */
public class ThirdPartyAuthenticator extends Authenticator{

    @Autowired
	private SecurityService securityService;
	
	@Override
	protected PrincipalModel doAuthenticate(IdType idType, UserType userType, AuthRequestModel authModel) throws Exception {

		// #1.获取验证器需要的参数
		String agencyCode = authModel.getAgency();
		String loginId = authModel.getLoginId();
		
		// #2.校验参数完整性
		if (ValidationUtil.isEmpty(idType)
				|| ValidationUtil.isEmpty(userType) 
				|| ValidationUtil.isEmpty(agencyCode) 
				|| ValidationUtil.isEmpty(loginId)) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_AUTHINFO_NOT_VALID);
		}
		
		//#3调用安全接口，为第三方登录创建账号信息
		PrincipalModel currentPrincipal = securityService.createSecurityInfoIfNotExist(agencyCode, loginId, userType, idType);

		if (ValidationUtil.isEmpty(currentPrincipal)) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_AUTHINFO_NOT_VALID);
		} else if (!CoreConstants.COMMON_N.equals(currentPrincipal.getLockStatus())) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_USER_LOCKED);
		}
		return currentPrincipal;
	}
}

/*
*$Log: av-env.bat,v $
*/