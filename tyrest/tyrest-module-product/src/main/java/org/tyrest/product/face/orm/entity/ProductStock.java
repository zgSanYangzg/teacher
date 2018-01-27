package org.tyrest.product.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductStock.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductStock.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "product_stock")
public class ProductStock extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long productSeq;
	private Integer stockCount;
	private Integer salesVolume;
	private Integer totalInflow;
	private Long updateVersion;

	@Column(name = "PRODUCT_SEQ", nullable = false)
	public Long getProductSeq() {
		return this.productSeq;
	}
	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
	}
	
	@Column(name = "STOCK_COUNT", nullable = false)
	public Integer getStockCount() {
		return this.stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	
	@Column(name = "SALES_VOLUME")
	public Integer getSalesVolume() {
		return this.salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	@Column(name = "TOTAL_INFLOW")
	public Integer getTotalInflow() {
		return this.totalInflow;
	}
	public void setTotalInflow(Integer totalInflow) {
		this.totalInflow = totalInflow;
	}
	
	@Column(name = "UPDATE_VERSION", nullable = false)
	public Long getUpdateVersion() {
		return this.updateVersion;
	}
	public void setUpdateVersion(Long updateVersion) {
		this.updateVersion = updateVersion;
	}
	
}

