package org.tyrest.security.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SmsHistoryModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SmsHistoryModel.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月13日		framework		Initial.
 *
 * </pre>
 */
public class SmsVerifyModel extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String smsId;
	private String mobile;
	private String smsVerify;
	private String smsType;
	private Date sendTime;

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsVerify() {
		return this.smsVerify;
	}

	public void setSmsVerify(String smsVerify) {
		this.smsVerify = smsVerify;
	}

	public String getSmsType() {
		return this.smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
