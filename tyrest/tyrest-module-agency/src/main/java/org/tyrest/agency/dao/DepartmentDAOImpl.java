package org.tyrest.agency.dao;

import org.tyrest.agency.face.orm.dao.DepartmentDAO;
import org.tyrest.agency.face.orm.entity.Department;
import org.tyrest.agency.face.orm.entity.Employee;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.springframework.stereotype.Repository;
import org.tyrest.security.face.orm.entity.Principal;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DepartmentDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DepartmentDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Repository(value = "departmentDAO")
public class DepartmentDAOImpl extends GenericDAOImpl<Department> implements DepartmentDAO {

	@Override
	public List<Department> findByParentCode(String agencyCode, String parentCode)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (!ValidationUtil.isEmpty(agencyCode)) {
			sql.append(" AND AGENCY_CODE=:AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if (!ValidationUtil.isEmpty(parentCode)) {
			sql.append(" AND PARENT_CODE=:PARENT_CODE ");
			params.put("PARENT_CODE", parentCode);
		}
		return this.find(sql.toString(), params, "recDate", "desc");
	}

	@Override
	public Department findByDepartmentCode(String agencyCode, String departmentCode) throws Exception {
		Department department = Redis.getSingle(Department.class, agencyCode, departmentCode);
		if (ValidationUtil.isEmpty(department)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("AGENCY_CODE", agencyCode);
			params.put("DEPARTMENT_CODE", departmentCode);
			department = this.findFirst("AND AGENCY_CODE = :AGENCY_CODE AND DEPARTMENT_CODE = :DEPARTMENT_CODE ", params);
			if (!ValidationUtil.isEmpty(department)) {
				Redis.setSingle(department, agencyCode, departmentCode);
			}
		}
		return department;
	}

	@Override
	public List<Department> findByPage(String agencyCode, String parentCode, String departmentCode,
			String departmentName, String lockStatus, Page page, String orderBy, String order) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (!ValidationUtil.isEmpty(agencyCode)) {
			sql.append(" AND AGENCY_CODE=:AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if (!ValidationUtil.isEmpty(parentCode)) {
			sql.append(" AND PARENT_CODE=:PARENT_CODE ");
			params.put("PARENT_CODE", parentCode);
		}
		if (!ValidationUtil.isEmpty(departmentCode)) {
			sql.append(" AND DEPARTMENT_CODE=:DEPARTMENT_CODE ");
			params.put("DEPARTMENT_CODE", departmentCode);
		}
		if (!ValidationUtil.isEmpty(departmentName)) {
			sql.append(" AND DEPARTMENT_NAME=:DEPARTMENT_NAME ");
			params.put("DEPARTMENT_NAME", departmentName);
		}
		if (!ValidationUtil.isEmpty(lockStatus)) {
			sql.append(" AND LOCK_STATUS=:LOCK_STATUS ");
			params.put("LOCK_STATUS", lockStatus);
		}
		if (ValidationUtil.isEmpty(orderBy)) {
			orderBy = "sequenceNbr";
		}
		if (ValidationUtil.isEmpty(order)) {
			order = "desc";
		}
		return this.paginate(sql.toString(), params, page, orderBy, order);
	}

	@Override
	public void deleteWithCache(String agencyCode, String departmentCode) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("DEPARTMENT_CODE", departmentCode);
		this.update("DELETE FROM "+ this.tableName() +" WHERE AGENCY_CODE = :AGENCY_CODE AND DEPARTMENT_CODE = :DEPARTMENT_CODE",params);
		Redis.removeSingle(Department.class, agencyCode, departmentCode);
	}

	@Override
	public void update(Department department) throws Exception {
		super.update(department);
		Redis.setSingle(department, department.getAgencyCode(), department.getDepartmentCode());
	}

	@Override
	public boolean isDepartmentCodeAvailable(String agencyCode, String departmentCode, Long id) throws Exception {
		StringBuilder sql = new StringBuilder(" AND AGENCY_CODE=:AGENCY_CODE AND DEPARTMENT_CODE=:DEPARTMENT_CODE ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("DEPARTMENT_CODE", departmentCode);
		if (!ValidationUtil.isEmpty(id)) {
			sql.append(" AND SEQUENCE_NBR!=:SEQUENCE_NBR");
			params.put("SEQUENCE_NBR", id);
		}
		return this.findCount(sql.toString(), params).compareTo(0L) <= 0 ? true : false;
	}

	@Override
	public boolean isDepartmentNameAvailable(String agencyCode, String departmentName, Long id) throws Exception {
		StringBuilder sql = new StringBuilder(" AND AGENCY_CODE=:AGENCY_CODE AND DEPARTMENT_NAME=:DEPARTMENT_NAME ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("DEPARTMENT_NAME", departmentName);
		if (!ValidationUtil.isEmpty(id)) {
			sql.append(" AND SEQUENCE_NBR!=:SEQUENCE_NBR");
			params.put("SEQUENCE_NBR", id);
		}
		return this.findCount(sql.toString(), params).compareTo(0L) <= 0 ? true : false;
	}

	@Override
	public String deleteDepartmentCheck(String agencyCode, String departmentCode)throws Exception {
		try{
			StringBuilder sql = new StringBuilder("SELECT COUNT(1) FROM "+Employee.class.getAnnotation(Table.class).name().toUpperCase()
					+" a INNER JOIN "+ Principal.class.getAnnotation(Table.class).name()+" b "
					+" WHERE a.USER_ID = b.USER_ID AND a.DEPARTMENT_CODE=:DEPARTMENT_CODE AND b.AGENCY_CODE=:AGENCY_CODE");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("DEPARTMENT_CODE", departmentCode);
			params.put("AGENCY_CODE", agencyCode);
			Long refValue = this.findCount(sql.toString(),params);
			if (refValue!=0){
				return ("在【员工信息】中有【" + refValue + "】条数据引用了当前数据，因此不能进行操作！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("检查是否可操作时出错，请检查参数传递是否正确！", e);
		}
		return null;
	}
}
