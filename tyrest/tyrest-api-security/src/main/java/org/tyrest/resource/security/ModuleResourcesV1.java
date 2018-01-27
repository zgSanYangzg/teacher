package org.tyrest.resource.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.security.validation.BeanValidation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.model.ModuleModel;
import org.tyrest.security.face.service.ModuleService;

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
 *  2016/10/26		yangbochao		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/modules")
@TyrestResource(module = "security", value = "ModuleResourcesV1", description = "业务模块管理")
public class ModuleResourcesV1 extends BaseResources {

    @Autowired
    private ModuleService moduleService;

    @TyrstOperation(name = "getModuleTree", ApiLevel = APILevel.AGENCY, description = "获取业务模块树")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ResponseModel<ModuleModel> getModuleTree(
            @RequestParam(value = "moduleCode", required = true, defaultValue = SecurityConstants.MODULE_ROOT_CODE)String moduleCode)throws Exception{
        return ResponseHelper.buildResponseModel(moduleService.buildModuleTree(moduleCode));
    }

    @TyrstOperation(name = "getAllChildrenModule", ApiLevel = APILevel.AGENCY, description = "根据父业务模块编码获取子业务模块")
    @RequestMapping(value = "/{moduleCode}/children", method = RequestMethod.GET)
    public ResponseModel<List<ModuleModel>> getAllChildrenModule(@PathVariable String moduleCode)throws Exception{
        return ResponseHelper.buildResponseModel(moduleService.getModulesByParentCode(moduleCode));
    }

    @TyrstOperation(name = "getByPort", ApiLevel = APILevel.AGENCY, description = "系统端（SYS|AGENCY）查询模块信息")
    @RequestMapping(value = "/{port}", method = RequestMethod.GET)
    public ResponseModel<List<ModuleModel>> getByPort(@PathVariable String port) throws Exception{
        return ResponseHelper.buildResponseModel(moduleService.getByPort(port));
    }

    @TyrstOperation(name = "getModules", ApiLevel = APILevel.AGENCY, description = "分页查询模块信息")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseModel<Page> getModules(
            @RequestParam(value = "moduleCode", required = false)String moduleCode,
            @RequestParam(value = "moduleName", required = false)String moduleName,
            @RequestParam(value = "parentCode", required = false)String parentCode,
            @RequestParam(value = "port", required = false)String port,
            @RequestParam(value = "lockStatus", required = false)String lockStatus,
            @RequestParam(value = ParamConstants.OFFSET, required = false) int start,
            @RequestParam(value = ParamConstants.LENGTH, required = false) int length,
            @RequestParam(value = ParamConstants.SIDX, required = false) String orderBy,
            @RequestParam(value = ParamConstants.SORT, required = false) String order)throws Exception{
        Page page = new Page(length, start);
        page = moduleService.getByPage(moduleCode, moduleName, parentCode, port, lockStatus, page, orderBy, order);
        return ResponseHelper.buildResponseModel(page);
    }

    @TyrstOperation(name= "createModule", ApiLevel = APILevel.AGENCY, description = "创建模块")
    @RequestMapping(value = "",method = RequestMethod.POST)
    @BeanValidation
    public ResponseModel<ModuleModel> createModule(@RequestBody ModuleModel moduleModel)throws Exception{
        return ResponseHelper.buildResponseModel(moduleService.createModule(moduleModel));
    }

    @TyrstOperation(name = "updateModule", ApiLevel = APILevel.AGENCY, description = "更新模块")
    @RequestMapping(value = "/{moduleCode}", method = RequestMethod.PUT)
    public ResponseModel<ModuleModel> updateModule(@PathVariable String moduleCode,
                                                   @RequestBody ModuleModel moduleModel)throws Exception{
        moduleModel.setModuleCode(moduleCode);
        return ResponseHelper.buildResponseModel(moduleService.updateModule(moduleModel));
    }

    @TyrstOperation(name = "deleteModule", ApiLevel = APILevel.AGENCY, description = "删除模块")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseModel<String> deleteModule(@RequestBody String[] moduleCodes)throws Exception{
        moduleService.deleteModules(moduleCodes);
        return ResponseHelper.buildResponseModel(ch.qos.logback.core.CoreConstants.SUPPRESSED);
    }

    @TyrstOperation(name = "updateLockStatus", ApiLevel = APILevel.AGENCY, description = "修改锁定状态")
    @RequestMapping(value = "/{moduleCode}/lockStatus", method = RequestMethod.PUT)
    public ResponseModel<ModuleModel> updateLockStatus(@PathVariable String moduleCode)throws Exception{
        return ResponseHelper.buildResponseModel(moduleService.updateLockStatus(moduleCode));
    }

    @TyrstOperation(name = "isModuleCodeAvailable", ApiLevel = APILevel.AGENCY, description = "判断模块code是否可用")
    @RequestMapping(value = "/moduleCode/available", method = RequestMethod.GET)
    public ResponseModel<Boolean> isModuleCodeAvailable(@RequestParam String moduleCode,
                  @RequestParam(required = false) Long id)throws Exception{
         return ResponseHelper.buildResponseModel(moduleService.isModuleCodeAvailable(moduleCode,id));
    }
}
