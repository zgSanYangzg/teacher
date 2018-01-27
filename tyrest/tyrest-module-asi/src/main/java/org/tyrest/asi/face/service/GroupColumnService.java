package org.tyrest.asi.face.service;

import java.util.List;
import java.util.Map;

import org.tyrest.asi.face.model.GroupColumnModel;
import org.tyrest.asi.face.orm.entity.GroupColumn;
import org.tyrest.core.mysql.BaseService;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: GroupColumnService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupColumnService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface GroupColumnService extends BaseService<GroupColumnModel,GroupColumn>
{
	/**
	 * TODO.创建自定义列
	 * @param gruopColumnModel
	 * @throws Exception
	 */
	GroupColumnModel createGroupColumn(GroupColumnModel gruopColumnModel) throws Exception;
	/**
	 * TODO.删除自定义列
	 * @param agencyCode
	 * @param groupCode
	 * @param subGroupCode
	 * @param columnCode
	 * @throws Exception
	 */
	String deleteGroupColumn(String agencyCode, String groupCode, String columnCode) throws Exception;
	/**
	 * TODO.更新自定义列
	 * @param gruopColumnModel
	 * @throws Exception
	 */
	GroupColumnModel updateGroupColumn(GroupColumnModel gruopColumnModel) throws Exception;

	/**
	 * TODO.复制模板列到分组自定义列
	 * @param agencyCode
	 * @param groupCode
	 * @param subgroupCode
	 * @param columnTemplates
	 * @throws Exception
	 */
	void copyFromTemplate(String agencyCode, String groupCode,List<Map<String, String>> columnTemplates) throws Exception;

	/**
	 * TODO.获取子分组的列定义,从缓存取
	 * 
	 * @param agencyCode
	 * @param groupCode
	 * @param subgroupCode
	 * @return
	 * @throws Exception
	 */
	List<GroupColumnModel> getGroupColumns(String agencyCode, String groupCode) throws Exception;

	/**
	 * TODO.获取自定义列详情
	 * @param agencyCode
	 * @param groupCode
	 * @param subgroupCode
	 * @param columnCode
	 * @return
	 * @throws Exception
	 */
	GroupColumnModel getGroupColumn(String agencyCode, String groupCode, String columnCode) throws Exception;
	/**
	 * TODO.判断自定义列的列编码是否可用
	 * @param agencyCode
	 * @param groupCode
	 * @param columnCode
	 * @return
	 * @throws Exception
	 */
	boolean isGroupColumnCodeAvailable(String agencyCode,String groupCode,String columnCode) throws Exception;
}
