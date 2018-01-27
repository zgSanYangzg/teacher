package org.tyrest.systrade.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: PingxxCharge.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PingxxCharge.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "trade_pingxx_charge")
public class PingxxCharge extends BaseEntity
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

	@Column(name = "order_no", nullable = false)
	public String getOrderNo() {
		return this.orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "app", nullable = false)
	public String getApp() {
		return this.app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	
	@Column(name = "channel", nullable = false)
	public String getChannel() {
		return this.channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@Column(name = "amount", nullable = false)
	public Integer getAmount() {
		return this.amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Column(name = "client_ip", nullable = false)
	public String getClientIp() {
		return this.clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	@Column(name = "currency", nullable = false)
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name = "subject", nullable = false)
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "body", nullable = false)
	public String getBody() {
		return this.body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Column(name = "extra")
	public String getExtra() {
		return this.extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	@Column(name = "time_expire")
	public Integer getTimeExpire() {
		return this.timeExpire;
	}
	public void setTimeExpire(Integer timeExpire) {
		this.timeExpire = timeExpire;
	}
	
	@Column(name = "metadata")
	public String getMetadata() {
		return this.metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	
	@Column(name = "id", nullable = false)
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "object", nullable = false)
	public String getObject() {
		return this.object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
	@Column(name = "created", nullable = false)
	public Integer getCreated() {
		return this.created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
	
	@Column(name = "livemode", nullable = false)
	public Boolean getLivemode() {
		return this.livemode;
	}
	public void setLivemode(Boolean livemode) {
		this.livemode = livemode;
	}
	
	@Column(name = "paid", nullable = false)
	public Boolean getPaid() {
		return this.paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	@Column(name = "refunded", nullable = false)
	public Boolean getRefunded() {
		return this.refunded;
	}
	public void setRefunded(Boolean refunded) {
		this.refunded = refunded;
	}
	
	@Column(name = "amount_settle")
	public Integer getAmountSettle() {
		return this.amountSettle;
	}
	public void setAmountSettle(Integer amountSettle) {
		this.amountSettle = amountSettle;
	}
	
	@Column(name = "time_paid")
	public Integer getTimePaid() {
		return this.timePaid;
	}
	public void setTimePaid(Integer timePaid) {
		this.timePaid = timePaid;
	}
	
	@Column(name = "time_settle")
	public Integer getTimeSettle() {
		return this.timeSettle;
	}
	public void setTimeSettle(Integer timeSettle) {
		this.timeSettle = timeSettle;
	}
	
	@Column(name = "transaction_no")
	public String getTransactionNo() {
		return this.transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	
	@Column(name = "refunds")
	public String getRefunds() {
		return this.refunds;
	}
	public void setRefunds(String refunds) {
		this.refunds = refunds;
	}
	
	@Column(name = "amount_refunded")
	public Integer getAmountRefunded() {
		return this.amountRefunded;
	}
	public void setAmountRefunded(Integer amountRefunded) {
		this.amountRefunded = amountRefunded;
	}
	
	@Column(name = "failure_code")
	public String getFailureCode() {
		return this.failureCode;
	}
	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}
	
	@Column(name = "failure_msg")
	public String getFailureMsg() {
		return this.failureMsg;
	}
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	
	@Column(name = "credential", nullable = false)
	public String getCredential() {
		return this.credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	
}

