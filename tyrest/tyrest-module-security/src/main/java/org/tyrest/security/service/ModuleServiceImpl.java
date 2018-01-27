package org.tyrest.security.service;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.core.mysql.ReferenceModel;
import org.tyrest.security.face.orm.dao.ModuleDAO;
import org.tyrest.security.face.service.ModuleService;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.orm.entity.Module;
import org.tyrest.security.face.orm.entity.ModuleOperation;
import org.tyrest.security.face.orm.entity.ModuleRole;
import org.tyrest.security.face.model.ModuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

@Service(value="moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<ModuleModel, Module> implements ModuleService{

    @Autowired
    ModuleDAO moduleDAO;

    @Override
    public ModuleModel buildModuleTree(String  moduleCode)throws Exception{
        ModuleModel moduleModel=null;
        Module module = moduleDAO.findByModuleCode(moduleCode);
        if (ValidationUtil.isEmpty(module)) {
            return moduleModel;
        }
        moduleModel = Bean.toModel(module, this.getModelClass().newInstance());
        if(!ValidationUtil.isEmpty(moduleModel)){
            buildTree(moduleModel);
        }
        return moduleModel;
    }

    @Override
    public List<ModuleModel> getModulesByParentCode(String parentCode) throws Exception {
        List<Module> modules = moduleDAO.findByParentCode(parentCode);
        return ValidationUtil.isEmpty(modules)? null:Bean.toModels(modules, ModuleModel.class);
    }

    @Override
    public List<ModuleModel> getByPort(String port) throws Exception{
        List<Module> moduleList = moduleDAO.findByPort(port);
        return ValidationUtil.isEmpty(moduleList)?null:Bean.toModels(moduleList,ModuleModel.class);
    }
    @Override
    public Page getByPage(String moduleCode, String moduleName, String parentCode, String port,
                          String lockStatus, Page page, String orderBy, String order)throws Exception{
        List<Module> moduleList = moduleDAO.getByPage(moduleCode,moduleName,parentCode,port,lockStatus,page,orderBy,order);
        page.setList(ValidationUtil.isEmpty(moduleList)?null:Bean.toModels(moduleList,ModuleModel.class));
        return page;
    }

    public void buildTree(ModuleModel parentModule)throws Exception{
        List<Module> modules= moduleDAO.findByParentCode(parentModule.getModuleCode());
        if(ValidationUtil.isEmpty(modules)){
            parentModule.setHasChildren(false);
        }else{
            parentModule.setHasChildren(true);
            parentModule.setChild(Bean.toModels(modules, ModuleModel.class));
            for(ModuleModel module:parentModule.getChild()){
                buildTree(module);
            }
        }
    }

    @Override
    public ModuleModel createModule(ModuleModel newModule)throws Exception{
        Module module = this.prepareEntity(newModule);
        if(ValidationUtil.isEmpty(newModule.getLockStatus())){
            module.setLockStatus(CoreConstants.COMMON_N);
        }
        if(ValidationUtil.isEmpty(newModule.getParentCode())){
            module.setParentCode(SecurityConstants.MODULE_ROOT_CODE);
        }
        module.setLockDate(new Date());
        module.setLockUserId(RequestContext.getExeUserId());
        moduleDAO.insert(module);
        return Bean.toModel(module, newModule);
    }

    @Override
    public void deleteModules(String... moduleCodes)throws Exception{
        for(String moduleCode:moduleCodes){
            this.deleteOneModule(moduleCode);
        }
    }

    public void deleteOneModule(String moduleCode)throws Exception{
        String checkResult=moduleDAO.deleteCheck(new ReferenceModel(getTableName(),
                        new String[]{"PARENT_CODE"},
                        new String[]{moduleCode},
                        "在子模块中"),
                        new ReferenceModel(getTableName(ModuleOperation.class),
                        new String[]{"MODULE_CODE"},
                        new String[]{moduleCode},
                        "在模块资源信息表中"),
                        new ReferenceModel(getTableName(ModuleRole.class),
                        new String[]{"MODULE_CODE"},
                        new String[]{moduleCode},
                        "在模块角色关系表中"));
        if(!ValidationUtil.isEmpty(checkResult)) {
            throw new DataValidateException(checkResult);
        }
        moduleDAO.deleteByModuleCode(moduleCode);
    }

    @Override
    public ModuleModel updateModule(ModuleModel currentModule)throws Exception{
        Module module = moduleDAO.findByModuleCode(currentModule.getModuleCode());
        if(ValidationUtil.isEmpty(module)){
            throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
        }
        Bean.copyExistPropertis(currentModule,module);
        module.setRecDate(new Date());
        module.setRecUserId(RequestContext.getExeUserId());
        moduleDAO.update(module);
        return Bean.toModel(module, currentModule);
    }

    @Override
    public ModuleModel updateLockStatus(String moduleCode)throws Exception{
        Module module = moduleDAO.findByModuleCode(moduleCode);
        if(ValidationUtil.isEmpty(module)){
            return null;
        }
        module.setLockStatus(CoreConstants.COMMON_N.equals(module.getLockStatus())?CoreConstants.COMMON_Y:CoreConstants.COMMON_N);
        module.setRecDate(new Date());
        module.setRecUserId(RequestContext.getExeUserId());
        moduleDAO.update(module);
        return Bean.toModel(module, new ModuleModel());
    }

    @Override
    public boolean isModuleCodeAvailable(String moduleCode, Long id) throws Exception {
        return moduleDAO.isModuleCodeAvailable(moduleCode, id);
    }
}
