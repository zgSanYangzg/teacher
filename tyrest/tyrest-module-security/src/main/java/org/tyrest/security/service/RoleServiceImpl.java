package org.tyrest.security.service;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.core.mysql.ReferenceModel;
import org.tyrest.security.face.orm.dao.ModuleRoleDAO;
import org.tyrest.security.face.orm.dao.PermissionDAO;
import org.tyrest.security.face.orm.dao.RoleDAO;
import org.tyrest.security.face.orm.dao.UserRoleDAO;
import org.tyrest.security.face.orm.entity.Role;
import org.tyrest.security.face.model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.security.face.service.RoleService;

import java.util.Date;
import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: RoleServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RoleServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleModel, Role> implements RoleService
{

	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private UserRoleDAO userRoleDao;
	
	@Autowired
	private ModuleRoleDAO moduleRoleDao;
	
	@Autowired
	private PermissionDAO rolePrivilegeDAO;
	
	
	@Override
	public RoleModel getRoleByCode(String agencyCode, String roleCode) throws Exception {
		return Bean.toModel(roleDao.findByCode(agencyCode, roleCode), new RoleModel());
	}


	@Override
	public List<RoleModel> getAllRoles(String agencyCode, String roleCode, String roleName, String lockStatus)
			throws Exception {
		List<RoleModel> models=Bean.toModels(roleDao.findRoles(agencyCode, roleCode, roleName, lockStatus),RoleModel.class);
		return models;
	}

	@Override
	public RoleModel createRole(String agencyCode, RoleModel roleModel) throws Exception {
		Role role=this.prepareEntity(roleModel);
		role.setAgencyCode(RequestContext.getAgencyCode());
		if(ValidationUtil.isEmpty(roleModel.getLockStatus())){
			role.setLockStatus(CoreConstants.COMMON_N);
		}
	    roleDao.insert(role);
		return Bean.toModel(role, roleModel);
	}

	@Override
	public Page getByPage(String agencyCode, String roleCode, String roleName, String lockStatus, Page page,
			String orderBy, String order) throws Exception {
		List<RoleModel> models=Bean.toModels(roleDao.findByPage(agencyCode, roleCode, roleName, lockStatus, page, orderBy, order),RoleModel.class);
		page.setList(models);
		return page;
	}

	@Override
	public RoleModel updateRole(RoleModel roleModel) throws Exception {
		Role role=roleDao.findByCode(RequestContext.getAgencyCode(), roleModel.getRoleCode());
		if(ValidationUtil.isEmpty(role)){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		Bean.copyExistPropertis(roleModel, role);
		role.setRecDate(new Date());
		role.setRecUserId(RequestContext.getExeUserId());
		roleDao.update(role);
		return Bean.toModel(role, roleModel);
	}

	@Override
	public String[] deleteRole(String agencyCode, String [] roleCodes) throws Exception {
		
		for(String roleCode :roleCodes){
			Role role=roleDao.findByCode(agencyCode, roleCode);
			if(ValidationUtil.isEmpty(role)){
				throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
			}
			String msg = userRoleDao.deleteCheck(
					new ReferenceModel("SECURITY_USER_ROLE",new String[]{"ROLE_CODE"},new String[]{roleCode},"商家员工"));
			if(!ValidationUtil.isEmpty(msg)){
				throw new DataValidateException(msg);
			}
			
			//删除角色对应的资源
			moduleRoleDao.deleteByRoleCode(agencyCode, roleCode);
			//删除角色对应的模块
			rolePrivilegeDAO.deleteByRoleCode(agencyCode, roleCode);
			roleDao.deleteByRoleCode(agencyCode, roleCode);
			
		}
		return roleCodes;
	}

	@Override
	public RoleModel updateLockStatus(String roleCode) throws Exception {
		Role role=roleDao.findByCode(RequestContext.getAgencyCode(), roleCode);
		if(ValidationUtil.isEmpty(role)){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		
		String lockStatus = CoreConstants.COMMON_Y.equals(role.getLockStatus()) ? CoreConstants.COMMON_N : CoreConstants.COMMON_Y;
		role.setRecDate(new Date());
		role.setLockStatus(lockStatus);
		if(CoreConstants.COMMON_Y.equals(lockStatus)){
			String msg = userRoleDao.deleteCheck(
					new ReferenceModel("SECURITY_USER_ROLE",new String[]{"ROLE_CODE"},new String[]{roleCode},"商家员工"));
			if(!ValidationUtil.isEmpty(msg)){
				throw new DataValidateException(msg);
			}
			role.setLockDate(new Date());
			role.setLockUserId(RequestContext.getExeUserId());
		}
		roleDao.update(role);
		Redis.removeSingle(this.getPoClass(), roleCode);
		
		return Bean.toModel(role, new RoleModel());
	}

	public boolean isRoleNameAvailable(String roleName, Long id) throws Exception{
		return roleDao.isRoleNameAvailable(roleName,id);
	}

	public boolean isRoleCodeAvailable(String roleCode, Long id) throws Exception{
		return roleDao.isRoleCodeAvailable(roleCode,id);
	}
}
