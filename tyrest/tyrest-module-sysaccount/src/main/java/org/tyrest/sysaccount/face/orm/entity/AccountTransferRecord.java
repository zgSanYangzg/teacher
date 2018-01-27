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
 *  File: AccountTransferRecord.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountTransferRecord.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "account_transfer_record")
public class AccountTransferRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String billNo;
	private String sourceAccountNo;
	private String targetAccountNo;
	private String transferType;
	private int transferAmount;
	private String transferStatus;
	private Date transferTime;
	private Long userId;
	private String transferPostscript;
	private String sourceAccountType;
	private String targetAccountType;

	@Column(name = "BILL_NO", nullable = false)
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@Column(name = "SOURCE_ACCOUNT_NO", nullable = false)
	public String getSourceAccountNo() {
		return this.sourceAccountNo;
	}
	public void setSourceAccountNo(String sourceAccountNo) {
		this.sourceAccountNo = sourceAccountNo;
	}
	
	@Column(name = "TARGET_ACCOUNT_NO", nullable = false)
	public String getTargetAccountNo() {
		return this.targetAccountNo;
	}
	public void setTargetAccountNo(String targetAccountNo) {
		this.targetAccountNo = targetAccountNo;
	}
	
	@Column(name = "TRANSFER_TYPE", nullable = false)
	public String getTransferType() {
		return this.transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	
	@Column(name = "TRANSFER_AMOUNT", nullable = false)
	public int getTransferAmount() {
		return this.transferAmount;
	}
	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	@Column(name = "TRANSFER_STATUS", nullable = false)
	public String getTransferStatus() {
		return this.transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	
	@Column(name = "TRANSFER_TIME", nullable = false)
	public Date getTransferTime() {
		return this.transferTime;
	}
	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}
	
	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "TRANSFER_POSTSCRIPT", nullable = false)
	public String getTransferPostscript() {
		return this.transferPostscript;
	}
	public void setTransferPostscript(String transferPostscript) {
		this.transferPostscript = transferPostscript;
	}
	
	@Column(name = "SOURCE_ACCOUNT_TYPE", nullable = false)
	public String getSourceAccountType() {
		return this.sourceAccountType;
	}
	public void setSourceAccountType(String sourceAccountType) {
		this.sourceAccountType = sourceAccountType;
	}
	
	@Column(name = "TARGET_ACCOUNT_TYPE", nullable = false)
	public String getTargetAccountType() {
		return this.targetAccountType;
	}
	public void setTargetAccountType(String targetAccountType) {
		this.targetAccountType = targetAccountType;
	}
	
}

