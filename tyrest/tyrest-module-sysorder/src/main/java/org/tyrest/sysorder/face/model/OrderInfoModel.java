package org.tyrest.sysorder.face.model;

import java.util.Date;
import java.util.Map;

import org.tyrest.core.mysql.BaseModel;
import org.tyrest.systrade.face.model.TransactionsBillModel;


/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderInfoModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderInfoModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class OrderInfoModel extends BaseModel
{
	
	private static final long serialVersionUID = 6649874495231081399L;

	/**
	 *订单编号
	 */
	private String orderSn;

	/**
	 * 账单编号
	 */
	private String billNo;

	/**
	 * 订单总额
	 */
	private int amount;

	/**
	 * 商品总额
	 */
	private int productAmount;

	/**
	 * 优惠抵扣
	 */
	private int couponDeduction;

	/**
	 * 优惠劵编号
	 */
	private String couponNo;
	
	/**
	 * 优惠劵抵扣
	 */
	private int couponTicketDeduction;
	
	/**
	 * 积分抵扣
	 */
	private int scoreDeduction;
	
	/**
	 * 打折抵扣
	 */
	private int discountDeduction;


	private String orderType;

	private String orderStatus;

	private Date createTime;

	private Date payTime;

	private String payMethod;

	private String payStatus;

	private Long userId;

	private String agencyCode;
	
	private String source;

	private String userName;

	private String agencyName;

	private String agencyPhone;

	private String productNames;

	private String isComment;
	
	private String orderPostscript;

	private String customerType;

	private String payNote;

	private String cacelUserId;

	private Long buIndexPuuser;

	private Long buIndexAgency;

	private Map<String,String> extra;//支付渠道附加参数
	
	protected Map<String,Object> tradeParams;
	
	private String payPassword;//支付密码
	
	private TransactionsBillModel billModel;//交易账单

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public int getCouponDeduction() {
		return couponDeduction;
	}

	public void setCouponDeduction(int couponDeduction) {
		this.couponDeduction = couponDeduction;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public int getCouponTicketDeduction() {
		return couponTicketDeduction;
	}

	public void setCouponTicketDeduction(int couponTicketDeduction) {
		this.couponTicketDeduction = couponTicketDeduction;
	}

	public int getScoreDeduction() {
		return scoreDeduction;
	}

	public void setScoreDeduction(int scoreDeduction) {
		this.scoreDeduction = scoreDeduction;
	}

	public int getDiscountDeduction() {
		return discountDeduction;
	}

	public void setDiscountDeduction(int discountDeduction) {
		this.discountDeduction = discountDeduction;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyPhone() {
		return agencyPhone;
	}

	public void setAgencyPhone(String agencyPhone) {
		this.agencyPhone = agencyPhone;
	}

	public String getProductNames() {
		return productNames;
	}

	public void setProductNames(String productNames) {
		this.productNames = productNames;
	}

	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	public String getOrderPostscript() {
		return orderPostscript;
	}

	public void setOrderPostscript(String orderPostscript) {
		this.orderPostscript = orderPostscript;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getPayNote() {
		return payNote;
	}

	public void setPayNote(String payNote) {
		this.payNote = payNote;
	}

	public String getCacelUserId() {
		return cacelUserId;
	}

	public void setCacelUserId(String cacelUserId) {
		this.cacelUserId = cacelUserId;
	}

	public Long getBuIndexPuuser() {
		return buIndexPuuser;
	}

	public void setBuIndexPuuser(Long buIndexPuuser) {
		this.buIndexPuuser = buIndexPuuser;
	}

	public Long getBuIndexAgency() {
		return buIndexAgency;
	}

	public void setBuIndexAgency(Long buIndexAgency) {
		this.buIndexAgency = buIndexAgency;
	}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}

	public Map<String, Object> getTradeParams() {
		return tradeParams;
	}

	public void setTradeParams(Map<String, Object> tradeParams) {
		this.tradeParams = tradeParams;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public TransactionsBillModel getBillModel() {
		return billModel;
	}

	public void setBillModel(TransactionsBillModel billModel) {
		this.billModel = billModel;
	}
}

