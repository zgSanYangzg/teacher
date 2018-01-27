package org.tyrest.security.service;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.security.face.orm.dao.*;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.orm.entity.*;
import org.tyrest.security.face.model.ModuleModel;
import org.tyrest.security.face.model.ModuleOperationModel;
import org.tyrest.security.face.model.OperationModel;
import org.tyrest.security.face.model.RoleSecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.security.face.service.PermissionService;

import java.util.*;

/**
 * <pre>
 *
 *  freeapis
 *  File: PrivilegeServiceImpl.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: PrivilegeServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    ModuleRoleDAO moduleRoleDAO;

    @Autowired
    PermissionDAO permissionDAO;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    ModuleDAO moduleDAO;

    @Autowired
    OperationDAO operationDAO;

    @Autowired
    ModuleOperationDAO moduleOperationDAO;

    private static final String PRIVILEGE_TREE = "PRIVILEGE_TREE";


    @Override
    public ModuleModel getCurrentUserModuleTree() throws Exception {
        //数据验证
        if (ValidationUtil.isEmpty(RequestContext.getAgencyCode())
                || ValidationUtil.isEmpty(RequestContext.getExeUserId())) {
            throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
        }
        Map<Object, ModuleModel> moduleMap = new HashMap<Object, ModuleModel>();
        //如果是super登录获取系统所有模块
        if(CoreConstants.SUPER_USER_ID.toString().equals(RequestContext.getExeUserId())
                && CoreConstants.CODE_SUPER_ADMIN.equals(RequestContext.getAgencyCode())){
            List<Module> modules = moduleDAO.findByPort(SecurityConstants.MODULE_PORT_SYS);
            List<ModuleModel> moduleModels = Bean.toModels(modules,ModuleModel.class);
            moduleMap = Bean.listToMap(moduleModels,"moduleCode",ModuleModel.class);
        }else{
            //获取用户所有角色对应的模块信息
            List<UserRole> userRoles = userRoleDAO.findByLockStatus(RequestContext.getAgencyCode(), Long.parseLong(RequestContext.getExeUserId()), CoreConstants.COMMON_N);
            for (UserRole userRole : userRoles) {
                List<ModuleRole> moduleRoles = moduleRoleDAO.findByRoleCode(userRole.getRoleCode(), userRole.getAgencyCode());
                if (ValidationUtil.isEmpty(moduleRoles)) {
                    continue;
                }
                for (ModuleRole moduleRole : moduleRoles) {
                    Module module = moduleDAO.findByModuleCode(moduleRole.getModuleCode());
                    ModuleModel moduleModel = Bean.copyExistPropertis(module, new ModuleModel());
                    moduleMap.put(moduleRole.getModuleCode(), moduleModel);
                }
            }
        }
        //查询模块信息
        createModuleTree(moduleMap);
        //查询完之后进行排序
        ModuleModel rootModuleModel = moduleMap.get(SecurityConstants.MODULE_ROOT_CODE);
        sortModuleTree(rootModuleModel);
        return rootModuleModel;
    }

    /**
     * 递归对树节点的每一层进行排序
     *
     * @param moduleModel
     */
    private void sortModuleTree(ModuleModel moduleModel) {
        List<ModuleModel> child = moduleModel.getChild();
        if (ValidationUtil.isEmpty(child)) {
            return;
        }
        Collections.sort(child);
        for (ModuleModel module : child) {
            sortModuleTree(module);
        }
    }

    /**
     * 构造模块树
     *
     * @param moduleMap
     * @throws Exception
     */
    private void createModuleTree(Map<Object, ModuleModel> moduleMap) throws Exception {
        Module root = moduleDAO.findByModuleCode(SecurityConstants.MODULE_ROOT_CODE);
        ModuleModel rootModel = Bean.copyExistPropertis(root, new ModuleModel());
        moduleMap.put(SecurityConstants.MODULE_ROOT_CODE, rootModel);
        for (Object roleModuleCode : moduleMap.keySet()) {
            String parentCode = moduleMap.get(roleModuleCode).getParentCode();
            if (SecurityConstants.MODULE_ROOT_PCODE.equals(parentCode)) {
                continue;
            }
            if(ValidationUtil.isEmpty(moduleMap.get(parentCode))){
                throw new DataNotFoundException("父模块Code【"+parentCode+"】不存在.");
            }
            if (ValidationUtil.isEmpty(moduleMap.get(parentCode).getChild())) {
                moduleMap.get(parentCode).setChild(new ArrayList<ModuleModel>());
            }
            moduleMap.get(parentCode).getChild().add(moduleMap.get(roleModuleCode));
        }
    }

    @Override
    public List<ModuleModel> getSysPrivilegeModuleTree() throws Exception {
        //获取系统所属端系统端还是商家端（SYS|AGENCY）
        String port = SecurityConstants.MODULE_PORT_AGENCY;
        if (CoreConstants.CODE_SUPER_ADMIN.equals(RequestContext.getAgencyCode())) {
            port = SecurityConstants.MODULE_PORT_SYS;
        }
        List<ModuleModel> moduleTree = Redis.get(PRIVILEGE_TREE, port);
        if (!ValidationUtil.isEmpty(moduleTree)) {
            return moduleTree;
        }
        List<Module> modules = moduleDAO.findByPort(port);
        if (ValidationUtil.isEmpty(modules)) {
            return null;
        }
        List<ModuleModel> moduleModels = new ArrayList<ModuleModel>();
        for (Module module : modules) {
            moduleModels.add(Bean.copyExistPropertis(module, new ModuleModel()));
        }
        //获取叶子节点并设置模块权限信息
        for (ModuleModel moduleModel : moduleModels) {
            if (!ValidationUtil.isEmpty(moduleModel.getChild())) {
                continue;
            }
            List<ModuleOperation> moduleResources = moduleOperationDAO.findByModuleCode(moduleModel.getModuleCode());
            for (ModuleOperation moduleResource : moduleResources) {
                Operation resource = operationDAO.findByFid(moduleResource.getFuncId());
                if (ValidationUtil.isEmpty(resource)) {
                    continue;
                }
                if (ValidationUtil.isEmpty(moduleModel.getOperations())) {
                    moduleModel.setOperations(new ArrayList<OperationModel>());
                }
                moduleModel.getOperations().add(Bean.copyExistPropertis(resource, new OperationModel()));
            }
        }


        Map<Object, ModuleModel> map = Bean.listToMap(moduleModels, "moduleCode", ModuleModel.class);
        createModuleTree(map);
        //查询完之后进行排序
        ModuleModel rootModuleModel = map.get(SecurityConstants.MODULE_ROOT_CODE);
        sortModuleTree(rootModuleModel);
        //返回权限数据
        List<ModuleModel> returnModelOperationList = new ArrayList<ModuleModel>();
        recursionModelOperation(rootModuleModel,returnModelOperationList);
        //将模块权限树存放到缓存中
        Redis.set(returnModelOperationList, PRIVILEGE_TREE, port);
        return returnModelOperationList;
    }

    private void recursionModelOperation(ModuleModel moduleModel,List<ModuleModel> returnModelOperationList) {
        List<ModuleModel> child = moduleModel.getChild();
        if (ValidationUtil.isEmpty(child)) {
            return;
        }
        for (ModuleModel module : child) {
            if(!ValidationUtil.isEmpty(module.getOperations())){
                returnModelOperationList.add(module);
            }
            recursionModelOperation(module,returnModelOperationList);
        }
    }

    @Override
    public List<Object> getRolePrivilegeModuleTree(String roleCode, String agencyCode) throws Exception {
        //数据验证
        if (ValidationUtil.isEmpty(roleCode)
                || ValidationUtil.isEmpty(agencyCode)) {
            throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
        }
        //组装数据对应的权限的数据
        List<ModuleRole> moduleRoles = moduleRoleDAO.findByRoleCode(roleCode, agencyCode);
        List<Object> returnList = null;
        if (ValidationUtil.isEmpty(moduleRoles)) {
            return returnList;
        }
        returnList = new ArrayList<Object>();
        for (ModuleRole moduleRole : moduleRoles) {
            List<Permission> permissions = permissionDAO.findByModuleCode(moduleRole.getAgencyCode(), moduleRole.getRoleCode(), moduleRole.getModuleCode());
            if (ValidationUtil.isEmpty(permissions)) {
                continue;
            }
            for (Permission permission : permissions) {
                List<String> fidObj = new ArrayList<String>();
                fidObj.add(permission.getModuleCode());
                fidObj.add(permission.getFuncId());
                returnList.add(fidObj);
            }
        }
        return returnList;
    }

    @Override
    public Set<String> getCurrentUserModuleFids(String moduleCode) throws Exception {
        //数据验证
        if (ValidationUtil.isEmpty(moduleCode)
                || ValidationUtil.isEmpty(RequestContext.getAgencyCode())
                || ValidationUtil.isEmpty(RequestContext.getExeUserId())) {
            throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
        }
        Set<String> fids = new HashSet<String>();
        //如果是super登录获取该模块的所有fid
        if(CoreConstants.SUPER_USER_ID.toString().equals(RequestContext.getExeUserId())
                && CoreConstants.CODE_SUPER_ADMIN.equals(RequestContext.getAgencyCode())){
            List<ModuleOperation> moduleOperations = moduleOperationDAO.findByModuleCode(moduleCode);
            for(ModuleOperation moduleOperation : moduleOperations){
                fids.add(moduleOperation.getFuncId());
            }
        }else{
            //如果是普通用户获取用户的所有角色信息
            List<UserRole> userRoles = userRoleDAO.findByLockStatus(RequestContext.getAgencyCode(), Long.parseLong(RequestContext.getExeUserId()), CoreConstants.COMMON_N);
            for (UserRole userRole : userRoles) {
                List<Permission> permissions = permissionDAO.findByModuleCode(RequestContext.getAgencyCode(), userRole.getRoleCode(), moduleCode);
                if (ValidationUtil.isEmpty(permissions)) {
                    continue;
                }
                for (Permission permission : permissions) {
                    fids.add(permission.getFuncId());
                }
            }
        }
        return fids;
    }

    @Override
    public RoleSecurityModel createPermission(RoleSecurityModel roleSecurityModel) throws Exception {
        // 数据验证
        if (ValidationUtil.isEmpty(roleSecurityModel) || ValidationUtil.isEmpty(roleSecurityModel.getRoleCode())
                || ValidationUtil.isEmpty(RequestContext.getAgencyCode())) {
            throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
        }
        roleSecurityModel.setAgencyCode(RequestContext.getAgencyCode());
        // 删除原有的角色模块关系
        moduleRoleDAO.deleteByRoleCode(roleSecurityModel.getAgencyCode(), roleSecurityModel.getRoleCode());
        // 删除原有的角色资源关系
        permissionDAO.deleteByRoleCode(roleSecurityModel.getAgencyCode(), roleSecurityModel.getRoleCode());
        List<ModuleOperationModel> moduleResourceModels = roleSecurityModel.getPrivilege();
        if (ValidationUtil.isEmpty(roleSecurityModel.getPrivilege())) {
            return roleSecurityModel;
        }
        Set<String> roleModuleSet = new HashSet<String>();
        for (ModuleOperationModel moduleResourceModel : moduleResourceModels) {
            //添加角色资源权限信息
            Permission rolePrivilege = new Permission();
            rolePrivilege.setAgencyCode(roleSecurityModel.getAgencyCode());
            rolePrivilege.setRoleCode(roleSecurityModel.getRoleCode());
            rolePrivilege.setModuleCode(moduleResourceModel.getModuleCode());
            rolePrivilege.setFuncId(moduleResourceModel.getFuncId());
            rolePrivilege.setRecDate(new Date());
            rolePrivilege.setRecStatus(CoreConstants.COMMON_Y);
            rolePrivilege.setRecUserId(RequestContext.getExeUserId());
            rolePrivilege.setSequenceNBR(sequenceGenerator.getNextValue());
            permissionDAO.insert(rolePrivilege);
            //组装角色模块集合信息
            roleModuleSet.add(moduleResourceModel.getModuleCode());
        }
        //递归获取父节模块
        Set<String> allRoleModelSet = new HashSet<String>();
        if (!ValidationUtil.isEmpty(roleModuleSet)) {
            for (String moduleCode : roleModuleSet) {
                recursiveGetModelSet(allRoleModelSet, moduleCode);
            }
        }
        //保存角色模块权限数据
        if (!ValidationUtil.isEmpty(allRoleModelSet)) {
            for (String moduleCode : allRoleModelSet) {
                ModuleRole moduleRole = new ModuleRole();
                moduleRole.setAgencyCode(roleSecurityModel.getAgencyCode());
                moduleRole.setModuleCode(moduleCode);
                moduleRole.setRoleCode(roleSecurityModel.getRoleCode());
                moduleRole.setRecDate(new Date());
                moduleRole.setRecStatus(CoreConstants.COMMON_Y);
                moduleRole.setRecUserId(RequestContext.getExeUserId());
                moduleRole.setSequenceNBR(sequenceGenerator.getNextValue());
                moduleRoleDAO.insert(moduleRole);
            }
        }
        return roleSecurityModel;
    }

    private void recursiveGetModelSet(Set<String> allRoleModelSet, String moduleCode) throws Exception {
        Module module = moduleDAO.findByModuleCode(moduleCode);
        allRoleModelSet.add(module.getModuleCode());
        if (SecurityConstants.MODULE_ROOT_CODE.equals(module.getParentCode())) {
            return;
        }
        recursiveGetModelSet(allRoleModelSet, module.getParentCode());
    }


}
