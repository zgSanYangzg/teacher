package org.tyrest.systrade.face.orm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsRecord.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsRecord.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "trade_transactions_record")
public class TransactionsRecord extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String billNo;
	private String serialNo;
	private Integer tradeAmount;
	private String agencyCode;
	private String agencyName;
	private Date finishedTime;
	private Date settlementTime;
	private String tradeType;
	private String payMethod;

	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "BILL_NO", unique = true, nullable = false)
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@Column(name = "SERIAL_NO", nullable = false)
	public String getSerialNo() {
		return this.serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@Column(name = "TRADE_AMOUNT", nullable = false)
	public Integer getTradeAmount() {
		return this.tradeAmount;
	}
	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "AGENCY_NAME")
	public String getAgencyName() {
		return this.agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	@Column(name = "FINISHED_TIME")
	public Date getFinishedTime() {
		return this.finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	
	@Column(name = "SETTLEMENT_TIME")
	public Date getSettlementTime() {
		return this.settlementTime;
	}
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}
	
	@Column(name = "TRADE_TYPE")
	public String getTradeType() {
		return this.tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	@Column(name = "PAY_METHOD")
	public String getPayMethod() {
		return this.payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
}

