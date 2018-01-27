package org.tyrest.systrade.face.model;

import java.util.Date;

import org.tyrest.core.mysql.BaseModel;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsSerialModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsSerialModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class TransactionsSerialModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private String serialNo;
	private String billNo;
	private String agencyCode;
	private Long userId;
	private String channelSerialNo;
	private Integer tradeAmount;
	private Integer discntFee;
	private String discntDesc;
	private Date syncFinishTime;
	private Date sendTime;
	private String resultMessage;
	private Date finishTime;
	private Date asyncFinishTime;
	private String clientIp;
	private String clientPlatform;
	private String payMethod;
	private String tradeStatus;
	private String tradeType;



	public String getSerialNo() {
		return this.serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getChannelSerialNo() {
		return this.channelSerialNo;
	}
	public void setChannelSerialNo(String channelSerialNo) {
		this.channelSerialNo = channelSerialNo;
	}
	
	public Integer getTradeAmount() {
		return this.tradeAmount;
	}
	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	public Integer getDiscntFee() {
		return this.discntFee;
	}
	public void setDiscntFee(Integer discntFee) {
		this.discntFee = discntFee;
	}
	
	public String getDiscntDesc() {
		return this.discntDesc;
	}
	public void setDiscntDesc(String discntDesc) {
		this.discntDesc = discntDesc;
	}
	
	public Date getSyncFinishTime() {
		return this.syncFinishTime;
	}
	public void setSyncFinishTime(Date syncFinishTime) {
		this.syncFinishTime = syncFinishTime;
	}
	
	public Date getSendTime() {
		return this.sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	public String getResultMessage() {
		return this.resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	public Date getFinishTime() {
		return this.finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	public Date getAsyncFinishTime() {
		return this.asyncFinishTime;
	}
	public void setAsyncFinishTime(Date asyncFinishTime) {
		this.asyncFinishTime = asyncFinishTime;
	}
	
	public String getClientIp() {
		return this.clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public String getClientPlatform() {
		return this.clientPlatform;
	}
	public void setClientPlatform(String clientPlatform) {
		this.clientPlatform = clientPlatform;
	}
	
	public String getPayMethod() {
		return this.payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public String getTradeStatus() {
		return this.tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	public String getTradeType() {
		return this.tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	
}

