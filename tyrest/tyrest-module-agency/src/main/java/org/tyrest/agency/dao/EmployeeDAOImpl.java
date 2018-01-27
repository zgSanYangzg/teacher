package org.tyrest.agency.dao;

import org.tyrest.agency.face.orm.dao.EmployeeDAO;
import org.tyrest.agency.face.orm.entity.Employee;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.entity.Principal;
import org.tyrest.security.face.orm.entity.Role;
import org.tyrest.security.face.orm.entity.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  freeapis
 *  File: EmployeeDAOImpl.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: EmployeeDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-09-09 15:33:42		freeapis		Initial.
 *
 * </pre>
 */
@Repository(value = "employeeDAO")
public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements EmployeeDAO {
    @Override
    public void update(Employee employee) throws Exception {
        super.update(employee);
        Redis.setSingle(employee, employee.getUserId().toString());
    }

    @Override
    public boolean isEmployeeCodeAvailable(String agencyCode, String employeeCode) throws Exception {
        StringBuilder sql = new StringBuilder(" SELECT COUNT(1) FROM " +
                this.tableName() + " a INNER JOIN " +
                Principal.class.getAnnotation(Table.class).name() + " b " +
                "ON a.USER_ID = b.USER_ID  WHERE a.EMPLOYEE_CODE=:EMPLOYEE_CODE " +
                "AND b.AGENCY_CODE = :AGENCY_CODE ");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EMPLOYEE_CODE", employeeCode);
        params.put("AGENCY_CODE", agencyCode);
        return this.findCount(sql.toString(), params) <= 0;
    }

    @Override
    public Employee findByUserId(Long userId) throws Exception {
        Employee employee = Redis.getSingle(this.getEntityClass(), userId.toString());
        if (ValidationUtil.isEmpty(employee)) {
            StringBuilder sql = new StringBuilder(" AND USER_ID = :USER_ID ");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("USER_ID", userId);
            employee = this.findFirst(sql.toString(), params);
            if (!ValidationUtil.isEmpty(employee)) {
                Redis.setSingle(employee, employee.getUserId().toString());
            }
        }
        return employee;
    }

	@Override
	public List<Map<String,Object>> findByPage(String agencyCode, String departmentCode, String userName, String employeeCode,Page page,String orderBy, String order) throws Exception {
		 String findSql = " SELECT " +
				" EE.SEQUENCE_NBR AS sequenceNBR, " +
				" EE.DEPARTMENT_CODE AS departmentCode, " +
				" EE.USER_ID AS userId, " +
				" EE.EMPLOYEE_CODE AS employeeCode, " +
				" EE.NEED_CHANGE_PASS AS needChangePass, " +
				" EE.REC_DATE AS recDate, " +
				" EE.REC_USER_ID AS recUserId, " +
				" EE.REC_STATUS AS recStatus, " +
				" SP.AVATAR AS avatar, " +
				" SP.USER_NAME AS userName, " +
				" SP.GENDER AS gender, " +
				" SP.MOBILE AS mobile, " +
				" SP.LOCK_STATUS AS lockStatus, " +
				" SP.LOCK_DATE AS lockDate, " +
				" SP.LOCK_USER_ID AS lockUserId, " +
				" ROLE.roleCodes, " +
				" ROLE.roleNames " +
				" FROM "+this.tableName()+" EE INNER JOIN "+
				tableName(Principal.class) +" SP LEFT JOIN " +
				"( SELECT UR.USER_ID,GROUP_CONCAT(UR.ROLE_CODE) roleCodes,GROUP_CONCAT(R.ROLE_NAME) roleNames " +
				" FROM " +
				 tableName(UserRole.class) +" UR LEFT JOIN " +
				 tableName(Role.class) +" R " +
				 " ON UR.ROLE_CODE = R.ROLE_CODE " +
				 " GROUP BY UR.USER_ID " +
				 ") ROLE "+
				" ON EE.USER_ID = ROLE.USER_ID " +
				" WHERE EE.USER_ID = SP.USER_ID ";

		StringBuilder sql = new StringBuilder(findSql);
		Map<String, Object> params = new HashMap<String, Object>();
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND SP.AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if(!ValidationUtil.isEmpty(departmentCode)){
			sql.append(" AND EE.DEPARTMENT_CODE = :DEPARTMENT_CODE ");
			params.put("DEPARTMENT_CODE", departmentCode);
		}
		if(!ValidationUtil.isEmpty(userName)){
			sql.append(" AND EE.USER_NAME = :USER_NAME ");
			params.put("USER_NAME", userName);
		}
		if(!ValidationUtil.isEmpty(employeeCode)){
			sql.append(" AND EE.EMPLOYEE_CODE = :EMPLOYEE_CODE ");
			params.put("EMPLOYEE_CODE", employeeCode);
		}
		return this.findMapsByPage(sql.toString(), params, page);
	}
	
	@Override
	public List<Employee> getEmployeesByRoleCode(String roleCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sql.append(" AND Z.LOCK_STATUS = 'N' ");
		if(!ValidationUtil.isEmpty(roleCode)){
			sql.append(" AND EXISTS(SELECT (1) FROM "+ UserRole.class.getAnnotation(Table.class).name()+" S WHERE Z.USER_ID = S.USER_ID AND S.ROLE_CODE = :ROLE_CODE ) ");
			params.put("ROLE_CODE", roleCode);
		}
		return this.find(sql.toString(), params, null, null);
	}
}
