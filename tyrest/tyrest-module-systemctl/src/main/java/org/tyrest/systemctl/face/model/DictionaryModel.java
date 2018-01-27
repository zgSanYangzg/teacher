package org.tyrest.systemctl.face.model;

import org.tyrest.core.mysql.BaseModel;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryModel.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月12日		framework		Initial.
 *
 * </pre>
 */
public class DictionaryModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	
	private String dictCode;
	private String dictName;
	private String dictAlias;
	private String dictDesc;
	private String buType;
	private String agencyCode;

	public String getDictCode() {
		return this.dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	public String getDictName() {
		return this.dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	public String getDictAlias() {
		return this.dictAlias;
	}
	public void setDictAlias(String dictAlias) {
		this.dictAlias = dictAlias;
	}
	
	public String getDictDesc() {
		return this.dictDesc;
	}
	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}
	
	public String getBuType() {
		return this.buType;
	}
	public void setBuType(String buType) {
		this.buType = buType;
	}
	
	public String getAgencyCode() {
		return this.agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
}

