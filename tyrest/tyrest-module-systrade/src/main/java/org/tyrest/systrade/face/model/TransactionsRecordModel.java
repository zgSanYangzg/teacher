package org.tyrest.systrade.face.model;

import java.util.Date;

import org.tyrest.core.mysql.BaseModel;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsRecordModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsRecordModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class TransactionsRecordModel extends BaseModel
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

	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getSerialNo() {
		return this.serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public Integer getTradeAmount() {
		return this.tradeAmount;
	}
	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	public String getAgencyName() {
		return this.agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	public Date getFinishedTime() {
		return this.finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	
	public Date getSettlementTime() {
		return this.settlementTime;
	}
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}
	
	public String getTradeType() {
		return this.tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	public String getPayMethod() {
		return this.payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
}

