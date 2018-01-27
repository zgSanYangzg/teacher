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
 *  File: TransactionsSerial.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsSerial.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "trade_transactions_serial")
public class TransactionsSerial extends BaseEntity
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

	@Column(name = "SERIAL_NO", unique = true, nullable = false)
	public String getSerialNo() {
		return this.serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@Column(name = "BILL_NO", nullable = false)
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "USER_ID")
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "CHANNEL_SERIAL_NO")
	public String getChannelSerialNo() {
		return this.channelSerialNo;
	}
	public void setChannelSerialNo(String channelSerialNo) {
		this.channelSerialNo = channelSerialNo;
	}
	
	@Column(name = "TRADE_AMOUNT", nullable = false)
	public Integer getTradeAmount() {
		return this.tradeAmount;
	}
	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	@Column(name = "DISCNT_FEE")
	public Integer getDiscntFee() {
		return this.discntFee;
	}
	public void setDiscntFee(Integer discntFee) {
		this.discntFee = discntFee;
	}
	
	@Column(name = "DISCNT_DESC")
	public String getDiscntDesc() {
		return this.discntDesc;
	}
	public void setDiscntDesc(String discntDesc) {
		this.discntDesc = discntDesc;
	}
	
	@Column(name = "SYNC_FINISH_TIME", nullable = false)
	public Date getSyncFinishTime() {
		return this.syncFinishTime;
	}
	public void setSyncFinishTime(Date syncFinishTime) {
		this.syncFinishTime = syncFinishTime;
	}
	
	@Column(name = "SEND_TIME")
	public Date getSendTime() {
		return this.sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Column(name = "RESULT_MESSAGE")
	public String getResultMessage() {
		return this.resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	@Column(name = "FINISH_TIME")
	public Date getFinishTime() {
		return this.finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	@Column(name = "ASYNC_FINISH_TIME")
	public Date getAsyncFinishTime() {
		return this.asyncFinishTime;
	}
	public void setAsyncFinishTime(Date asyncFinishTime) {
		this.asyncFinishTime = asyncFinishTime;
	}
	
	@Column(name = "CLIENT_IP")
	public String getClientIp() {
		return this.clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	@Column(name = "CLIENT_PLATFORM")
	public String getClientPlatform() {
		return this.clientPlatform;
	}
	public void setClientPlatform(String clientPlatform) {
		this.clientPlatform = clientPlatform;
	}
	
	@Column(name = "PAY_METHOD", nullable = false)
	public String getPayMethod() {
		return this.payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Column(name = "TRADE_STATUS", nullable = false)
	public String getTradeStatus() {
		return this.tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	@Column(name = "TRADE_TYPE", nullable = false)
	public String getTradeType() {
		return this.tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
}

