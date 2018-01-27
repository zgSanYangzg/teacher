package org.tyrest.sysaccount.face.model;

import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.core.mysql.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AccountSerial.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountSerial.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */

public class AccountSerialModel extends BaseModel
{
	
	private Long userId;
	private String accountNo;
	private int initialPrefundedBalance;
	private int changeAmount;
	private int finalBalance;
	private String operationType;
	private Date operateTime;
	private String billNo;
	private Long updateVersion;
	private String bookkeeping;
	private String accountType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getInitialPrefundedBalance() {
		return initialPrefundedBalance;
	}

	public void setInitialPrefundedBalance(int initialPrefundedBalance) {
		this.initialPrefundedBalance = initialPrefundedBalance;
	}

	public int getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public int getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(int finalBalance) {
		this.finalBalance = finalBalance;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Long getUpdateVersion() {
		return updateVersion;
	}

	public void setUpdateVersion(Long updateVersion) {
		this.updateVersion = updateVersion;
	}

	public String getBookkeeping() {
		return bookkeeping;
	}

	public void setBookkeeping(String bookkeeping) {
		this.bookkeeping = bookkeeping;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}

