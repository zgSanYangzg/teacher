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
 *  File: DictionaryEntry.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryEntry.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:40:30		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_dictionary_entry")
public class DictionaryEntry extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private String dictCode;
	private String entryKey;
	private String entryValue;
	private Integer orderNum;
	private String lockStatus;
	private String lockUserId;
	private Date lockDate;



	@Column(name = "AGENCY_CODE", nullable = false)
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Column(name = "DICT_CODE", nullable = false)
	public String getDictCode() {
		return this.dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	@Column(name = "ENTRY_KEY", nullable = false)
	public String getEntryKey() {
		return this.entryKey;
	}
	public void setEntryKey(String entryKey) {
		this.entryKey = entryKey;
	}
	
	@Column(name = "ENTRY_VALUE")
	public String getEntryValue() {
		return this.entryValue;
	}
	public void setEntryValue(String entryValue) {
		this.entryValue = entryValue;
	}
	
	@Column(name = "ORDER_NUM")
	public Integer getOrderNum() {
		return this.orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	@Column(name = "LOCK_STATUS", nullable = false)
	public String getLockStatus() {
		return this.lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	@Column(name = "LOCK_USER_ID")
	public String getLockUserId() {
		return this.lockUserId;
	}
	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}
	
	@Column(name = "LOCK_DATE")
	public Date getLockDate() {
		return this.lockDate;
	}
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
}

