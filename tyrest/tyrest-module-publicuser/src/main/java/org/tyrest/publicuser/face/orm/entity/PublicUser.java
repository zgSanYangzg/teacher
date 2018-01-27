package org.tyrest.publicuser.face.orm.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.EnableSnapshot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PublicUser.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PublicUser.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-25 14:50:25		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "security_public_user")
@EnableSnapshot(SnptPublicUser.class)
@DynamicUpdate
public class PublicUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String source;
	private String province;
	private String city;
	private String region;
	private String provinceName;
	private String cityName;
	private String regionName;
	private String profession;
	private String email;
	private String homePage;
	private String aboutMe;
	private String idCard;
	private String idCardFrontImg;
	private String idCardBackImg;
	private String isPasswordAuthed;
	private String isEmailAuthed;
	private String isIdCardAuthed;
	private String bizLockStatus;
	private Date bizLockDate;
	private String bizLockUserId;

	@Column(name = "USER_ID", nullable = false,updatable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "SOURCE", nullable = false)
	public String getSource() {
		return this.source;
	}
	public void setSource(String source) {
		this.source = source;
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
	
	@Column(name = "PROVINCE_NAME")
	public String getProvinceName() {
		return this.provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Column(name = "CITY_NAME")
	public String getCityName() {
		return this.cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Column(name = "REGION_NAME")
	public String getRegionName() {
		return this.regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	@Column(name = "PROFESSION")
	public String getProfession() {
		return this.profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "HOME_PAGE")
	public String getHomePage() {
		return this.homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	@Column(name = "ABOUT_ME")
	public String getAboutMe() {
		return this.aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	@Column(name = "ID_CARD")
	public String getIdCard() {
		return this.idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Column(name = "ID_CARD_FRONT_IMG")
	public String getIdCardFrontImg() {
		return this.idCardFrontImg;
	}
	public void setIdCardFrontImg(String idCardFrontImg) {
		this.idCardFrontImg = idCardFrontImg;
	}
	
	@Column(name = "ID_CARD_BACK_IMG")
	public String getIdCardBackImg() {
		return this.idCardBackImg;
	}
	public void setIdCardBackImg(String idCardBackImg) {
		this.idCardBackImg = idCardBackImg;
	}
	
	@Column(name = "IS_PASSWORD_AUTHED", nullable = false)
	public String getIsPasswordAuthed() {
		return this.isPasswordAuthed;
	}
	public void setIsPasswordAuthed(String isPasswordAuthed) {
		this.isPasswordAuthed = isPasswordAuthed;
	}
	
	@Column(name = "IS_EMAIL_AUTHED", nullable = false)
	public String getIsEmailAuthed() {
		return this.isEmailAuthed;
	}
	public void setIsEmailAuthed(String isEmailAuthed) {
		this.isEmailAuthed = isEmailAuthed;
	}
	
	@Column(name = "IS_ID_CARD_AUTHED", nullable = false)
	public String getIsIdCardAuthed() {
		return this.isIdCardAuthed;
	}
	public void setIsIdCardAuthed(String isIdCardAuthed) {
		this.isIdCardAuthed = isIdCardAuthed;
	}
	
	@Column(name = "BIZ_LOCK_STATUS", nullable = false)
	public String getBizLockStatus() {
		return this.bizLockStatus;
	}
	public void setBizLockStatus(String bizLockStatus) {
		this.bizLockStatus = bizLockStatus;
	}
	
	@Column(name = "BIZ_LOCK_DATE")
	public Date getBizLockDate() {
		return this.bizLockDate;
	}
	public void setBizLockDate(Date bizLockDate) {
		this.bizLockDate = bizLockDate;
	}
	
	@Column(name = "BIZ_LOCK_USER_ID")
	public String getBizLockUserId() {
		return this.bizLockUserId;
	}
	public void setBizLockUserId(String bizLockUserId) {
		this.bizLockUserId = bizLockUserId;
	}
	
}

