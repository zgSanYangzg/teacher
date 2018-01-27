package org.tyrest.sysaccount.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

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

public class AccountCashoutRecordModel extends BaseModel
{

	private Long userId;
	private String userName;
	private String applayNo;
	private String applayType;
	private String transferAccount;
	private String transferName;
	private String openBank;
	private int outAmount;//实际转账金额
	private String applyStatus;
	private Date finishTime;
	private int poundage;//手续费
	private int applayAmount;//申请提现金额

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplayNo() {
		return applayNo;
	}

	public void setApplayNo(String applayNo) {
		this.applayNo = applayNo;
	}

	public String getApplayType() {
		return applayType;
	}

	public void setApplayType(String applayType) {
		this.applayType = applayType;
	}

	public String getTransferAccount() {
		return transferAccount;
	}

	public void setTransferAccount(String transferAccount) {
		this.transferAccount = transferAccount;
	}

	public String getTransferName() {
		return transferName;
	}

	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public int getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}


	public int getPoundage() {
		return poundage;
	}

	public void setPoundage(int poundage) {
		this.poundage = poundage;
	}

	public int getApplayAmount() {
		return applayAmount;
	}

	public void setApplayAmount(int applayAmount) {
		this.applayAmount = applayAmount;
	}
}

