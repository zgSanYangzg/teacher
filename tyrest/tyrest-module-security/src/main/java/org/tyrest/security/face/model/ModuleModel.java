package org.tyrest.security.face.model;

import org.tyrest.core.mysql.BaseModel;
import org.tyrest.core.foundation.utils.ValidationUtil;

import java.util.Date;
import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: ModuleModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ModuleModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 14:25:41		freeapis		Initial.
 *
 * </pre>
 */
public class ModuleModel extends BaseModel implements Comparable<ModuleModel>{
	private static final long serialVersionUID = 1L;
	private String moduleCode;
	private String moduleName;
	private String parentCode;
	private String direction;
	private String port;
	private Integer orderNum;
	private String lockStatus;
	private Date lockDate;
	private String lockUserId;
	private boolean hasChildren;

	List<ModuleModel> child;
	List<OperationModel> operations;

	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Date getLockDate() {
		return this.lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockUserId() {
		return this.lockUserId;
	}

	public void setLockUserId(String lockUserId) {
		this.lockUserId = lockUserId;
	}

	public List<ModuleModel> getChild() {
		return child;
	}

	public void setChild(List<ModuleModel> child) {
		this.child = child;
	}

	public List<OperationModel> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationModel> operations) {
		this.operations = operations;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Override
	public int compareTo(ModuleModel o) {
		int rvalue = 0;
		if(!ValidationUtil.isEmpty(o))
		{
			rvalue = this.getOrderNum() - o.getOrderNum();
		}
		return rvalue;
	}

}
