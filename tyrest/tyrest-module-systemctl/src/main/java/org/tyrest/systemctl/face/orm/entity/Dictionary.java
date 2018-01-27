package org.tyrest.systemctl.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: Dictionary.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Dictionary.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:40:30		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_dictionary")
public class Dictionary extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String agencyCode;
	private String dictCode;
	private String dictName;

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
	
	@Column(name = "DICT_NAME", nullable = false)
	public String getDictName() {
		return this.dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
}

