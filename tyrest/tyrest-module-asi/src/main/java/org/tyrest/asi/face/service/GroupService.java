package org.tyrest.asi.face.service;

import java.util.List;

import org.tyrest.asi.face.model.GroupModel;
import org.tyrest.asi.face.orm.entity.Group;
import org.tyrest.core.mysql.BaseService;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: GroupService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface GroupService extends BaseService<GroupModel,Group>
{
	/**
     * 创建动态表单组
     * @param groupModel
     * @throws Exception
     */
    GroupModel createGroup(GroupModel groupModel) throws Exception;
    /**
     * 删除动态表单组
     * @param groupCode
     * @throws Exception
     */
    String deleteGroup(String agencyCode,String groupCode) throws Exception;
    /**
     * 更新动态表单组
     * @param groupModel
     * @throws Exception
     */
    GroupModel updateGroup(GroupModel groupModel) throws Exception;
    /**
     * TODO.获取组定义详情
     * @param agencyCode
     * @param groupCode
     * @return
     * @throws Exception
     */
    GroupModel getGroup(String agencyCode,String groupCode) throws Exception;
    /**
     * 根据父分组编码获取动态表单组定义列表
     * @param agencyCode
     * @param groupCOde
     * @return
     * @throws Exception
     */
	List<GroupModel> getGroupsByParentCode(String agencyCode,String parentCode) throws Exception;
	/**
	 * TODO.判断分组编码是否可用
	 * @param agencyCode
	 * @param groupCode
	 * @return
	 * @throws Exception
	 */
	boolean isGroupCodeAvailable(String agencyCode,String groupCode) throws Exception;
	/**
	 * TODO.同步获取分组树
	 * @param agencyCode
	 * @return
	 * @throws Exception
	 */
	GroupModel buildGruopTree(String agencyCode) throws Exception;
}
