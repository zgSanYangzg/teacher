package org.tyrest.sysorder.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderProductRelation.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderProductRelation.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "order_product_relation")
public class OrderProductRelation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String orderSn;
	private String productType;
	private String productName;
	private Integer count;
	private Integer productPrice;
	private String isComment;

	@Column(name = "ORDER_SN", unique = true, nullable = false)
	public String getOrderSn() {
		return this.orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	@Column(name = "PRODUCT_TYPE", nullable = false)
	public String getProductType() {
		return this.productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Column(name = "PRODUCT_NAME")
	public String getProductName() {
		return this.productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name = "COUNT", nullable = false)
	public Integer getCount() {
		return this.count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Column(name = "PRODUCT_PRICE", nullable = false)
	public Integer getProductPrice() {
		return this.productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	@Column(name = "IS_COMMENT")
	public String getIsComment() {
		return this.isComment;
	}
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}
	
}

