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
@Entity
@Table(name = "account_serial")
public class AccountSerial extends BaseEntity
{
	private static final long serialVersionUID = -6820241068321216631L;
	
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
	
	@Column(name = "INITIAL_PREFUNDED_BALANCE", nullable = false)
	public int getInitialPrefundedBalance() {
		return this.initialPrefundedBalance;
	}
	public void setInitialPrefundedBalance(int initialPrefundedBalance) {
		this.initialPrefundedBalance = initialPrefundedBalance;
	}
	
	@Column(name = "CHANGE_AMOUNT", nullable = false)
	public int getChangeAmount() {
		return this.changeAmount;
	}
	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}
	
	@Column(name = "FINAL_BALANCE", nullable = false)
	public int getFinalBalance() {
		return this.finalBalance;
	}
	public void setFinalBalance(int finalBalance) {
		this.finalBalance = finalBalance;
	}
	
	@Column(name = "OPERATION_TYPE", nullable = false)
	public String getOperationType() {
		return this.operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	@Column(name = "OPERATE_TIME", nullable = false)
	public Date getOperateTime() {
		return this.operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "BILL_NO", nullable = false)
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@Column(name = "UPDATE_VERSION")
	public Long getUpdateVersion()
	{
		return updateVersion;
	}
	public void setUpdateVersion(Long updateVersion)
	{
		this.updateVersion = updateVersion;
	}
	
	@Column(name = "BOOKKEEPING", nullable = false)
	public String getBookkeeping()
	{
		return bookkeeping;
	}
	public void setBookkeeping(String bookkeeping)
	{
		this.bookkeeping = bookkeeping;
	}
	
	
	
	@Column(name = "ACCOUNT_TYPE", nullable = false)
	public String getAccountType()
	{
		return accountType;
	}
	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}
}

