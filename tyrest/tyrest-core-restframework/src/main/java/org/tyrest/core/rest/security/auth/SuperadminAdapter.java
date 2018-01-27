package org.tyrest.core.rest.security.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.rest.containers.APILevel;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: SuperadminAdapter.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SuperadminAdapter.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "superAdminAuthAdapter")
public class SuperadminAdapter extends AuthAdapter {

	@Override
	protected boolean doSubAuth(HandlerMethod handlerMethod, String resource, String operation, APILevel apiLevel)
			throws Exception {
		return UserType.SUPER_ADMIN.getValue() >= apiLevel.getValue();
	}
}

/*
 * $Log: av-env.bat,v $
 */