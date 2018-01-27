package org.tyrest.sysorder.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderDetail.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderDetail.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String orderSn;
	private String userName;
	private String agencyName;
	private String agencyPhone;
	private String productNames;
	private String customerType;
	private String orderPostscript;
	private String payNote;
	private String cacelUserId;
	private String couponNo;
	private Integer scoreDeduction;
	private Integer discountDeduction;
	private Integer couponTicketDeduction;
	private String isComment;

	@Column(name = "ORDER_SN", unique = true, nullable = false)
	public String getOrderSn() {
		return this.orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "AGENCY_NAME")
	public String getAgencyName() {
		return this.agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	@Column(name = "AGENCY_PHONE")
	public String getAgencyPhone() {
		return this.agencyPhone;
	}
	public void setAgencyPhone(String agencyPhone) {
		this.agencyPhone = agencyPhone;
	}
	
	@Column(name = "PRODUCT_NAMES")
	public String getProductNames() {
		return this.productNames;
	}
	public void setProductNames(String productNames) {
		this.productNames = productNames;
	}
	
	@Column(name = "CUSTOMER_TYPE")
	public String getCustomerType() {
		return this.customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	@Column(name = "ORDER_POSTSCRIPT")
	public String getOrderPostscript() {
		return this.orderPostscript;
	}
	public void setOrderPostscript(String orderPostscript) {
		this.orderPostscript = orderPostscript;
	}
	
	@Column(name = "PAY_NOTE")
	public String getPayNote() {
		return this.payNote;
	}
	public void setPayNote(String payNote) {
		this.payNote = payNote;
	}
	
	@Column(name = "CACEL_USER_ID")
	public String getCacelUserId() {
		return this.cacelUserId;
	}
	public void setCacelUserId(String cacelUserId) {
		this.cacelUserId = cacelUserId;
	}
	
	@Column(name = "COUPON_NO")
	public String getCouponNo() {
		return this.couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	
	@Column(name = "SCORE_DEDUCTION", nullable = false)
	public Integer getScoreDeduction() {
		return this.scoreDeduction;
	}
	public void setScoreDeduction(Integer scoreDeduction) {
		this.scoreDeduction = scoreDeduction;
	}
	
	@Column(name = "DISCOUNT_DEDUCTION", nullable = false)
	public Integer getDiscountDeduction() {
		return this.discountDeduction;
	}
	public void setDiscountDeduction(Integer discountDeduction) {
		this.discountDeduction = discountDeduction;
	}
	
	@Column(name = "COUPON_TICKET_DEDUCTION", nullable = false)
	public Integer getCouponTicketDeduction() {
		return this.couponTicketDeduction;
	}
	public void setCouponTicketDeduction(Integer couponTicketDeduction) {
		this.couponTicketDeduction = couponTicketDeduction;
	}
	
	@Column(name = "IS_COMMENT")
	public String getIsComment() {
		return this.isComment;
	}
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}
	
}

