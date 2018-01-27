package org.tyrest.agency.service;

import org.tyrest.agency.face.model.EmployeeModel;
import org.tyrest.agency.face.orm.dao.EmployeeDAO;
import org.tyrest.agency.face.orm.entity.Employee;
import org.tyrest.agency.face.service.EmployeeService;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.PrincipalModel;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.orm.dao.RoleDAO;
import org.tyrest.security.face.orm.dao.UserRoleDAO;
import org.tyrest.security.face.service.SecurityService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  freeapis
 *  File: EmployeeServiceImpl.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: EmployeeServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 15:33:42		freeapis		Initial.
 *
 * </pre>
 */
@Service(value = "employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeModel, Employee> implements EmployeeService {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    PrincipalDAO principalDAO;


    @Override
    public EmployeeModel createEmployee(EmployeeModel employeeModel) throws Exception {
        if (ValidationUtil.isEmpty(RequestContext.getAgencyCode())) {
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        //#1创建员工信息
        employeeModel.setUserId(sequenceGenerator.getNextValue());
        employeeModel.setNeedChangePass(CoreConstants.COMMON_Y);
        employeeModel.setAgencyCode(RequestContext.getAgencyCode());
        Employee employee = this.prepareEntity(employeeModel);
        employeeDAO.insert(employee);
        //#创建主体信息
        PrincipalModel principalModel = new PrincipalModel();
        principalModel.setLoginId(employeeModel.getEmployeeCode());
        principalModel.setUserId(employeeModel.getUserId());
        principalModel.setAgencyCode(employeeModel.getAgencyCode());
        principalModel.setUserName(employeeModel.getUserName());
        principalModel.setNickName(employeeModel.getNickName());
        principalModel.setRealName(employeeModel.getRealName());
        principalModel.setGender(employeeModel.getGender());
        principalModel.setBirthDate(employeeModel.getBirthDate());
        principalModel.setAvatar(employeeModel.getAvatar());
        principalModel.setPassword(SecurityConstants.DEFAULT_USER_PASSWORD);
        principalModel.setMobile(employeeModel.getMobile());
        principalModel.setRoleCodes(employeeModel.getRoleCodes());
        principalModel.setLoginId(employeeModel.getEmployeeCode());
        principalModel.setUserType(CoreConstants.CODE_SUPER_ADMIN.equals(principalModel.getAgencyCode()) ? UserType.SUPER_ADMIN.getValue() : UserType.AGENCY_USER.getValue());
        principalModel = securityService.createPrincipal(principalModel, IdType.employeeCode);
        EmployeeModel model = Bean.toModel(employee, new EmployeeModel());
        Bean.copyExistPropertis(principalModel, model);
        return model;
    }

    @Override
    public EmployeeModel updateEmployee(EmployeeModel employeeModel) throws Exception {
        Employee currentEmployee = employeeDAO.findByUserId(employeeModel.getUserId());
        if (ValidationUtil.isEmpty(currentEmployee)) {
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        employeeModel.setRecDate(new Date());
        employeeModel.setRecUserId(RequestContext.getExeUserId());
        Bean.copyExistPropertis(employeeModel, currentEmployee);
        employeeDAO.update(currentEmployee);
        //修改主体信息
        PrincipalModel currentPrincipal = Bean.copyExistPropertis(employeeModel, new PrincipalModel());
        securityService.updatePrincipal(currentPrincipal);
        Bean.copyExistPropertis(currentPrincipal, employeeModel);
        return employeeModel;
    }

    @Override
    public EmployeeModel getEmployee(Long id) throws Exception {
        Employee employee = employeeDAO.findByUserId(id);
        EmployeeModel result = null;
        if (!ValidationUtil.isEmpty(employee)) {
            result = Bean.toModel(employee, new EmployeeModel());
            PrincipalModel currentPrincipal = securityService.getPrincipal(result.getUserId());
            if (!ValidationUtil.isEmpty(currentPrincipal)) {
                BeanUtils.copyProperties(currentPrincipal, result);
                //查询用户角色
                List<Map<String, Object>> userRoles = userRoleDAO.findUserRolesInfo(currentPrincipal.getAgencyCode(), currentPrincipal.getUserId());
                StringBuffer roleCodes = new StringBuffer();
                StringBuffer roleNames = new StringBuffer();
                for (Map<String, Object> userRole : userRoles) {
                    roleCodes.append(userRole.get("roleCode") + ",");
                    roleNames.append(userRole.get("roleName") + ",");
                }
                if (!ValidationUtil.isEmpty(roleCodes.toString())) {
                    roleCodes.deleteCharAt(roleCodes.length() - 1);
                }
                if (!ValidationUtil.isEmpty(roleNames.toString())) {
                    roleNames.deleteCharAt(roleNames.length() - 1);
                }
                result.setRoleCodes(roleCodes.toString());
                result.setRoleNames(roleNames.toString());
            }
        }
        return result;
    }

    @Override
    public boolean isEmployeeCodeAvailable(String agencyCode, String employeeCode) throws Exception {
        return employeeDAO.isEmployeeCodeAvailable(RequestContext.getAgencyCode(), employeeCode);
    }

    @Override
    public EmployeeModel updateLockStatus(Long userId) throws Exception {
        Employee currentEmployee = employeeDAO.findByUserId(userId);
        if (ValidationUtil.isEmpty(currentEmployee)) {
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        PrincipalModel principalModel = securityService.updateLockStatus(currentEmployee.getUserId());
        EmployeeModel employeeModel = Bean.toModel(currentEmployee, new EmployeeModel());
        Bean.copyExistPropertis(principalModel, employeeModel);
        return employeeModel;
    }

    @Override
    public Page getByPage(String agencyCode, String departmentCode, String userName, String employeeCode, Page page, String orderBy, String order) throws Exception {
        List<Map<String, Object>> listEntity = employeeDAO.findByPage(agencyCode, departmentCode, userName, employeeCode, page, orderBy, order);
        List<EmployeeModel> listModel = Bean.listMap2ListBean(listEntity, EmployeeModel.class);
        page.setList(listModel);
        return page;
    }

    @Override
    public void updatePasswordBySelf(EmployeeModel employeeModel) throws Exception {
        Employee employee = employeeDAO.findByUserId(Long.valueOf(RequestContext.getExeUserId()));
        if (ValidationUtil.isEmpty(employee)) {
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        securityService.updatePassword(employee.getUserId(), employeeModel.getPassword(), employeeModel.getOldPassword());
    }
}