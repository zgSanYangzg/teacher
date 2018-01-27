package org.tyrest.product.face.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.EnableSnapshot;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Product.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Product.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "product")
@EnableSnapshot(SnptProduct.class)
public class Product extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private String categoryCode;
	private String productType;
	private String productName;
	private Integer originalPrice;
	private String lockStatus;
	private String lockUserId;
	private Date lockDate;

	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "CATEGORY_CODE")
	public String getCategoryCode() {
		return this.categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Column(name = "PRODUCT_TYPE", nullable = false)
	public String getProductType() {
		return this.productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Column(name = "PRODUCT_NAME", nullable = false)
	public String getProductName() {
		return this.productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name = "ORIGINAL_PRICE")
	public Integer getOriginalPrice() {
		return this.originalPrice;
	}
	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	@Column(name = "LOCK_STATUS", nullable = false)
	public String getLockStatus() {
		return this.lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	@Column(name = "LOCK_USER_ID")
	public String getLockUserId() {
		return this.lockUserId;
	}
	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}
	
	@Column(name = "LOCK_DATE")
	public Date getLockDate() {
		return this.lockDate;
	}
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
}

