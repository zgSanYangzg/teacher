package org.tyrest.security.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.security.face.model.RoleModel;
import org.tyrest.security.face.orm.entity.Role;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AgRoleService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgRoleService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
public interface RoleService extends BaseService<RoleModel, Role> {

	/**
	 * 根据角色编号获取角色信息
	 * 
	 * @param agencyCode
	 *            机构代码
	 * @param roleCode
	 *            角色编号
	 */
	public RoleModel getRoleByCode(String agencyCode, String roleCode) throws Exception;

	/**
	 * 查询该机构所有没有禁用的角色
	 * 
	 * @param agencyCode
	 *            机构代码
	 */
	public List<RoleModel> getAllRoles(String agencyCode, String roleCode, String roleName, String lockStatus)
			throws Exception;

	/**
	 * 创建角色
	 * 
	 * @param agencyCode
	 *            机构代码
	 * @param roleModel
	 *            角色实体
	 */
	public RoleModel createRole(String agencyCode, RoleModel roleModel) throws Exception;

	/**
	 * 
	 * 分页查询角色
	 *
	 * @param agencyCode
	 * @param roleCode
	 * @param roleName
	 * @param lockStatus
	 * @param page
	 * @param order
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public Page getByPage(String agencyCode, String roleCode, String roleName, String lockStatus, Page page,
                          String orderBy, String order) throws Exception;

	/**
	 * 
	 * 更新系統角色
	 *
	 * @param roleModel
	 * @return
	 * @throws Exception
	 */
	public RoleModel updateRole(RoleModel roleModel) throws Exception;

	public String[]  deleteRole(String agencyCode, String[] roleCode) throws Exception;
	
	/**
	 * 启用/禁用角色
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	public RoleModel updateLockStatus(String roleCode) throws Exception;

	/**
	 * 判断角色名称是否可用
	 * @param roleName
	 * @param id
	 * @return
	 */
	boolean isRoleNameAvailable(String roleName, Long id) throws Exception;

	/**
	 * 判断角色编号是否可用
	 * @param roleCode
	 * @param id
	 * @return
	 */
	boolean isRoleCodeAvailable(String roleCode, Long id) throws Exception;

}

/*
 * $Log: av-env.bat,v $
 */