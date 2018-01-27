package org.tyrest.resource.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.security.face.model.RoleModel;
import org.tyrest.security.face.service.RoleService;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: RoleResourceV1.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RoleResourceV1.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/roles")
@TyrestResource(module = "security",value = "RoleResourceV1", description = "角色管理")
public class RoleResourceV1 extends BaseResources {

	@Autowired
	private RoleService roleService;

	@TyrstOperation(name = "createRole", ApiLevel = APILevel.SUPERADMIN, description = "创建系统角色。")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseModel<RoleModel> createRole(@RequestBody RoleModel roleModel) throws Exception {
		return ResponseHelper.buildResponseModel(roleService.createRole(RequestContext.getAgencyCode(), roleModel));
	}

	@TyrstOperation(name = "updateRole", ApiLevel = APILevel.SUPERADMIN, description = "更新系统角色。")
	@RequestMapping(value = "/{roleCode}", method = RequestMethod.PUT)
	public ResponseModel<RoleModel> updateRole(@PathVariable String roleCode, @RequestBody RoleModel roleModel) throws Exception {
		roleModel.setRoleCode(roleCode);
		return ResponseHelper.buildResponseModel(roleService.updateRole(roleModel));
	}

	@TyrstOperation(name = "updateLockStatus", ApiLevel = APILevel.SUPERADMIN, description = "启用/禁用角色")
	@RequestMapping(value = "/{roleCode}/lockStatus", method = RequestMethod.PUT)
	public ResponseModel<RoleModel> updateLockStatus(@PathVariable String roleCode) throws Exception {
		return ResponseHelper.buildResponseModel(roleService.updateLockStatus(roleCode));
	}

	@TyrstOperation(name = "getRole", ApiLevel = APILevel.SUPERADMIN, description = "通过Code查找角色。")
	@RequestMapping(value = "/{roleCode}", method = RequestMethod.GET)
	public ResponseModel<RoleModel> getRole(@PathVariable String roleCode) throws Exception {
		return ResponseHelper.buildResponseModel(roleService.getRoleByCode(RequestContext.getAgencyCode(), roleCode));
	}

	@TyrstOperation(name = "isRoleCodeAvailable", ApiLevel = APILevel.SUPERADMIN, description = "检查角色编号是否可用。")
	@RequestMapping(value = "/roleCode/available", method = RequestMethod.GET)
	public ResponseModel<Boolean> isRoleCodeAvailable(
			@RequestParam String roleCode,
			@RequestParam(required = false) Long id) throws Exception {

		return ResponseHelper.buildResponseModel(roleService.isRoleCodeAvailable(roleCode, id));
	}

	@TyrstOperation(name = "isRoleNameAvailable", ApiLevel = APILevel.SUPERADMIN, description = "检查角色名称是否可用。")
	@RequestMapping(value = "/roleName/available", method = RequestMethod.GET)
	public ResponseModel<Boolean> isRoleNameAvailable(
			@RequestParam String roleName,
			@RequestParam(required = false) Long id)throws Exception
	{
		return ResponseHelper.buildResponseModel(roleService.isRoleNameAvailable( roleName, id));
	}

	@TyrstOperation(name = "getRoles", ApiLevel = APILevel.SUPERADMIN, description = "条件查询角色列表。")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseModel<List<RoleModel>> getRoles(
			@RequestParam(required = false) String roleCode,
			@RequestParam(required = false) String roleName,
			@RequestParam(required = false) String lockStatus
			) throws Exception
	{
		return ResponseHelper.buildResponseModel(roleService.getAllRoles(RequestContext.getAgencyCode(), roleCode, roleName, lockStatus));
	}

	@TyrstOperation(name = "queryForPage", ApiLevel = APILevel.SUPERADMIN, description = "分頁查询角色列表。")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseModel<Page> queryForPage(
			@RequestParam(value = "roleCode", required = false) String roleCode,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = ParamConstants.LOCKSTATUS, required = false) String lockStatus,
			@RequestParam(value = ParamConstants.ORDERBY, required = false) String orderby,
			@RequestParam(value = ParamConstants.ORDER, required = false) String order,
			@RequestParam(value = ParamConstants.OFFSET,required = true,defaultValue = CoreConstants.COMMON_0) int offset,
			@RequestParam(value = ParamConstants.LENGTH,required = true,defaultValue = CoreConstants.COMMON_10) int limit) throws Exception
	{
		Page page=new Page(limit,offset);
		
		return ResponseHelper.buildResponseModel(roleService.getByPage(RequestContext.getAgencyCode(), roleCode, roleName, lockStatus, page, orderby, order));
	}

	@TyrstOperation(name = "deleteRole",ApiLevel = APILevel.SUPERADMIN, description = "刪除角色")
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseModel<String []> deleteRole(@RequestBody String[] roleCodes)throws Exception
	{
		return ResponseHelper.buildResponseModel(roleService.deleteRole(RequestContext.getAgencyCode(), roleCodes));
	}

}

/*
 * $Log: av-env.bat,v $
 */