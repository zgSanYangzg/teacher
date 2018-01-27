package org.tyrest.agency.face.service;

import org.tyrest.agency.face.model.EmployeeModel;
import org.tyrest.agency.face.orm.entity.Employee;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: EmployeeService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: EmployeeService.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 15:33:42		freeapis		Initial.
 *
 * </pre>
 */
public interface EmployeeService extends BaseService<EmployeeModel,Employee>
{

	public EmployeeModel createEmployee(EmployeeModel employeeModel) throws Exception;

	public EmployeeModel updateEmployee(EmployeeModel employeeModel) throws Exception;

	public EmployeeModel getEmployee(Long id) throws Exception;

	public boolean isEmployeeCodeAvailable(String agencyCode, String employeeCode) throws Exception;

	public EmployeeModel updateLockStatus(Long id) throws Exception;

	public Page getByPage(String agencyCode, String departmentCode, String userName,
                          String employeeCode, Page page, String orderBy, String order) throws Exception;

	/**
	 * 员工修改自身密码
	 * @param employeeModel
	 * @throws Exception
	 */
	public void updatePasswordBySelf(EmployeeModel employeeModel) throws Exception;

}
