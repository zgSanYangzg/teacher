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
 *  File: AccountRechargeRecord.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountRechargeRecord.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "account_recharge_record")
public class AccountRechargeRecord extends BaseEntity
{
	private static final long serialVersionUID = -2349177773986247517L;
	
	private Long userId;
	private String accountNo; 
	private Date rechargeTime;
	private String infoStatus;
	private int rechargeAmount;
	private String billNo;

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "ACCOUNT_NO")
	public String getAccountNo() {
		return this.accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Column(name = "RECHARGE_TIME", nullable = false)
	public Date getRechargeTime() {
		return this.rechargeTime;
	}
	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	
	@Column(name = "INFO_STATUS")
	public String getInfoStatus() {
		return this.infoStatus;
	}
	public void setInfoStatus(String infoStatus) {
		this.infoStatus = infoStatus;
	}
	
	@Column(name = "RECHARGE_AMOUNT", nullable = false)
	public int getRechargeAmount() {
		return this.rechargeAmount;
	}
	public void setRechargeAmount(int rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	
	@Column(name = "BILL_NO", nullable = false)
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
}

