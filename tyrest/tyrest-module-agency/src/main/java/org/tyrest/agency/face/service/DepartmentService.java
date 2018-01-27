package org.tyrest.agency.face.service;

import org.tyrest.agency.face.orm.entity.Department;
import org.tyrest.agency.face.model.DepartmentModel;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;

import java.util.List;

/**
 * <pre>
 * 
 *  freeapis
 *  File: DepartmentService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:商家部门service
 *  TODO
 * 
 *  Notes:
 * 	$Id: DepartmentService.java 31101200-9 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年3月10日		wuqiang		Initial.
 * 
 * </pre>
 */
public interface DepartmentService extends BaseService<DepartmentModel, Department> {
	
	/**
	 * 获取部门树
	 * @param agencyCode
	 * @param parentCode
	 * @return
	 * @throws Exception
	 */
	DepartmentModel buildDepartmentTree(String agencyCode, String parentCode) throws Exception;
	
	/**
	 * 根据父部门编号获取子部门列表
	 * @param agencyCode
	 * @param parentCode
	 * @return
	 * @throws Exception
	 */
	List<DepartmentModel> getDepartmentsByParentCode(String agencyCode, String parentCode) throws Exception;
	
	/**
	 * 分页查询部门信息列表
	 * @param agencyCode
	 * @param parentCode
	 * @param departmentCode
	 * @param departmentName
	 * @param lockStatus
	 * @param page
	 * @param orderBy
	 * @param order
	 * @return Page
	 * @throws Exception 
	 */
	Page getByPage(String agencyCode, String parentCode, String departmentCode, String departmentName,
                   String lockStatus, Page page, String orderBy, String order) throws Exception;
	
	/**
	 * 创建部门信息
	 * @param newDepartment
	 * @return
	 * @throws Exception
	 */
	DepartmentModel createDepartment(DepartmentModel newDepartment) throws Exception;

	/**
	 * 删除部门
	 * @param agencyCode
	 * @param departmentCodes……
	 * @return
	 * @throws Exception 
	 */
	void deleteDepartments(String agencyCode, String... departmentCodes) throws Exception;
	
	/**
	 * 修改部门信息
	 * @param currentDepartment
	 * @return
	 * @throws Exception
	 */
	DepartmentModel updateDepartment(DepartmentModel currentDepartment) throws Exception;

	/**
	 * 修改锁定状态
	 * @param agencyCode
	 * @param departmentCode
	 * @return
	 * @throws Exception 
	 */
	DepartmentModel updateLockStatus(String agencyCode, String departmentCode) throws Exception;
	
	/**
	 * 根据部门code获取部门信息
	 * @param agencyCode
	 * @param departmentCode
	 * @return
	 * @throws Exception
	 */
	DepartmentModel getDepartmentByCode(String agencyCode, String departmentCode) throws Exception;

	/**
	 * 判断部门code是否可用
	 * @param agencyCode
	 * @param departmentCode
	 * @return
	 * @throws Exception
	 */
	boolean isDepartmentCodeAvailable(String agencyCode, String departmentCode, Long id) throws Exception;

	/**
	 * 判断部门名称是否可用
	 * @param agencyCode
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	boolean isDepartmentNameAvailable(String agencyCode, String departmentName, Long id) throws Exception;

}
