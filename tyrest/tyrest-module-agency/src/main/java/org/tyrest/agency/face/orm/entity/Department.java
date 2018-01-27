package org.tyrest.agency.face.orm.entity;

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
 *  File: Department.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Department.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:48:04		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "agency_department")
public class Department extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String departmentCode;
	private String departmentName;
	private String agencyCode;
	private String parentCode;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;

	@Column(name = "DEPARTMENT_CODE")
	public String getDepartmentCode() {
		return this.departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	
	@Column(name = "DEPARTMENT_NAME", nullable = false)
	public String getDepartmentName() {
		return this.departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "PARENT_CODE", nullable = false)
	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

