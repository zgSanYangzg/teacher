package org.tyrest.agency.face.constants;

/**
 * <pre>
 * 
 *  freeapis
 *  File: AgencyConstants.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: AgencyConstants.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年8月26日		wuqiang		Initial.
 * 
 * </pre>
 */
public class AgencyConstants {
	
	public static final String DEFAULT_AGENCY_COORDINATE = "108.938198,34.230751";

	public static final String DEFAULT_AGENCY_USER_PASSWORD = "a123456";
	
	public static final String CUSTOM_SERVICE = "CUSTOM_SERVICE";
	
	public static final String NO_CUSTOM_SERVICE_CAN_USE = "暂无可用的客服";
	
	//在Redis中的所有客服的list列表key
	public static final String CUSTOM_SERVICE_LIST = "CUSTOM_SERVICE_LIST";
	
	//Redis循环到的客服的序列数key
	public static final String CUSTOM_SERVICE_SORT = "CUSTOM_SERVICE_SORT";
}

/*
 * $Log: av-env.bat,v $
 */