package org.tyrest.snapshot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.snapshot.face.orm.dao.SnapshotGroupDAO;
import org.tyrest.snapshot.face.orm.entity.SnapshotGroup;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotGroupDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  快照<实体对象，实体快照对象>
 * 
 *  Notes:
 *  $Id: SnapshotGroupDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */

@Repository(value = "snapshotGroupDAO")
public class SnapshotGroupDAOImpl extends GenericDAOImpl<SnapshotGroup> implements SnapshotGroupDAO {

	
	public List<SnapshotGroup> queryForList(String groupCode,String businessCode,String snapshotType) throws Exception
	{
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder sql = new StringBuilder();
		
		if(!ValidationUtil.isEmpty(groupCode))
		{
			sql.append(" AND GROUP_CODE=:groupCode");	
			params.put("groupCode", groupCode);	
		}
		
		if(!ValidationUtil.isEmpty(businessCode))
		{
			sql.append(" AND GROUP_CODE=:businessCode");	
			params.put("businessCode", businessCode);	
		}
		if(!ValidationUtil.isEmpty(snapshotType))
		{
			sql.append(" AND SNAPSHOT_TYPE=:snapshotType");	
			params.put("snapshotType", snapshotType);	
		}
		
		
		return this.find(sql.toString(), params, null, null);
	}
}

/*
 * $Log: av-env.bat,v $
 */