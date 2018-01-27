package org.tyrest.asi.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.asi.face.orm.dao.ASIBizDAO;
import org.tyrest.asi.face.orm.entity.ASIBiz;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: ASIBizDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIBizDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="asiBizDAO")
public class ASIBizDAOImpl extends GenericDAOImpl<ASIBiz> implements ASIBizDAO
{

	@Override
	public void insertWithCache(ASIBiz asiBiz) throws Exception {
		this.insert(asiBiz);
		Redis.removeSingle(this.getEntityClass(), asiBiz.getAgencyCode(),asiBiz.getEntityType());
	}

	@Override
	public void deleteWithCache(String agencyCode, String entityType) throws Exception {
		
	}

	@Override
	public void updateWithCache(ASIBiz asiBiz) throws Exception {
		this.update(asiBiz);
		Redis.removeSingle(this.getEntityClass(), asiBiz.getAgencyCode(),asiBiz.getEntityType());
	}

	@Override
	public ASIBiz findASIBiz(String agencyCode, String entityType) throws Exception {
		ASIBiz result = Redis.getSingle(this.getEntityClass(), agencyCode,entityType);
		if(ValidationUtil.isEmpty(result)){
			StringBuilder sql = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND ENTITY_TYPE = :ENTITY_TYPE ");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("AGENCY_CODE", agencyCode);
			params.put("ENTITY_TYPE", entityType);
			result = this.findFirst(sql.toString(), params);
			if(!ValidationUtil.isEmpty(result)){
				Redis.setSingle(result, result.getAgencyCode(),result.getEntityType());
			}
		}
		return result;
	}
	
}
