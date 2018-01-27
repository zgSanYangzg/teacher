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
 *  File: AccountCashoutRecord.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountCashoutRecord.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "account_cashout_record")
public class AccountCashoutRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String applayNo;
	private String applayType;
	private String transferAccount;
	private String transferName;
	private String openBank;
	private int outAmount;
	private String applyStatus;
	private Date finishTime;
	private int poundage;//手续费
	private int applayAmount;//申请提现金额


	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "APPLAY_NO", nullable = false)
	public String getApplayNo() {
		return this.applayNo;
	}
	public void setApplayNo(String applayNo) {
		this.applayNo = applayNo;
	}
	
	@Column(name = "APPLAY_TYPE", nullable = false)
	public String getApplayType() {
		return this.applayType;
	}
	public void setApplayType(String applayType) {
		this.applayType = applayType;
	}
	
	@Column(name = "TRANSFER_ACCOUNT", nullable = false)
	public String getTransferAccount() {
		return this.transferAccount;
	}
	public void setTransferAccount(String transferAccount) {
		this.transferAccount = transferAccount;
	}
	
	@Column(name = "TRANSFER_NAME", nullable = false)
	public String getTransferName() {
		return this.transferName;
	}
	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}
	
	@Column(name = "OPEN_BANK")
	public String getOpenBank() {
		return this.openBank;
	}
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	@Column(name = "OUT_AMOUNT", nullable = false)
	public int getOutAmount() {
		return this.outAmount;
	}
	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}
	
	@Column(name = "APPLY_STATUS", nullable = false)
	public String getApplyStatus() {
		return this.applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Column(name = "FINISH_TIME")
	public Date getFinishTime() {
		return this.finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "POUNDAGE")
	public int getPoundage() {
		return poundage;
	}

	public void setPoundage(int poundage) {
		this.poundage = poundage;
	}

	@Column(name = "APPLAY_AMOUNT")
	public int getApplayAmount() {
		return applayAmount;
	}

	public void setApplayAmount(int applayAmount) {
		this.applayAmount = applayAmount;
	}
}

