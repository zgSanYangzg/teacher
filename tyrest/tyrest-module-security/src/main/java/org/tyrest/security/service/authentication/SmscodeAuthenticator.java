package org.tyrest.security.service.authentication;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.UserIdOrPasswordNotValidException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.security.face.service.SecurityService;
import org.tyrest.security.face.service.SmsVerifyService;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.model.PrincipalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 
 *  freeapis
 *  File: SmscodeAuthenticator.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: SmscodeAuthenticator.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月7日		wuqiang		Initial.
 * 
 * </pre>
 */
@Component(value = "smscodeAuthenticator")
public class SmscodeAuthenticator extends Authenticator {

	@Autowired
	private SmsVerifyService smsVerifyService;
	
	@Autowired
	private SecurityService securityService;

	@Override
	protected PrincipalModel doAuthenticate(IdType idType, UserType userType, AuthRequestModel authModel) throws Exception {

		// #1.获取验证器需要的参数
		String agencyCode = authModel.getAgency();
		String loginId = authModel.getLoginId();
		String sessionId = RequestContext.getSessionId();
		String identifyingCode = authModel.getIdentifyingCode();
		// #2.校验参数完整性
		if (ValidationUtil.isEmpty(idType)
				|| ValidationUtil.isEmpty(userType) 
				|| ValidationUtil.isEmpty(agencyCode) 
				|| ValidationUtil.isEmpty(loginId)
				|| ValidationUtil.isEmpty(sessionId)
				|| ValidationUtil.isEmpty(identifyingCode)) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_AUTHINFO_NOT_VALID);
		}
		//3#.验证ID类型，短信验证必须要求ID是手机号
		if(!IdType.mobile.equals(idType)) throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_LOGINID_NOT_VALID);
		
		//#4调用安全接口，在用户不存在时创建用户安全信息
		PrincipalModel currentPrincipal = securityService.createSecurityInfoIfNotExist(agencyCode, loginId, userType, idType);

		if (ValidationUtil.isEmpty(currentPrincipal)) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_USER_NOT_FOUND);
		} else if (!CoreConstants.COMMON_N.equals(currentPrincipal.getLockStatus())) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_USER_LOCKED);
		} else if (!smsVerifyService.verifyCode(sessionId,loginId,identifyingCode)) {
			throw new UserIdOrPasswordNotValidException(SecurityConstants.MESSAGE_SMSCODE_ERROR);
		}

		return currentPrincipal;
	}

}

/*
 * $Log: av-env.bat,v $
 */