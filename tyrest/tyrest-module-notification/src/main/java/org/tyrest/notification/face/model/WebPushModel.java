package org.tyrest.notification.face.model;

import java.util.List;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: WebPushModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WebPushModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class WebPushModel extends AbstractPushModel{

	private static final long serialVersionUID = 1L;

	private String agencyCode;// 商家编码

	private List<Long> userIds;// 商家员工用户

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	
}	

/*
*$Log: av-env.bat,v $
*/