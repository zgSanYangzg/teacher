package org.tyrest.sysorder.face.model;

import org.tyrest.core.mysql.BaseModel;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderProductRelationModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderProductRelationModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class OrderProductRelationModel extends BaseModel {

	
	private static final long serialVersionUID = 8313163591170019515L;
	
	private String orderSn;

	private String entityType;

	private Long productSnpt;

	private Long productPriceSnpt;

	private String productName;

	private Integer count;

	private int productPrice;

	private String isComment;
	
	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Long getProductSnpt() {
		return productSnpt;
	}

	public void setProductSnpt(Long productSnpt) {
		this.productSnpt = productSnpt;
	}

	public Long getProductPriceSnpt() {
		return productPriceSnpt;
	}

	public void setProductPriceSnpt(Long productPriceSnpt) {
		this.productPriceSnpt = productPriceSnpt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}
	
}
