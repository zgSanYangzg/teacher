package org.tyrest.asi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.asi.face.constants.ASIConstants;
import org.tyrest.asi.face.model.GroupModel;
import org.tyrest.asi.face.orm.dao.GroupColumnDAO;
import org.tyrest.asi.face.orm.dao.GroupDAO;
import org.tyrest.asi.face.orm.entity.Group;
import org.tyrest.asi.face.service.GroupService;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: GroupServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="groupService")
public class GroupServiceImpl extends BaseServiceImpl<GroupModel, Group> implements GroupService
{
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private GroupColumnDAO groupColumnDAO;
	
	@Override
	public GroupModel createGroup(GroupModel groupModel) throws Exception {
		if (ValidationUtil.isEmpty(groupModel)) {
			throw new BusinessException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		Group newGroup = Bean.toPo(groupModel, new Group());
		if(ValidationUtil.isEmpty(newGroup.getDisplayMeta())){
			newGroup.setDisplayMeta(CoreConstants.COMMON_Y);
		}
		if(ValidationUtil.isEmpty(newGroup.getParentGroupCode())){
			newGroup.setParentGroupCode(ASIConstants.ROOT_GROUP_CODE);
		}
		newGroup.setAgencyCode(RequestContext.getAgencyCode());
		newGroup.setRecDate(new Date());
		newGroup.setRecStatus(CoreConstants.COMMON_ACTIVE);
		newGroup.setRecUserId(RequestContext.getExeUserId());
		groupDAO.insertWithCache(newGroup);
		return Bean.toModel(newGroup, groupModel);
	}

	@Override
	public String deleteGroup(String agencyCode, String groupCode) throws Exception {
		List<String> error = new ArrayList<String>();
		if(groupDAO.hasChildrenGroup(agencyCode, groupCode)){
			error.add("该分组存在子分组,不能删除.");
		}
		if(!ValidationUtil.isEmpty(groupColumnDAO.findGroupColumns(agencyCode, groupCode))){
			error.add("该分组存在列定义,不能删除.");
		}
		if(!ValidationUtil.isEmpty(error)){
			return Arrays.toString(error.toArray(new String[]{}));
		}
		groupDAO.deleteWithCache(agencyCode, groupCode);
		return null;
	}

	@Override
	public GroupModel updateGroup(GroupModel groupModel) throws Exception {
		Group currentGroup = groupDAO.findGroup(groupModel.getAgencyCode(), groupModel.getGroupCode());
		if (ValidationUtil.isEmpty(currentGroup)) {
			throw new BusinessException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		currentGroup.setGroupOrder(groupModel.getGroupOrder());
		currentGroup.setGroupName(groupModel.getGroupName());
		currentGroup.setDisplayMeta(groupModel.getDisplayMeta());
		if(ValidationUtil.isEmpty(currentGroup.getDisplayMeta())){
			currentGroup.setDisplayMeta(CoreConstants.COMMON_Y);
		}
		currentGroup.setRecDate(new Date());
		currentGroup.setRecUserId(RequestContext.getExeUserId());
		groupDAO.updateWithCache(currentGroup);
		return Bean.toModel(currentGroup, groupModel);
	}

	@Override
	public GroupModel getGroup(String agencyCode, String groupCode) throws Exception {
		GroupModel result = Bean.toModel(groupDAO.findGroup(agencyCode, groupCode), new GroupModel());
		this.setHasChildren(result);
		return result;
	}

	@Override
	public List<GroupModel> getGroupsByParentCode(String agencyCode, String parentCode) throws Exception {
		List<GroupModel> result = Bean.toModels(groupDAO.findGroupByParentCode(agencyCode, parentCode),GroupModel.class);
		for(GroupModel currentGroup : result){
			this.setHasChildren(currentGroup);
		}
		return result;
	}
	
	private void setHasChildren(GroupModel currentGroup) throws Exception{
		currentGroup.setHasChildren(groupDAO.hasChildrenGroup(currentGroup.getAgencyCode(),currentGroup.getGroupCode()));
	}

	@Override
	public boolean isGroupCodeAvailable(String agencyCode, String groupCode) throws Exception {
		return ValidationUtil.isEmpty(groupDAO.findGroup(agencyCode, groupCode));
	}

	@Override
	public GroupModel buildGruopTree(String agencyCode) throws Exception {
		GroupModel result = new GroupModel();
		result.setNodeLevel(ASIConstants.ROOT_GROUP_LEVEL);
		result.setGroupCode(ASIConstants.ROOT_GROUP_CODE);
		result.setGroupName(ASIConstants.ROOT_GROUP_NAME);
		this.buildChildGroup(agencyCode, result);
		return result;
	}

	private void buildChildGroup(String agencyCode, GroupModel parentGroup) throws Exception {
		List<GroupModel> children = Bean.toModels(groupDAO.findGroupByParentCode(agencyCode,parentGroup.getGroupCode()),GroupModel.class);
		if (!ValidationUtil.isEmpty(children)) {
			parentGroup.setHasChildren(true);
			parentGroup.setChildren(children);
			for (GroupModel child : children) {
				this.buildChildGroup(agencyCode,child);
			}
		} else {
			parentGroup.setHasChildren(false);
		}
	}

}
