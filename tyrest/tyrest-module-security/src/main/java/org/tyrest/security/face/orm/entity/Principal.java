package org.tyrest.security.face.orm.entity;

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
 *  File: Principal.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Principal.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:42		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "security_principal")
@DynamicUpdate
@EnableSnapshot(SnptPrincipal.class)
public class Principal extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private Integer userType;
	private Long userId;
	private String userName;
	private String nickName;
	private String realName;
	private String gender;
	private Date birthDate;
	private String avatar;
	private Date registerDate;
	private String password;
	private String salt;
	private String mobile;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;

	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "USER_TYPE", nullable = false)
	public Integer getUserType() {
		return this.userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@Column(name = "USER_ID", nullable = false,updatable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "NICK_NAME")
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Column(name = "REAL_NAME")
	public String getRealName() {
		return this.realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Column(name = "GENDER")
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "BIRTH_DATE")
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Column(name = "AVATAR")
	public String getAvatar() {
		return this.avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name = "REGISTER_DATE", nullable = false)
	public Date getRegisterDate() {
		return this.registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "SALT", nullable = false)
	public String getSalt() {
		return this.salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
}

