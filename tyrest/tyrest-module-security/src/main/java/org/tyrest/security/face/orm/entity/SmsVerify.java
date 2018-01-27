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
 *  File: SmsHistory.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SmsHistory.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:40:31		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "security_sms_verify")
public class SmsVerify extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String smsId;
	private String mobile;
	private String smsVerify;
	private String smsType;
	private Date sendTime;

	@Column(name = "SMS_ID", nullable = false)
	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	@Column(name = "MOBILE", nullable = false)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "SMS_VERIFY")
	public String getSmsVerify() {
		return this.smsVerify;
	}

	public void setSmsVerify(String smsVerify) {
		this.smsVerify = smsVerify;
	}

	@Column(name = "SMS_TYPE")
	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	@Column(name = "SEND_TIME")
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
