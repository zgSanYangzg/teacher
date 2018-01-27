package org.tyrest.security.face.model;

import java.io.Serializable;
import java.util.List;

public class RoleSecurityModel implements Serializable {
    private static final long serialVersionUID = 1L;
	private String roleCode;
	private String agencyCode;
	/**
	 * 该角色对应的权限
	 * 主要传入：moduleCode 和 funcId属性
	 */
	private List<ModuleOperationModel> privilege;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public List<ModuleOperationModel> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<ModuleOperationModel> privilege) {
		this.privilege = privilege;
	}
	
	

}
