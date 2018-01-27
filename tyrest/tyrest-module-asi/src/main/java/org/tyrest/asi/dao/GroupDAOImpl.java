package org.tyrest.asi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.asi.face.orm.dao.GroupDAO;
import org.tyrest.asi.face.orm.entity.Group;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: GroupDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="groupDAO")
public class GroupDAOImpl extends GenericDAOImpl<Group> implements GroupDAO
{

	@Override
	public void insertWithCache(Group group) throws Exception {
		this.insert(group);
		Redis.setSingle(group,group.getAgencyCode(),group.getGroupCode());
	}

	@Override
	public void deleteWithCache(String agencyCode, String groupCode) throws Exception {
		Group currentGroup = this.findGroup(agencyCode, groupCode);
		if(!ValidationUtil.isEmpty(currentGroup)){
			StringBuilder deleteSql = new StringBuilder(" DELETE FROM ASI_GROUP WHERE 1=1 ")
					.append(" AND GROUP_CODE = :GROUP_CODE AND AGENCY_CODE = :AGENCY_CODE ");
			Map<String , Object> params = new HashMap<String , Object>();
	        params.put("GROUP_CODE",groupCode);
	        params.put("AGENCY_CODE",agencyCode);
	        this.update(deleteSql.toString(), params);
	        Redis.removeSingle(this.getEntityClass(), agencyCode,groupCode);
		}
	}

	@Override
	public void updateWithCache(Group group) throws Exception {
		this.update(group);
		Redis.setSingle(group,group.getAgencyCode(),group.getGroupCode());
	}

	@Override
	public Group findGroup(String agencyCode, String groupCode) throws Exception {
		Group result = Redis.getSingle(this.getEntityClass(), agencyCode,groupCode);
    	if(ValidationUtil.isEmpty(result)){
    		StringBuilder sql = new StringBuilder(" AND GROUP_CODE = :GROUP_CODE AND AGENCY_CODE = :AGENCY_CODE ");
    		Map<String , Object> params = new HashMap<String , Object>();
            params.put("GROUP_CODE",groupCode);
            params.put("AGENCY_CODE",agencyCode);
            result = this.findFirst(sql.toString(),params);
            if(!ValidationUtil.isEmpty(result)){
            	Redis.setSingle(result,result.getAgencyCode(),result.getGroupCode());
            }
    	}
        return result;
	}

	@Override
	public List<Group> findGroupByParentCode(String agencyCode, String parentCode) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" AND AGENCY_CODE = :AGENCY_CODE ")
				.append(" AND PARENT_GROUP_CODE = :PARENT_GROUP_CODE ");
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("AGENCY_CODE",agencyCode);
		params.put("PARENT_GROUP_CODE",parentCode);
		return this.find(sql.toString(), params, "groupOrder", "desc");
	}

	@Override
	public boolean hasChildrenGroup(String agencyCode, String parentCode) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" AND AGENCY_CODE = :AGENCY_CODE ")
				.append(" AND PARENT_GROUP_CODE = :PARENT_GROUP_CODE ");
		Map<String , Object> params = new HashMap<String , Object>();
		params.put("AGENCY_CODE",agencyCode);
		params.put("PARENT_GROUP_CODE",parentCode);
		return this.findCount(sql.toString(), params) > 0;
	}

}
