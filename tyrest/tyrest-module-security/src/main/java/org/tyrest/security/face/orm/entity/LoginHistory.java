package org.tyrest.security.face.orm.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LoginHistory.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginHistory.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:41		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "security_login_history")
@DynamicUpdate
public class LoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "SEQUENCE_NBR")
	private Long sequenceNBR;
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
	private Date recDate;
	private String recUserId;
	private String recStatus;

	@Column(name = "SEQUENCE_NBR", nullable = false)
	public Long getSequenceNBR() {
		return sequenceNBR;
	}

	public void setSequenceNBR(Long sequenceNBR) {
		this.sequenceNBR = sequenceNBR;
	}

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "LOGIN_ID", nullable = false)
	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name = "SSO_SESSION_ID", nullable = false)
	public String getSsoSessionId() {
		return this.ssoSessionId;
	}

	public void setSsoSessionId(String ssoSessionId) {
		this.ssoSessionId = ssoSessionId;
	}

	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	@Column(name = "SSO_USER_NAME", nullable = false)
	public String getSsoUserName() {
		return this.ssoUserName;
	}

	public void setSsoUserName(String ssoUserName) {
		this.ssoUserName = ssoUserName;
	}

	@Column(name = "ACTION_BY_PRODUCT", nullable = false)
	public String getActionByProduct() {
		return this.actionByProduct;
	}

	public void setActionByProduct(String actionByProduct) {
		this.actionByProduct = actionByProduct;
	}

	@Column(name = "ACTION_BY_IP")
	public String getActionByIp() {
		return this.actionByIp;
	}

	public void setActionByIp(String actionByIp) {
		this.actionByIp = actionByIp;
	}

	@Column(name = "SSO_SESSION_EXPIRATION", nullable = false)
	public String getSsoSessionExpiration() {
		return this.ssoSessionExpiration;
	}

	public void setSsoSessionExpiration(String ssoSessionExpiration) {
		this.ssoSessionExpiration = ssoSessionExpiration;
	}

	@Column(name = "SSO_SESSION_STATUS", nullable = false)
	public String getSsoSessionStatus() {
		return this.ssoSessionStatus;
	}

	public void setSsoSessionStatus(String ssoSessionStatus) {
		this.ssoSessionStatus = ssoSessionStatus;
	}

	@Column(name = "SSO_SESSION_CREATION", nullable = false)
	public Date getSsoSessionCreation() {
		return this.ssoSessionCreation;
	}

	public void setSsoSessionCreation(Date ssoSessionCreation) {
		this.ssoSessionCreation = ssoSessionCreation;
	}

	@Column(name = "ACTION_BY_AGENT")
	public String getActionByAgent() {
		return this.actionByAgent;
	}

	public void setActionByAgent(String actionByAgent) {
		this.actionByAgent = actionByAgent;
	}

	@Column(name = "USER_TYPE", nullable = false)
	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "REC_DATE", nullable = false)
	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	@Column(name = "REC_USER_ID", nullable = false)
	public String getRecUserId() {
		return recUserId;
	}

	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}

	@Column(name = "REC_STATUS", nullable = false)
	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

}
