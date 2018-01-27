package org.tyrest.security.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.ModuleDAO;
import org.tyrest.security.face.orm.entity.Module;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityModuleDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityModuleDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value="moduleDAO")
public class ModuleDAOImpl extends GenericDAOImpl<Module> implements ModuleDAO
{

	@Override
	public Module findByModuleCode(String moduleCode) throws Exception {
		Module module = Redis.getSingle(Module.class, moduleCode);
		if(!ValidationUtil.isEmpty(module)){
			return module;
		}
		String sqlSufix = " AND MODULE_CODE = :MODULE_CODE ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MODULE_CODE", moduleCode);
		module = this.findFirst(sqlSufix, params);
		if(!ValidationUtil.isEmpty(module)){
			Redis.setSingle(Module.class, moduleCode);
		}
		return module;
	}

	@Override
	public List<Module> findByParentCode(String parentCode)throws Exception{
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if(!ValidationUtil.isEmpty(parentCode)){
			sql.append("AND PARENT_CODE = :PARENT_CODE");
			params.put("PARENT_CODE", parentCode);
		}
		return this.find(sql.toString(), params, "recDate", "desc" );
	}

	@Override
	public List<Module> findByPort(String port) throws Exception {
		String sqlSufix = " AND PORT = :PORT ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PORT", port);
		return this.find(sqlSufix, params, null, null);
	}

	@Override
	public List<Module> getByPage(String moduleCode, String moduleName, String parentCode, String port,
						  String lockStatus, Page page, String orderBy, String order)throws Exception{
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		if(!ValidationUtil.isEmpty(moduleCode)) {
			sql.append("AND MODULE_CODE =:MODULE_CODE");
			params.put("MODULE_CODE", moduleCode);
		}
		if(!ValidationUtil.isEmpty(moduleName)){
			sql.append(" AND MODULE_NAME =:MODULE_NAME");
			params.put("MODULE_NAME", moduleName);
		}
		if(!ValidationUtil.isEmpty(parentCode)){
			sql.append(" AND PARENT_CODE =:PARENT_CODE");
			params.put("PARENT_CODE", parentCode);
		}
		if(!ValidationUtil.isEmpty(port)){
			sql.append(" AND PORT =:PORT");
			params.put("PORT", port);
		}
		if(!ValidationUtil.isEmpty(lockStatus)){
			sql.append(" AND LOCK_STATUS =:LOCK_STATUS");
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
	public void deleteByModuleCode(String moduleCode)throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MODULE_CODE",moduleCode);
		this.update("DELETE FROM "+ this.tableName() +" WHERE MODULE_CODE =:MODULE_CODE",params );
		Redis.removeSingle(Module.class, moduleCode);
	}

	@Override
	public boolean isModuleCodeAvailable(String moduleCode, Long id)throws Exception{
		StringBuilder sql = new StringBuilder("AND MODULE_CODE =:MODULE_CODE");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MODULE_CODE", moduleCode);
		if(!ValidationUtil.isEmpty(id)){
			sql.append(" AND SEQUENCE_NBR!=SEQUENCE_NBR");
			params.put("SEQUENCE_NBR",id);
		}
		return this.findCount(sql.toString(),params).compareTo(0L) <= 0 ? true:false;
	}

}
