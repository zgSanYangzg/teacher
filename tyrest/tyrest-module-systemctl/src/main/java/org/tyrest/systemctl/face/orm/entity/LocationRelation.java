package org.tyrest.systemctl.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LocationRelation.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationRelation.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_location_relation")
public class LocationRelation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String locationCode;
	private String refLocationCode;
	private String refLocationName;
	private String thirdPartyName;

	@Column(name = "LOCATION_CODE")
	public String getLocationCode() {
		return this.locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	@Column(name = "REF_LOCATION_CODE")
	public String getRefLocationCode() {
		return this.refLocationCode;
	}
	public void setRefLocationCode(String refLocationCode) {
		this.refLocationCode = refLocationCode;
	}
	
	@Column(name = "REF_LOCATION_NAME")
	public String getRefLocationName() {
		return this.refLocationName;
	}
	public void setRefLocationName(String refLocationName) {
		this.refLocationName = refLocationName;
	}
	
	@Column(name = "THIRD_PARTY_NAME")
	public String getThirdPartyName() {
		return this.thirdPartyName;
	}
	public void setThirdPartyName(String thirdPartyName) {
		this.thirdPartyName = thirdPartyName;
	}
	
}

