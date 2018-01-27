package org.tyrest.security.face.orm.entity;

import java.util.Date;

/**
 * <pre>
 * 
 *  freeapis
 *  File: UserSession.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: UserSession.java 31101200-9 2014-10-14 16:43:51Z freeapis\baijunyan $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月7日		baijunyan		Initial.
 *
 * </pre>
 */
public class UserSession implements java.io.Serializable {
	private static final long serialVersionUID = 1701924515983788810L;

	private String ssoSessionId;

	private String agencyCode;

	private String ssoUserName;

	private int userType; // 用户类型

	private String actionByProduct;

	private String actionByIp;

	private String ssoSessionExpiration;

	private String ssoSessionStatus;

	private Date ssoSessionCreation;

	private String password;

	private String salt;// MD5盐

	private Long userId;

	private String loginId;

	private String actionByAgent;

	private boolean isRefresh;

	public String getSsoSessionId() {
		return this.ssoSessionId;
	}

	public void setSsoSessionId(String ssoSessionId) {
		this.ssoSessionId = ssoSessionId;
	}

	public String getAgencyCode() {
		return this.agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getSsoUserName() {
		return this.ssoUserName;
	}

	public void setSsoUserName(String ssoUserName) {
		this.ssoUserName = ssoUserName;
	}

	public String getActionByProduct() {
		return this.actionByProduct;
	}

	public void setActionByProduct(String actionByProduct) {
		this.actionByProduct = actionByProduct;
	}

	public String getActionByIp() {
		return this.actionByIp;
	}

	public void setActionByIp(String actionByIp) {
		this.actionByIp = actionByIp;
	}

	public String getSsoSessionExpiration() {
		return this.ssoSessionExpiration;
	}

	public void setSsoSessionExpiration(String ssoSessionExpiration) {
		this.ssoSessionExpiration = ssoSessionExpiration;
	}

	public String getSsoSessionStatus() {
		return this.ssoSessionStatus;
	}

	public void setSsoSessionStatus(String ssoSessionStatus) {
		this.ssoSessionStatus = ssoSessionStatus;
	}

	public Date getSsoSessionCreation() {
		return this.ssoSessionCreation;
	}

	public void setSsoSessionCreation(Date ssoSessionCreation) {
		this.ssoSessionCreation = ssoSessionCreation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getActionByAgent() {
		return this.actionByAgent;
	}

	public void setActionByAgent(String actionByAgent) {
		this.actionByAgent = actionByAgent;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isRefresh() {
		return isRefresh;
	}

	public void setRefresh(boolean isRefresh) {
		this.isRefresh = isRefresh;
	}

}