package org.tyrest.security.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: OperationModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OperationModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:41		freeapis		Initial.
 *
 * </pre>
 */
public class OperationModel extends BaseModel
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

	public String getResourceCode() {
		return this.resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	public String getResourceName() {
		return this.resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getResType() {
		return this.resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	
	public String getOprateCode() {
		return this.oprateCode;
	}
	public void setOprateCode(String oprateCode) {
		this.oprateCode = oprateCode;
	}
	
	public String getOprateDescription() {
		return this.oprateDescription;
	}
	public void setOprateDescription(String oprateDescription) {
		this.oprateDescription = oprateDescription;
	}
	
	public String getLevelCode() {
		return this.levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	public String getReqUrl() {
		return this.reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	
	public String getReqMode() {
		return this.reqMode;
	}
	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}
	
	public String getFuncId() {
		return this.funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	public String getIgnoreAuth() {
		return this.ignoreAuth;
	}
	public void setIgnoreAuth(String ignoreAuth) {
		this.ignoreAuth = ignoreAuth;
	}
	
	public String getLockStatus() {
		return this.lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	public Date getLockDate() {
		return this.lockDate;
	}
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
	public String getLockUserId() {
		return this.lockUserId;
	}
	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}
	
}

