package org.tyrest.product.face.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.BaseSnapshot;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnptProductPrice.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnptProductPrice.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "snpt_product_price")
public class SnptProductPrice extends BaseEntity implements BaseSnapshot
{
	private static final long serialVersionUID = 1L;
	private Long productSeq;
	private String options;
	private Integer price;
	private Date masterRecDate;
	private Long masterSequenceNbr;

	@Column(name = "PRODUCT_SEQ", nullable = false)
	public Long getProductSeq() {
		return this.productSeq;
	}
	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
	}
	
	@Column(name = "OPTIONS")	
	public String getOptions() {
		return options;
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
	
	@Column(name = "MASTER_REC_DATE")
	public Date getMasterRecDate() {
		return this.masterRecDate;
	}
	public void setMasterRecDate(Date masterRecDate) {
		this.masterRecDate = masterRecDate;
	}
	
	@Column(name = "MASTER_SEQUENCE_NBR", nullable = false)
	public Long getMasterSequenceNbr() {
		return this.masterSequenceNbr;
	}
	public void setMasterSequenceNbr(Long masterSequenceNbr) {
		this.masterSequenceNbr = masterSequenceNbr;
	}
	
}

