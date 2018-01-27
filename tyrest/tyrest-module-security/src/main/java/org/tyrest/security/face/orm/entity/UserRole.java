package org.tyrest.security.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: UserRole.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: UserRole.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:42		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "security_user_role")
public class UserRole implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "SEQUENCE_NBR")
	protected Long sequenceNBR;
	private Long userId;
	private String roleCode;
	private String agencyCode;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;
	private Date recDate;
	private String recUserId;
	private String recStatus;
	private String description;
	
	
	@Column(name = "SEQUENCE_NBR", nullable = false)
	public Long getSequenceNBR() {
		return sequenceNBR;
	}

	public void setSequenceNBR(Long sequenceNBR) {
		this.sequenceNBR = sequenceNBR;
	}
	
	@Column(name = "REC_DATE", nullable = false)
	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	
	@Column(name = "REC_USER_ID", nullable = false)
	public String getRecUserId() {
		return recUserId;
	}
	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}
	
	@Column(name = "REC_STATUS", nullable = false)
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	
	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "ROLE_CODE", nullable = false)
	public String getRoleCode() {
		return this.roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
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

