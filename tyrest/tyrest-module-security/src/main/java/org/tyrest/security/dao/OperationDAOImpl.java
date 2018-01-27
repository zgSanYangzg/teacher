package org.tyrest.security.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.OperationDAO;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.orm.entity.Operation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityResourceDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityResourceDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value="operationDAO")
public class OperationDAOImpl extends GenericDAOImpl<Operation> implements OperationDAO
{
	private static final String DELETE_ALL_SECURITY_RESOURCE = " DELETE FROM SECURITY_OPERATION ";
	private static final String SELECT_ALL_SECURITY_OPERATION=" SELECT * FROM SECURITY_OPERATION ";

	@Override
	public void insert(Operation entity) throws Exception {
		super.insert(entity);
		Redis.remove(SecurityConstants.ALL_OPERATION_RESOURCE);
	}

	@Override
	public void update(Operation entity) throws Exception {
		super.update(entity);
		Redis.remove(SecurityConstants.ALL_OPERATION_RESOURCE);
	}

	@Override
	public int deleteAllResource() throws Exception {
		Redis.remove(SecurityConstants.ALL_OPERATION_RESOURCE);
		return this.update(DELETE_ALL_SECURITY_RESOURCE, null);
	}

	@Override
	public Operation findByFid(String funcId) throws Exception {
		String sqlSufix = " AND FUNC_ID = :FUNC_ID";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("FUNC_ID", funcId);
		return this.findFirst(sqlSufix, params);
	}

	@Override
	public List<Operation> findAllResource()throws Exception {
		return this.find(SELECT_ALL_SECURITY_OPERATION, null, null, null);
	}


}
