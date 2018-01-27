package org.tyrest.agency.face.orm.dao;

import org.tyrest.agency.face.orm.entity.Department;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AgDepartmentDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgDepartmentDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月24日		yangbochao		Initial.
 *
 * </pre>
 */
public interface DepartmentDAO extends GenericDAO<Department> {
	
	/**
	 * @param agencyCode
	 * @param parentCode
	 * @param
	 * @return
	 * @throws Exception
	 */
	List<Department> findByParentCode(String agencyCode, String parentCode) throws Exception;
	
	/**
	 * 根据部门编号查询部门信息
	 * @param agencyCode
	 * @param departmentCode
	 * @return
	 * @throws Exception
	 */
	Department findByDepartmentCode(String agencyCode, String departmentCode) throws Exception;

	/**
	 * 分页查询部门信息
	 * @param agencyCode
	 * @param parentCode
	 * @param departmentCode
	 * @param departmentName
	 * @param lockStatus
	 * @param page
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws Exception
	 */
	List<Department> findByPage(String agencyCode, String parentCode, String departmentCode, String departmentName,
                                String lockStatus, Page page, String orderBy, String order) throws Exception;
	
	/**
	 * 删除部门
	 * @param agencyCode
	 * @param departmentCode
	 * @return 
	 * @throws Exception
	 */
	void deleteWithCache(String agencyCode, String departmentCode) throws Exception;

	/**
	 * 验证部门编号是否可用
	 * @param departmentCode
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean isDepartmentCodeAvailable(String agencyCode, String departmentCode, Long id) throws Exception;

	/**
	 * 验证部门名称是否可用
	 * @param agencyCode
	 * @param departmentName
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	boolean isDepartmentNameAvailable(String agencyCode, String departmentName, Long id) throws Exception;

	/**
	 * 检查删除引用
	 * @param agencyCode
	 * @param departmentCode
	 * @return
	 */
    String deleteDepartmentCheck(String agencyCode, String departmentCode) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */