package org.tyrest.systrade.face.model;


import org.tyrest.core.mysql.BaseModel;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: PingxxChargeModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PingxxChargeModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class PingxxChargeModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String app;
	private String channel;
	private Integer amount;
	private String clientIp;
	private String currency;
	private String subject;
	private String body;
	private String extra;
	private Integer timeExpire;
	private String metadata;
	private String id;
	private String object;
	private Integer created;
	private Boolean livemode;
	private Boolean paid;
	private Boolean refunded;
	private Integer amountSettle;
	private Integer timePaid;
	private Integer timeSettle;
	private String transactionNo;
	private String refunds;
	private Integer amountRefunded;
	private String failureCode;
	private String failureMsg;
	private String credential;

	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getApp() {
		return this.app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	
	public String getChannel() {
		return this.channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Integer getAmount() {
		return this.amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getClientIp() {
		return this.clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return this.body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getExtra() {
		return this.extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public Integer getTimeExpire() {
		return this.timeExpire;
	}
	public void setTimeExpire(Integer timeExpire) {
		this.timeExpire = timeExpire;
	}
	
	public String getMetadata() {
		return this.metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getObject() {
		return this.object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
	public Integer getCreated() {
		return this.created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
	
	public Boolean getLivemode() {
		return this.livemode;
	}
	public void setLivemode(Boolean livemode) {
		this.livemode = livemode;
	}
	
	public Boolean getPaid() {
		return this.paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	public Boolean getRefunded() {
		return this.refunded;
	}
	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}
	
	public Integer getAmountSettle() {
		return this.amountSettle;
	}
	public void setAmountSettle(Integer amountSettle) {
		this.amountSettle = amountSettle;
	}
	
	public Integer getTimePaid() {
		return this.timePaid;
	}
	public void setTimePaid(Integer timePaid) {
		this.timePaid = timePaid;
	}
	
	public Integer getTimeSettle() {
		return this.timeSettle;
	}
	public void setTimeSettle(Integer timeSettle) {
		this.timeSettle = timeSettle;
	}
	
	public String getTransactionNo() {
		return this.transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	
	public String getRefunds() {
		return this.refunds;
	}
	public void setRefunds(String refunds) {
		this.refunds = refunds;
	}
	
	public Integer getAmountRefunded() {
		return this.amountRefunded;
	}
	public void setAmountRefunded(Integer amountRefunded) {
		this.amountRefunded = amountRefunded;
	}
	
	public String getFailureCode() {
		return this.failureCode;
	}
	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}
	
	public String getFailureMsg() {
		return this.failureMsg;
	}
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	
	public String getCredential() {
		return this.credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	
}

