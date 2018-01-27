package org.tyrest.security.face.model;

import org.tyrest.core.mysql.BaseModel;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: ModuleOperationModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ModuleOperationModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:41		freeapis		Initial.
 *
 * </pre>
 */
public class ModuleOperationModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private String moduleCode;
	private String funcId;
	private String reqMode;

	public String getModuleCode() {
		return this.moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
	public String getFuncId() {
		return this.funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	public String getReqMode() {
		return this.reqMode;
	}
	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}
	
}

