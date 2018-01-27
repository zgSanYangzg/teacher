package org.tyrest.asi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.asi.face.orm.dao.GroupColumnDAO;
import org.tyrest.asi.face.orm.entity.GroupColumn;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: GroupColumnDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupColumnDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value = "groupColumnDAO")
public class GroupColumnDAOImpl extends GenericDAOImpl<GroupColumn>implements GroupColumnDAO {
	
	private static final String CACHE_KEY_GROUPCOLUMNS = "GROUPCOLUMNS";

	@Override
	public Map<String, GroupColumn> findGroupColumnsMap(String agencyCode, String groupCode) throws Exception {
		List<GroupColumn> fields = this.findGroupColumns(agencyCode, groupCode);
		Map<String, GroupColumn> fieldsMap = new HashMap<String, GroupColumn>();
		if (!ValidationUtil.isEmpty(fields)) {
			for (GroupColumn field : fields) {
				fieldsMap.put(field.getColumnCode(), field);
			}
		}
		return fieldsMap;
	}

	@Override
	public void insertWithCache(GroupColumn groupColumn) throws Exception {
		this.insert(groupColumn);
		Redis.setSingle(groupColumn, groupColumn.getAgencyCode(), groupColumn.getGroupCode(),groupColumn.getColumnCode());
		Redis.remove(CACHE_KEY_GROUPCOLUMNS,groupColumn.getAgencyCode(), groupColumn.getGroupCode());
	}

	@Override
	public void deleteWithCache(String agencyCode, String groupCode, String columnCode) throws Exception {
		GroupColumn currentField = this.findGroupColumn(agencyCode, groupCode, columnCode);
		if (!ValidationUtil.isEmpty(currentField)) {
			StringBuilder sql = new StringBuilder(" DELETE FROM ASI_GROUP_COLUMN WHERE 1=1 ").append(
					" AND AGENCY_CODE = :AGENCY_CODE AND GROUP_CODE = :GROUP_CODE AND COLUMN_CODE = :COLUMN_CODE ");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("AGENCY_CODE", agencyCode);
			param.put("GROUP_CODE", groupCode);
			param.put("COLUMN_CODE", columnCode);
			this.update(sql.toString(), param);
			Redis.removeSingle(GroupColumn.class, agencyCode, groupCode, columnCode);
			Redis.remove(CACHE_KEY_GROUPCOLUMNS,agencyCode, groupCode);
		}
	}

	@Override
	public void updateWithCache(GroupColumn groupColumn) throws Exception {
		this.update(groupColumn);
		Redis.setSingle(groupColumn, groupColumn.getAgencyCode(), groupColumn.getGroupCode(),groupColumn.getColumnCode());
		Redis.remove(CACHE_KEY_GROUPCOLUMNS,groupColumn.getAgencyCode(), groupColumn.getGroupCode());
	}

	@Override
	public GroupColumn findGroupColumn(String agencyCode, String groupCode, String columnCode) throws Exception {
		GroupColumn groupColumn = Redis.getSingle(GroupColumn.class, agencyCode, groupCode, columnCode);
		if (ValidationUtil.isEmpty(groupColumn)) {
			StringBuilder sql = new StringBuilder(
					" AND AGENCY_CODE = :AGENCY_CODE AND GROUP_CODE = :GROUP_CODE AND COLUMN_CODE = :COLUMN_CODE ");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("AGENCY_CODE", agencyCode);
			param.put("GROUP_CODE", groupCode);
			param.put("COLUMN_CODE", columnCode);
			groupColumn = this.findFirst(sql.toString(), param);
			if (!ValidationUtil.isEmpty(groupColumn)) {
				Redis.setSingle(groupColumn, groupColumn.getAgencyCode(), groupColumn.getGroupCode(),
						groupColumn.getColumnCode());
			}
		}
		return groupColumn;
	}

	@Override
	public List<GroupColumn> findGroupColumns(String agencyCode, String groupCode) throws Exception {
		List<GroupColumn> result = Redis.get(CACHE_KEY_GROUPCOLUMNS, agencyCode, groupCode);
		if (ValidationUtil.isEmpty(result)) {
			this.refreshCache(agencyCode, groupCode);
			result = Redis.get(CACHE_KEY_GROUPCOLUMNS, agencyCode, groupCode);
		}
		return ValidationUtil.isEmpty(result) ? new ArrayList<GroupColumn>() : result;
	}

	private void refreshCache(String agencyCode, String groupCode) throws Exception {
		StringBuilder sql = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND GROUP_CODE = :GROUP_CODE ");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AGENCY_CODE", agencyCode);
		param.put("GROUP_CODE", groupCode);
		List<GroupColumn> searchResult = this.find(sql.toString(), param, "displayOrder", "asc");
		if (!ValidationUtil.isEmpty(searchResult)) {
			Redis.set(searchResult, CACHE_KEY_GROUPCOLUMNS, agencyCode, groupCode);
		}
	}
}
