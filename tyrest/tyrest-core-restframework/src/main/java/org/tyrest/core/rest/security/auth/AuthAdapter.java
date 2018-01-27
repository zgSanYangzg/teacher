package org.tyrest.core.rest.security.auth;

import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AuthAdapter.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AuthAdapter.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class AuthAdapter {

	public static final String AUTH_ADAPTER_PUBLICAUTHADAPTER = "publicAuthAdapter";
	public static final String AUTH_ADAPTER_AGENCYAUTHADAPTER = "agencyAuthAdapter";
	public static final String AUTH_ADAPTER_SUPERADMINAUTHADAPTER = "superAdminAuthAdapter";
	public static final String AUTH_ADAPTER_ANONYMOUSAUTHADAPTER = "anonymousAuthAdapter";

	/**
	 * AuthAdapter ganerator
	 * 
	 * @param userType
	 * @return
	 */
	public static AuthAdapter getAuthAdapter(UserType userType) {
		AuthAdapter adaptor = null;
		switch (userType) {
		case PUBLIC_USER:
			adaptor = (AuthAdapter) SpringContextHelper.getBean(AUTH_ADAPTER_PUBLICAUTHADAPTER);
			break;
		case AGENCY_USER:
			adaptor = (AuthAdapter) SpringContextHelper.getBean(AUTH_ADAPTER_AGENCYAUTHADAPTER);
			break;
		case SUPER_ADMIN:
			adaptor = (AuthAdapter) SpringContextHelper.getBean(AUTH_ADAPTER_SUPERADMINAUTHADAPTER);
			break;
		case ANONYMOUS:
			adaptor = (AuthAdapter) SpringContextHelper.getBean(AUTH_ADAPTER_ANONYMOUSAUTHADAPTER);
			break;
		default:
			break;
		}
		return adaptor;
	}

	/**
	 * 提供给authInterceptor调用,登陆和基本权限校验
	 * 
	 * @param handlerMethod
	 * @param session
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public boolean doAuth(HandlerMethod handlerMethod, String appKey) throws Exception {
		boolean result = false;
		// 获取方法头上的注解
		TyrestResource tyrestResource = handlerMethod.getBeanType().getAnnotation(TyrestResource.class);
		TyrstOperation tyrestOperation = handlerMethod.getMethodAnnotation(TyrstOperation.class);
		// 调用的方法所操作的资源
		String resource = tyrestResource.value();
		// 调用的方法对资源的操作
		String operation = tyrestOperation.name();
		// 获得API 级别
		APILevel apiLevel = tyrestOperation.ApiLevel();

		result = doSubAuth(handlerMethod, resource, operation, apiLevel);

		return result;
	}

	protected abstract boolean doSubAuth(HandlerMethod handlerMethod, String resource, String operation,
			APILevel apiLevel) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */