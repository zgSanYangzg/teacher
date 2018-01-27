package org.tyrest.systemctl.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LocationInfo.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationInfo.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:40:30		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_location_info")
public class LocationInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
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

	@Column(name = "PINYIN_NAME", nullable = false)
	public String getPinyinName() {
		return this.pinyinName;
	}
	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}
	
	@Column(name = "DATA_ACTION", nullable = false)
	public String getDataAction() {
		return this.dataAction;
	}
	public void setDataAction(String dataAction) {
		this.dataAction = dataAction;
	}
	
	@Column(name = "LOCATION_NAME", nullable = false)
	public String getLocationName() {
		return this.locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	@Column(name = "LOCATION_CODE", unique = true, nullable = false)
	public String getLocationCode() {
		return this.locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	@Column(name = "LOCATION_TYPE", nullable = false)
	public String getLocationType() {
		return this.locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	@Column(name = "LAST_UPDATE_TIME", nullable = false)
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Column(name = "DATA_VERSION", nullable = false)
	public Integer getDataVersion() {
		return this.dataVersion;
	}
	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}
	
	@Column(name = "LOCATION_CENTER")
	public String getLocationCenter() {
		return this.locationCenter;
	}
	public void setLocationCenter(String locationCenter) {
		this.locationCenter = locationCenter;
	}
	
	@Column(name = "PARENT_CODE")
	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	@Column(name = "LEVEL_NUM")
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

