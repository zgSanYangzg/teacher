package org.tyrest.security.face.service;


import org.tyrest.security.face.model.ModuleModel;
import org.tyrest.security.face.model.RoleSecurityModel;

import java.util.List;
import java.util.Set;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PrivilegeOperationService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PrivilegeOperationService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月21日		yangbochao		Initial.
 *
 * </pre>
 */
public interface PermissionService{

	/**
	 * 获取当前登录用户的模块树
	 * @return
	 * @throws Exception 
	 */
	ModuleModel getCurrentUserModuleTree() throws Exception;

	/**
	 * 获取系统所有的资源模块权限树
	 * @return
	 * @throws Exception 
	 */
	List<ModuleModel> getSysPrivilegeModuleTree() throws Exception;

	/**
	 * 获取指定角色的资源模块树
	 * @return
	 * @throws Exception 
	 */
	List<Object> getRolePrivilegeModuleTree(String roleCode, String agencyCode) throws Exception;

	/**
	 * 创建角色权限信息
	 * @param rolePrivilegeModel
	 * @return
	 * @throws Exception 
	 */
	RoleSecurityModel createPermission(RoleSecurityModel rolePrivilegeModel) throws Exception;

	/**
	 * 获取当前用户指定模块的所有fid
	 * @param moduleCode
	 * @return
	 * @throws Exception 
	 */
	Set<String> getCurrentUserModuleFids(String moduleCode) throws Exception;


}

/*
 * $Log: av-env.bat,v $
 */