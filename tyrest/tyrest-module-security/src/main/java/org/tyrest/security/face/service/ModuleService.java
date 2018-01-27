package org.tyrest.security.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.security.face.model.ModuleModel;
import org.tyrest.security.face.orm.entity.Module;

import java.util.List;

/**
 *
 * <pre>
 *
 *  freeapis
 *  File: LoginService.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: LoginService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016/10/26		yangbochao		Initial.
 *
 * </pre>
 */
public interface ModuleService extends BaseService<ModuleModel, Module> {

    /**
     * 获取模块树
     * @return
     * @throws Exception
     */
    ModuleModel buildModuleTree(String moduleCode)throws Exception;

    /**
     * 根据父模块编码获取子模块列表
     * @return
     * @throws Exception
     */
    List<ModuleModel> getModulesByParentCode(String parentCode)throws  Exception;

    /**
     * 根据系统端（SYS|AGENCY）查询模块信息
     * @param port
     * @return
     * @throws Exception
     */
    List<ModuleModel> getByPort(String port) throws Exception;

    /**
     * 分页查询模块信息
     * @param moduleCode
     * @param moduleName
     * @param parentCode
     * @param port
     * @param lockStatus
     * @param page
     * @param orderBy
     * @param order
     * @return
     * @throws Exception
     */
    Page getByPage(String moduleCode, String moduleName, String parentCode, String port,
                   String lockStatus, Page page, String orderBy, String order)throws Exception;

    /**
     * 创建模块
     * @param moduleModel
     * @return
     * @throws Exception
     */
    ModuleModel createModule(ModuleModel moduleModel)throws Exception;

    /**
     * 删除模块
     * @param moduleCode
     * @throws Exception
     */
    void deleteModules(String... moduleCode)throws Exception;

    /**
     * 更新模块
     * @param moduleModel
     * @return
     * @throws Exception
     */
    ModuleModel updateModule(ModuleModel moduleModel)throws Exception;

    /**
     * 启用/禁用
     * @param moduleCode
     * @return
     * @throws Exception
     */
    ModuleModel updateLockStatus(String moduleCode)throws Exception;

    /**
     * 检查模块编码是否可用
     * @param moduleCode
     * @param id
     * @return
     * @throws Exception
     */
    boolean isModuleCodeAvailable(String moduleCode, Long id)throws Exception;
}
