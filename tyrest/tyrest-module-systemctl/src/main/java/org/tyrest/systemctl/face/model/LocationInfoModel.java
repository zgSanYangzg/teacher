package org.tyrest.systemctl.face.model;

import java.util.Date;
import java.util.List;

import org.tyrest.core.mysql.BaseModel;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LocationInfoVo.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 	地理位置
 *  Notes:
 *  $Id: LocationInfoVo.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月15日		yangbochao		Initial.
 *
 * </pre>
 */
public class LocationInfoModel extends BaseModel {

	private static final long serialVersionUID = -9029222844632721454L;

	private String pinyinName;

	private String dataAction;

	private String locationName;

	private String locationCode;

	private String locationType;

	private Date lastUpdateTime;

	private Integer dataVersion;

	private String locationCenter;

	private String parentCode;

	private Integer levelNum;

	private Integer orderNum;

	private List<LocationInfoModel> childs;

	// Constructors

	/** default constructor */
	public LocationInfoModel() {
	}

	public String getPinyinName() {
		return pinyinName;
	}

	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}

	public String getDataAction() {
		return dataAction;
	}

	public void setDataAction(String dataAction) {
		this.dataAction = dataAction;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}

	public String getLocationCenter() {
		return locationCenter;
	}

	public void setLocationCenter(String locationCenter) {
		this.locationCenter = locationCenter;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<LocationInfoModel> getChilds() {
		return childs;
	}

	public void setChilds(List<LocationInfoModel> childs) {
		this.childs = childs;
	}

}