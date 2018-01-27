package org.tyrest.agency.face.orm.dao;

import org.tyrest.agency.face.orm.entity.Employee;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: EmployeeDAO.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: EmployeeDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 15:33:42		freeapis		Initial.
 *
 * </pre>
 */
public interface EmployeeDAO extends GenericDAO<Employee> {

	/**
	 * 验证员工编号是否存在
	 * @param agencyCode
	 * @param employeeCode
	 * @return
	 * @throws Exception
	 */
	public boolean isEmployeeCodeAvailable(String agencyCode, String employeeCode) throws Exception;

	/**
	 * 分页查询系统用户信息
	 * @param agencyCode
	 * @param departmentCode
	 * @param userName
	 * @param employeeCode
	 * @param page
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findByPage(String agencyCode, String departmentCode, String userName,
                                                String employeeCode, Page page, String orderBy, String order) throws Exception;

	/**
	 * 根据RoleCode查询Employee
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	public List<Employee> getEmployeesByRoleCode(String roleCode) throws Exception;

	/**
	 * 根据userId查询系统用户信息
	 * @param userId
	 * @return
	 */
    Employee findByUserId(Long userId) throws Exception;
}
