package org.tyrest.publicuser.face.orm.entity;

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
 *  File: SnptAddress.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnptAddress.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "snpt_public_user_address")
public class SnptAddress extends BaseEntity implements BaseSnapshot
{
	private static final long serialVersionUID = 1L;
	private String province;
	private String city;
	private String region;
	private String addrDetail;
	private String addrAlias;
	private Long userId;
	private String recieverPhone;
	private String recieverName;
	private String isDefault;
	private String postcode;
	private Long masterSequenceNbr;
	private Date masterRecDate;

	@Column(name = "PROVINCE", nullable = false)
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name = "CITY", nullable = false)
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "REGION", nullable = false)
	public String getRegion() {
		return this.region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	@Column(name = "ADDR_DETAIL", nullable = false)
	public String getAddrDetail() {
		return this.addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	
	@Column(name = "ADDR_ALIAS")
	public String getAddrAlias() {
		return this.addrAlias;
	}
	public void setAddrAlias(String addrAlias) {
		this.addrAlias = addrAlias;
	}
	
	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "RECIEVER_PHONE", nullable = false)
	public String getRecieverPhone() {
		return this.recieverPhone;
	}
	public void setRecieverPhone(String recieverPhone) {
		this.recieverPhone = recieverPhone;
	}
	
	@Column(name = "RECIEVER_NAME", nullable = false)
	public String getRecieverName() {
		return this.recieverName;
	}
	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}
	
	@Column(name = "IS_DEFAULT")
	public String getIsDefault() {
		return this.isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	@Column(name = "POSTCODE")
	public String getPostcode() {
		return this.postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Column(name = "MASTER_SEQUENCE_NBR", nullable = false)
	public Long getMasterSequenceNbr() {
		return this.masterSequenceNbr;
	}
	public void setMasterSequenceNbr(Long masterSequenceNbr) {
		this.masterSequenceNbr = masterSequenceNbr;
	}
	
	@Column(name = "MASTER_REC_DATE", nullable = false)
	public Date getMasterRecDate() {
		return this.masterRecDate;
	}
	public void setMasterRecDate(Date masterRecDate) {
		this.masterRecDate = masterRecDate;
	}
	
}

