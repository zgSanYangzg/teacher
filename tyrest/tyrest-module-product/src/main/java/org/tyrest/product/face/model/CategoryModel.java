package org.tyrest.product.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.List;

/**
 * <pre>
 *
 *  freeapis
 *  File: CategoryModel.java
 *
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: CategoryModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-21 10:23:38		freeapis		Initial.
 *
 * </pre>
 */
public class CategoryModel extends BaseModel {
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

    private boolean hasChildren;
    private List<CategoryModel> children;

    public String getAgencyCode() {
        return this.agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryKeywords() {
        return this.categoryKeywords;
    }

    public void setCategoryKeywords(String categoryKeywords) {
        this.categoryKeywords = categoryKeywords;
    }

    public String getCategoryPy() {
        return this.categoryPy;
    }

    public void setCategoryPy(String categoryPy) {
        this.categoryPy = categoryPy;
    }

    public String getCategoryType() {
        return this.categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getLevelNum() {
        return this.levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<CategoryModel> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryModel> children) {
        this.children = children;
    }
}