package org.tyrest.agency.face.orm.entity;

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
 *  File: SnptAgency.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnptAgency.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "snpt_agency")
public class SnptAgency extends BaseEntity implements BaseSnapshot
{
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private String agencyName;
	private String agencyAlias;
	private String agencyDesc;
	private String avatar;
	private String banner;
	private String agencyLevel;
	private String parentCode;
	private String coordinate;
	private String country;
	private String province;
	private String city;
	private String region;
	private String addressPrefix;
	private String agencyAddress;
	private String phone1;
	private String phone2;
	private String phone3;
	private String agencyFax;
	private String legalPerson;
	private String legalMobil;
	private String businessHoursStart;
	private String businessHoursEnd;
	private String agencyLicense;
	private String adminId;
	private String adminPhone;
	private String qqNo;
	private String email;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;
	private String lbsId;
	private String isApproved;
	private String isPublished;
	private String isLoginable;
	private Long masterSequenceNbr;
	private Date masterRecDate;

	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "AGENCY_NAME", nullable = false)
	public String getAgencyName() {
		return this.agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	@Column(name = "AGENCY_ALIAS")
	public String getAgencyAlias() {
		return this.agencyAlias;
	}
	public void setAgencyAlias(String agencyAlias) {
		this.agencyAlias = agencyAlias;
	}
	
	@Column(name = "AGENCY_DESC")
	public String getAgencyDesc() {
		return this.agencyDesc;
	}
	public void setAgencyDesc(String agencyDesc) {
		this.agencyDesc = agencyDesc;
	}
	
	@Column(name = "AVATAR")
	public String getAvatar() {
		return this.avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name = "BANNER")
	public String getBanner() {
		return this.banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	@Column(name = "AGENCY_LEVEL")
	public String getAgencyLevel() {
		return this.agencyLevel;
	}
	public void setAgencyLevel(String agencyLevel) {
		this.agencyLevel = agencyLevel;
	}
	
	@Column(name = "PARENT_CODE")
	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	@Column(name = "COORDINATE")
	public String getCoordinate() {
		return this.coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	
	@Column(name = "COUNTRY")
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "REGION")
	public String getRegion() {
		return this.region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	@Column(name = "ADDRESS_PREFIX")
	public String getAddressPrefix() {
		return this.addressPrefix;
	}
	public void setAddressPrefix(String addressPrefix) {
		this.addressPrefix = addressPrefix;
	}
	
	@Column(name = "AGENCY_ADDRESS")
	public String getAgencyAddress() {
		return this.agencyAddress;
	}
	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}
	
	@Column(name = "PHONE1")
	public String getPhone1() {
		return this.phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	
	@Column(name = "PHONE2")
	public String getPhone2() {
		return this.phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	@Column(name = "PHONE3")
	public String getPhone3() {
		return this.phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	
	@Column(name = "AGENCY_FAX")
	public String getAgencyFax() {
		return this.agencyFax;
	}
	public void setAgencyFax(String agencyFax) {
		this.agencyFax = agencyFax;
	}
	
	@Column(name = "LEGAL_PERSON")
	public String getLegalPerson() {
		return this.legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	@Column(name = "LEGAL_MOBIL")
	public String getLegalMobil() {
		return this.legalMobil;
	}
	public void setLegalMobil(String legalMobil) {
		this.legalMobil = legalMobil;
	}
	
	@Column(name = "BUSINESS_HOURS_START")
	public String getBusinessHoursStart() {
		return this.businessHoursStart;
	}
	public void setBusinessHoursStart(String businessHoursStart) {
		this.businessHoursStart = businessHoursStart;
	}
	
	@Column(name = "BUSINESS_HOURS_END")
	public String getBusinessHoursEnd() {
		return this.businessHoursEnd;
	}
	public void setBusinessHoursEnd(String businessHoursEnd) {
		this.businessHoursEnd = businessHoursEnd;
	}
	
	@Column(name = "AGENCY_LICENSE")
	public String getAgencyLicense() {
		return this.agencyLicense;
	}
	public void setAgencyLicense(String agencyLicense) {
		this.agencyLicense = agencyLicense;
	}
	
	@Column(name = "ADMIN_ID")
	public String getAdminId() {
		return this.adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	@Column(name = "ADMIN_PHONE")
	public String getAdminPhone() {
		return this.adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	
	@Column(name = "QQ_NO")
	public String getQqNo() {
		return this.qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "LOCK_STATUS", nullable = false)
	public String getLockStatus() {
		return this.lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	@Column(name = "LOCK_DATE")
	public Date getLockDate() {
		return this.lockDate;
	}
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
	@Column(name = "LOCK_USER_ID")
	public String getLockUserId() {
		return this.lockUserId;
	}
	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}
	
	@Column(name = "LBS_ID")
	public String getLbsId() {
		return this.lbsId;
	}
	public void setLbsId(String lbsId) {
		this.lbsId = lbsId;
	}
	
	@Column(name = "IS_APPROVED")
	public String getIsApproved() {
		return this.isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	@Column(name = "IS_PUBLISHED")
	public String getIsPublished() {
		return this.isPublished;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	
	@Column(name = "IS_LOGINABLE")
	public String getIsLoginable() {
		return this.isLoginable;
	}
	public void setIsLoginable(String isLoginable) {
		this.isLoginable = isLoginable;
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

