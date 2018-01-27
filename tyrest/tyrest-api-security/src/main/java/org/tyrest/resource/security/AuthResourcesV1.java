package org.tyrest.resource.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.security.validation.BeanValidation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.publicuser.face.service.PublicUserService;
import org.tyrest.security.face.enums.AuthType;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.orm.entity.UserSession;
import org.tyrest.security.face.service.SecurityService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AuthResourcesV1.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AuthResourcesV1.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/authentication")
@TyrestResource(module = "security",value = "AuthResourcesV1", description = "平台用户认证")
public class AuthResourcesV1 extends BaseResources
{
	@Autowired
	private SecurityService securityService;

	@Autowired
	private PublicUserService publicUserService;


	@TyrstOperation(name = "publicSignUp", ApiLevel = APILevel.ALL,  description = "公网用户注册")
	@RequestMapping(value = "/public", method = RequestMethod.POST)
	@BeanValidation
	public ResponseModel<String> publicSignUp(@RequestBody AuthRequestModel authModel) throws Exception
	{
		publicUserService.signUp(authModel);
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	}

	@TyrstOperation(name = "authAnonymous", ApiLevel = APILevel.ALL,  description = "匿名用户登录",needAuth = false)
	@RequestMapping(value = "/anonymous", method = RequestMethod.POST)
	public ResponseModel<Map<String, Object>> authAnonymous(@RequestBody AuthRequestModel authModel) throws Exception
	{ 
		authModel.setAgency(CoreConstants.CODE_SUPER_ADMIN);
		return createSession(IdType.mobile, AuthType.userNamePassword, UserType.ANONYMOUS,authModel);
	}
	
	@TyrstOperation(name = "authPublic", ApiLevel = APILevel.ALL,  description = "公网用户登录",needAuth = false)
	@RequestMapping(value = "/public/{authType}/{idType}", method = RequestMethod.POST)
	@BeanValidation
	public ResponseModel<Map<String, Object>> authPublic(@RequestBody AuthRequestModel authModel,
														 @PathVariable String authType,
														 @PathVariable String idType) throws Exception
	{
		AuthType authTypeEnum = AuthType.getAuthType(authType);
		IdType idTypeEnum = IdType.valueOf(idType);
		authModel.setAgency(CoreConstants.CODE_SUPER_ADMIN);
		Map<String, Object> result = publicUserService.signIn(idTypeEnum,authTypeEnum,UserType.PUBLIC_USER,authModel);
		return ResponseHelper.buildResponseModel(result);
	}
	
	@TyrstOperation(name = "authAgency", ApiLevel = APILevel.AGENCY,  description = "商家员工登录。",needAuth = false)
	@RequestMapping(value = "/agency", method = RequestMethod.POST)
	public ResponseModel<Map<String, Object>> authAgency(@RequestBody AuthRequestModel authModel) throws Exception
	{
		return createSession(IdType.employeeCode,AuthType.userNamePassword,UserType.AGENCY_USER, authModel);
	}
	
	@TyrstOperation(name = "authSuperAdmin", ApiLevel = APILevel.SUPERADMIN,  description = "超级管理员登录。",needAuth = false)
	@RequestMapping(value = "/superadmin", method = RequestMethod.POST)
	public ResponseModel<Map<String, Object>> authSuperAdmin(@RequestBody AuthRequestModel authModel) throws Exception
	{
		return createSession(IdType.employeeCode,AuthType.userNamePassword,UserType.SUPER_ADMIN, authModel);
	}
	
	private ResponseModel<Map<String, Object>> createSession(IdType idType,AuthType authType,UserType userType, AuthRequestModel authModel)
			throws Exception
	{
		UserSession userSession = securityService.createSession(idType,authType,userType,authModel);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(CoreConstants.TOKEN, userSession.getSsoSessionId());
		result.put(CoreConstants.EXPIRE, userSession.getSsoSessionExpiration());
		result.put(CoreConstants.USERID, userSession.getUserId());
		return ResponseHelper.buildResponseModel(result);
	}
	
	@TyrstOperation(name = "getSubmitToken", ApiLevel = APILevel.ALL,  description = "提交表单之前获取令牌.")
	@RequestMapping(value = "/submittoken", method = RequestMethod.GET)
	public ResponseModel<String> getSubmitToken() throws Exception
	{
		Redis.setWithExpire(0, 1800L,CoreConstants.SUBMIT_TOKEN, RequestContext.getExeUserId(),UUID.randomUUID().toString());
		return ResponseHelper.buildResponseModel(null);
	}

}
/*
 * $Log: av-env.bat,v $
 */