package org.tyrest.core.rest.security.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.rest.containers.APILevel;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AgencyAdapter.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgencyAdapter.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "agencyAuthAdapter")
public class AgencyAdapter extends AuthAdapter {

	@Override
	protected boolean doSubAuth(HandlerMethod handlerMethod, String resource, String operation, APILevel apiLevel)
			throws Exception {
		return UserType.AGENCY_USER.getValue() >= apiLevel.getValue();
	}

}

/*
 * $Log: av-env.bat,v $
 */