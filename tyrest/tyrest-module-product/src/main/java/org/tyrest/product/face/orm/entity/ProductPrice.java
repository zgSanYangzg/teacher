package org.tyrest.product.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.EnableSnapshot;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductPrice.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductPrice.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "product_price")
@EnableSnapshot(SnptProductPrice.class)
public class ProductPrice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long productSeq;
	private String options;
	private Integer price;

	@Column(name = "PRODUCT_SEQ", nullable = false)
	public Long getProductSeq() {
		return this.productSeq;
	}
	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
	}
	
	@Column(name = "OPTIONS")
	public String getOptions() {
		return this.options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	@Column(name = "PRICE", nullable = false)
	public Integer getPrice() {
		return this.price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
}

