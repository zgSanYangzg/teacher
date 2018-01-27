package org.tyrest.security.face.model;

import org.tyrest.core.mysql.BaseModel;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AuthRequestModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AuthRequestModel.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
public class AuthRequestModel extends BaseModel {
	private static final long serialVersionUID = -536412498052440039L;

	private String agency;

	private String loginId;//如果是公网用户修改手机号码，此字段填写新手机号码

	private String password;

	private String openId;

	private String identifyingCode;

	private String mobile;	//员工修改密码时，验证员工手机号

	private String oldMobile;//公网用户修改手机号专用-旧手机号码

	public String getOldMobile() {
		return oldMobile;
	}

	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}

/*
 * $Log: av-env.bat,v $
 */