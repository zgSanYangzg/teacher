package org.tyrest.resource.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.security.face.model.ModuleModel;
import org.tyrest.security.face.model.RoleSecurityModel;
import org.tyrest.security.face.service.PermissionService;

import java.util.List;
import java.util.Set;

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
@RequestMapping(value = "/1/permissions")
@TyrestResource(module = "security",value = "PermissionResourceV1", description = "系统权限管理")
public class PermissionResourceV1 extends BaseResources {
	
	@Autowired
	PermissionService permissionService;
	
	@TyrstOperation(name = "getUserModuleTree", ApiLevel = APILevel.AGENCY, description = "获取用户模块树")
	@RequestMapping(value = "/userModuleTree", method = RequestMethod.GET)
	public ResponseModel<ModuleModel> getUserModuleTree() throws Exception {
		ModuleModel moduleModel = permissionService.getCurrentUserModuleTree();
		return ResponseHelper.buildResponseModel(moduleModel);
	}
	
	@TyrstOperation(name = "getSysPrivilegeTree", ApiLevel = APILevel.AGENCY, description = "获取系统所有模块资源权限树")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseModel<List<ModuleModel>> getSysPrivilegeTree() throws Exception {
		List<ModuleModel> moduleModelList = permissionService.getSysPrivilegeModuleTree();
		return ResponseHelper.buildResponseModel(moduleModelList);
	}
	
	@TyrstOperation(name = "getRolePrivilegeModuleTree", ApiLevel = APILevel.AGENCY, description = "获取系统角色模块资源权限树")
	@RequestMapping(value = "/role/{roleCode}", method = RequestMethod.GET)
	public ResponseModel<List<Object>> getRolePrivilegeModuleTree(@PathVariable String roleCode) throws Exception {
		List<Object> moduleFidList = permissionService.getRolePrivilegeModuleTree(roleCode, RequestContext.getAgencyCode());
		return ResponseHelper.buildResponseModel(moduleFidList);
	}
	
	@TyrstOperation(name = "createOrUpdatePermission", ApiLevel = APILevel.AGENCY, description = "创建角色权限信息")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseModel<RoleSecurityModel> createOrUpdatePermission(@RequestBody RoleSecurityModel rolePrivilegeModel) throws Exception {
		rolePrivilegeModel = permissionService.createPermission(rolePrivilegeModel);
		return ResponseHelper.buildResponseModel(rolePrivilegeModel);
	}

	@TyrstOperation(name = "moduleCode", ApiLevel = APILevel.AGENCY, description = "获取当前登录用户指定模块的FunId")
	@RequestMapping(value = "/module/{moduleCode}/operations", method = RequestMethod.GET)
	public ResponseModel<Set<String>> getCurrentUserModuleFids(@PathVariable String moduleCode) throws Exception {
		Set<String> fids = permissionService.getCurrentUserModuleFids(moduleCode);
		return ResponseHelper.buildResponseModel(fids);
	}
		
}

/*
 * $Log: av-env.bat,v $
 */