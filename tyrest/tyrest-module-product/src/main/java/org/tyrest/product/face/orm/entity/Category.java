package org.tyrest.product.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: Category.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Category.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-21 10:23:38		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "product_category")
public class Category extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private String categoryCode;
	private String categoryName;
	private String categoryKeywords;
	private String categoryPy;
	private String categoryType;
	private String parentCode;
	private Integer levelNum;
	private Integer orderNum;

	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "CATEGORY_CODE", unique = true, nullable = false)
	public String getCategoryCode() {
		return this.categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Column(name = "CATEGORY_NAME", nullable = false)
	public String getCategoryName() {
		return this.categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name = "CATEGORY_KEYWORDS")
	public String getCategoryKeywords() {
		return this.categoryKeywords;
	}
	public void setCategoryKeywords(String categoryKeywords) {
		this.categoryKeywords = categoryKeywords;
	}
	
	@Column(name = "CATEGORY_PY")
	public String getCategoryPy() {
		return this.categoryPy;
	}
	public void setCategoryPy(String categoryPy) {
		this.categoryPy = categoryPy;
	}
	
	@Column(name = "CATEGORY_TYPE")
	public String getCategoryType() {
		return this.categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	@Column(name = "PARENT_CODE", nullable = false)
	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	@Column(name = "LEVEL_NUM", nullable = false)
	public Integer getLevelNum() {
		return this.levelNum;
	}
	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}
	
	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return this.orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}

