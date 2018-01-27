package org.tyrest.sysorder.face.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderHistory.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderHistory.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "order_history")
public class OrderHistory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String orderSn;
	private String billNo;
	private Integer amount;
	private Integer productAmount;
	private Integer couponDeduction;
	private String orderType;
	private String orderStatus;
	private Date createTime;
	private Date payTime;
	private String payMethod;
	private String payStatus;
	private Long userId;
	private String agencyCode;
	private String source;

	@Column(name = "ORDER_SN", unique = true, nullable = false)
	public String getOrderSn() {
		return this.orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	@Column(name = "BILL_NO", nullable = false)
	public String getBillNo() {
		return this.billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@Column(name = "AMOUNT", nullable = false)
	public Integer getAmount() {
		return this.amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Column(name = "PRODUCT_AMOUNT", nullable = false)
	public Integer getProductAmount() {
		return this.productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	
	@Column(name = "COUPON_DEDUCTION")
	public Integer getCouponDeduction() {
		return this.couponDeduction;
	}
	public void setCouponDeduction(Integer couponDeduction) {
		this.couponDeduction = couponDeduction;
	}
	
	@Column(name = "ORDER_TYPE", nullable = false)
	public String getOrderType() {
		return this.orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Column(name = "ORDER_STATUS", nullable = false)
	public String getOrderStatus() {
		return this.orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Column(name = "CREATE_TIME", nullable = false)
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "PAY_TIME")
	public Date getPayTime() {
		return this.payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	@Column(name = "PAY_METHOD")
	public String getPayMethod() {
		return this.payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@Column(name = "PAY_STATUS", nullable = false)
	public String getPayStatus() {
		return this.payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "SOURCE")
	public String getSource() {
		return this.source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}

