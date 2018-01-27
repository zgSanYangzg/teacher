package org.tyrest.publicuser.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PublicUserModel.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PublicUserModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-25 14:50:25		freeapis		Initial.
 *
 * </pre>
 */
public class PublicUserModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private String source;
	private Long userId;
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
	//主体信息
	private String agencyCode;
	private Integer userType;
	private String userName;
	private String nickName;
	private String realName;
	private String gender;
	private Date birthDate;
	private String avatar;
	private Date registerDate;
	private String mobile;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;
	private String password;
	//默认地址信息
	private String defaultAddress;

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockUserId() {
		return lockUserId;
	}

	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}

	public String getSource() {
		return this.source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getRegion() {
		return this.region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getProvinceName() {
		return this.provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getCityName() {
		return this.cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getRegionName() {
		return this.regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public String getProfession() {
		return this.profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHomePage() {
		return this.homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	public String getAboutMe() {
		return this.aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	public String getIdCard() {
		return this.idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public String getIdCardFrontImg() {
		return this.idCardFrontImg;
	}
	public void setIdCardFrontImg(String idCardFrontImg) {
		this.idCardFrontImg = idCardFrontImg;
	}
	
	public String getIdCardBackImg() {
		return this.idCardBackImg;
	}
	public void setIdCardBackImg(String idCardBackImg) {
		this.idCardBackImg = idCardBackImg;
	}
	
	public String getIsPasswordAuthed() {
		return this.isPasswordAuthed;
	}
	public void setIsPasswordAuthed(String isPasswordAuthed) {
		this.isPasswordAuthed = isPasswordAuthed;
	}
	
	public String getIsEmailAuthed() {
		return this.isEmailAuthed;
	}
	public void setIsEmailAuthed(String isEmailAuthed) {
		this.isEmailAuthed = isEmailAuthed;
	}
	
	public String getIsIdCardAuthed() {
		return this.isIdCardAuthed;
	}
	public void setIsIdCardAuthed(String isIdCardAuthed) {
		this.isIdCardAuthed = isIdCardAuthed;
	}
	
	public String getBizLockStatus() {
		return this.bizLockStatus;
	}
	public void setBizLockStatus(String bizLockStatus) {
		this.bizLockStatus = bizLockStatus;
	}
	
	public Date getBizLockDate() {
		return this.bizLockDate;
	}
	public void setBizLockDate(Date bizLockDate) {
		this.bizLockDate = bizLockDate;
	}
	
	public String getBizLockUserId() {
		return this.bizLockUserId;
	}
	public void setBizLockUserId(String bizLockUserId) {
		this.bizLockUserId = bizLockUserId;
	}
	
}

