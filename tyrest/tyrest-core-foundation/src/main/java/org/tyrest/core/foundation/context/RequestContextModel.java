package org.tyrest.core.foundation.context;

import org.tyrest.core.foundation.enumeration.UserType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 *  freeapis
 *  File: RestThreadLocalModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  
 * 
 *  Notes:
 *  $Id: RestThreadLocalModel.java 31101200-9 2014-10-14 16:43:51Z freeapis\baijunyan $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月7日		baijunyan		Initial.
 *
 * </pre>
 */
public class RequestContextModel implements Serializable {
	private static final long serialVersionUID = -6237296664652045754L;

	private String agencyCode;

	private String traceId;

	private String sessionId;

	private String excutedUserId;

	private UserType userType;

	private String requestIP;

	private String userAgent;

	private String product;

	private String appVersion;

	private Map<String, Object> extra;

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getExcutedUserId() {
		return excutedUserId;
	}

	public void setExcutedUserId(String excutedUserId) {
		this.excutedUserId = excutedUserId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getRequestIP() {
		return requestIP;
	}

	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public void setAttribute(String key, Object value) {
		if (extra == null) {
			extra = new HashMap<String, Object>();
		}
		extra.put(key, value);
	}

	public Object getAttribute(String key) {
		if (extra == null) {
			return null;
		}
		return extra.get(key);
	}

	public void clean() {
		this.traceId = null;
		this.sessionId = null;
		this.excutedUserId = null;
		this.agencyCode = null;
		this.userType = null;
		this.userAgent = null;
		this.product = null;
		this.appVersion = null;
		if (this.extra != null) {
			this.extra = null;
		}
	}

}
