package org.tyrest.security.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: Operation.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Operation.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:41		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "security_operation")
public class Operation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String resourceCode;
	private String resourceName;
	private String resType;
	private String oprateCode;
	private String oprateDescription;
	private String levelCode;
	private String reqUrl;
	private String reqMode;
	private String funcId;
	private String ignoreAuth;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;

	@Column(name = "RESOURCE_CODE", nullable = false)
	public String getResourceCode() {
		return this.resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	@Column(name = "RESOURCE_NAME", nullable = false)
	public String getResourceName() {
		return this.resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	@Column(name = "RES_TYPE")
	public String getResType() {
		return this.resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	
	@Column(name = "OPRATE_CODE")
	public String getOprateCode() {
		return this.oprateCode;
	}
	public void setOprateCode(String oprateCode) {
		this.oprateCode = oprateCode;
	}
	
	@Column(name = "OPRATE_DESCRIPTION")
	public String getOprateDescription() {
		return this.oprateDescription;
	}
	public void setOprateDescription(String oprateDescription) {
		this.oprateDescription = oprateDescription;
	}
	
	@Column(name = "LEVEL_CODE")
	public String getLevelCode() {
		return this.levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	@Column(name = "REQ_URL")
	public String getReqUrl() {
		return this.reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	
	@Column(name = "REQ_MODE")
	public String getReqMode() {
		return this.reqMode;
	}
	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}
	
	@Column(name = "FUNC_ID", nullable = false)
	public String getFuncId() {
		return this.funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	@Column(name = "IGNORE_AUTH")
	public String getIgnoreAuth() {
		return this.ignoreAuth;
	}
	public void setIgnoreAuth(String ignoreAuth) {
		this.ignoreAuth = ignoreAuth;
	}
	
	@Column(name = "LOCK_STATUS", nullable = false)
	public String getLockStatus() {
		return this.lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	@Column(name = "LOCK_DATE")
	public Date getLockDate() {
		return this.lockDate;
	}
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
	@Column(name = "LOCK_USER_ID")
	public String getLockUserId() {
		return this.lockUserId;
	}
	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}
	
}

