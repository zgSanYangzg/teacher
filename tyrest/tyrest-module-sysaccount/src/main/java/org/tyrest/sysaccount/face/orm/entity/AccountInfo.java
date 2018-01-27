package org.tyrest.sysaccount.face.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AccountInfo.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountInfo.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "account_info")
public class AccountInfo extends BaseEntity
{
	private static final long serialVersionUID = -7485595589815879086L;
	
	private String agencyCode;
	private Long userId;
	private String accountNo;
	private int balance;
	private String accountType;
	private String accountStatus;
	private String paymentPassword;
	private int cumulativeBalance;
	private Long updateVersion;
	private Date createTime;

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "ACCOUNT_NO", unique = true)
	public String getAccountNo() {
		return this.accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Column(name = "BALANCE", nullable = false)
	public int getBalance() {
		return this.balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Column(name = "ACCOUNT_TYPE", nullable = false)
	public String getAccountType() {
		return this.accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "ACCOUNT_STATUS", nullable = false)
	public String getAccountStatus() {
		return this.accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	@Column(name = "PAYMENT_PASSWORD")
	public String getPaymentPassword() {
		return this.paymentPassword;
	}
	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}
	
	@Column(name = "CUMULATIVE_BALANCE")
	public int getCumulativeBalance() {
		return this.cumulativeBalance;
	}
	public void setCumulativeBalance(int cumulativeBalance) {
		this.cumulativeBalance = cumulativeBalance;
	}
	
	@Column(name = "UPDATE_VERSION")
	public Long getUpdateVersion() {
		return updateVersion;
	}
	public void setUpdateVersion(Long updateVersion) {
		this.updateVersion = updateVersion;
	}
	
	@Column(name = "CREATE_TIME")
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}

