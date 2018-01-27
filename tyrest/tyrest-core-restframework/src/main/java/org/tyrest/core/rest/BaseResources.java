package org.tyrest.core.rest;

import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.utils.ValidationUtil;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: BaseResources.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BaseResources.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class BaseResources {

	protected boolean hasLogin() {
		if (!ValidationUtil.isEmpty(RequestContext.getAgencyCode())
				&& !ValidationUtil.isEmpty(RequestContext.getExeUserId())
				&& !ValidationUtil.isEmpty(RequestContext.getUserType())
				&& !UserType.ANONYMOUS.equals(RequestContext.getUserType())) {
			return true;
		}
		return false;
	}

	protected boolean isSuperAdmin() {
		if (!ValidationUtil.isEmpty(RequestContext.getAgencyCode())
				&& !ValidationUtil.isEmpty(RequestContext.getExeUserId())
				&& !ValidationUtil.isEmpty(RequestContext.getUserType())
				&& UserType.SUPER_ADMIN.equals(RequestContext.getUserType())) {
			return true;
		}
		return false;
	}

	protected boolean isAnonymous() {
		if (!ValidationUtil.isEmpty(RequestContext.getAgencyCode())
				&& !ValidationUtil.isEmpty(RequestContext.getExeUserId())
				&& !ValidationUtil.isEmpty(RequestContext.getUserType())
				&& UserType.ANONYMOUS.equals(RequestContext.getUserType())) {
			return true;
		}
		return false;
	}

	protected boolean isPublicUser() {
		if (!ValidationUtil.isEmpty(RequestContext.getAgencyCode())
				&& !ValidationUtil.isEmpty(RequestContext.getExeUserId())
				&& !ValidationUtil.isEmpty(RequestContext.getUserType())
				&& UserType.PUBLIC_USER.equals(RequestContext.getUserType())) {
			return true;
		}
		return false;
	}
}

/*
 * $Log: av-env.bat,v $
 */