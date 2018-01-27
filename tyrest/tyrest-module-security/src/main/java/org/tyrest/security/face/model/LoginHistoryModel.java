package org.tyrest.security.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LoginHistoryModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginHistoryModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:41		freeapis		Initial.
 *
 * </pre>
 */
public class LoginHistoryModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String loginId;
	private String ssoSessionId;
	private String agencyCode;
	private String ssoUserName;
	private String actionByProduct;
	private String actionByIp;
	private String ssoSessionExpiration;
	private String ssoSessionStatus;
	private Date ssoSessionCreation;
	private String actionByAgent;
	private Integer userType;

	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getLoginId() {
		return this.loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
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
	
	public String getActionByAgent() {
		return this.actionByAgent;
	}
	public void setActionByAgent(String actionByAgent) {
		this.actionByAgent = actionByAgent;
	}
	
	public Integer getUserType() {
		return this.userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}

