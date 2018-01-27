package org.tyrest.asi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.asi.face.model.ASIBizModel;
import org.tyrest.asi.face.model.GroupColumnModel;
import org.tyrest.asi.face.model.GroupModel;
import org.tyrest.asi.face.orm.dao.ASIBizDAO;
import org.tyrest.asi.face.orm.entity.ASIBiz;
import org.tyrest.asi.face.service.ASIService;
import org.tyrest.asi.face.service.GroupColumnService;
import org.tyrest.asi.face.service.GroupService;
import org.tyrest.asi.face.service.enums.ASIType;
import org.tyrest.asi.service.core.processor.TypeProcessor;
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
 *  File: ASIServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value = "asiService")
public class ASIServiceImpl extends BaseServiceImpl<ASIBizModel, ASIBiz> implements ASIService {

	@Autowired
	private GroupColumnService groupColumnService;

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ASIBizDAO asiBizDAO;

	@Override
	public GroupModel getASIMeta(String agencyCode, String entityType) throws Exception {
		ASIBiz currentASIBiz = asiBizDAO.findASIBiz(agencyCode, entityType);
		if(ValidationUtil.isEmpty(currentASIBiz)) return null;
		
		GroupModel rootGroup = groupService.getGroup(agencyCode, currentASIBiz.getGroupCode());
		if(!ValidationUtil.isEmpty(rootGroup)){
			List<GroupModel> children = groupService.getGroupsByParentCode(agencyCode, rootGroup.getGroupCode());
			if(!ValidationUtil.isEmpty(children)){
				rootGroup.setChildren(children);
				List<GroupColumnModel> currentGroupColumns = null;
				for(GroupModel child : children){
					currentGroupColumns = groupColumnService.getGroupColumns(agencyCode, child.getGroupCode());
					if (!ValidationUtil.isEmpty(currentGroupColumns)) {
						child.setColumns(currentGroupColumns);
					}
				}
			}
		}
		return rootGroup;
	}

	@Override
	public GroupModel getASIData(String agencyCode, String groupCode, String entityType, String entityId)
			throws Exception {
		GroupModel group = groupService.getGroup(agencyCode, groupCode);
		List<GroupColumnModel> columns = groupColumnService.getGroupColumns(agencyCode, groupCode);
		if (!ValidationUtil.isEmpty(group) && !ValidationUtil.isEmpty(columns)) {
			group.setColumns(columns);
			Object values = TypeProcessor.getProcessor(ASIType.getASIType(group.getGroupType())).getValues(agencyCode,
					groupCode, entityType, entityId);
			if (!ValidationUtil.isEmpty(values)) {
				group.setValues(values);
			}
		}
		return group;
	}

	@Override
	public void updateASIData(String agencyCode, String entityType, String entityId,
			Map<String, List<Map<String, String>>> tableValues, Map<String, Map<String, String>> formValues,ASIType asiType)
					throws Exception {
		TypeProcessor.getProcessor(asiType).update(agencyCode, entityType, entityId, tableValues, formValues);
	}

	@Override
	public ASIBizModel upsertASIBiz(ASIBizModel asiBizModel) throws Exception {
		
		if(ValidationUtil.isEmpty(asiBizModel)
				|| ValidationUtil.isEmpty(asiBizModel.getAgencyCode())
				|| ValidationUtil.isEmpty(asiBizModel.getEntityType()))
			throw new BusinessException(MessageConstants.DATA_VALIDATION_FAILED);
		
		ASIBiz currentAsiBiz = asiBizDAO.findASIBiz(asiBizModel.getAgencyCode(), asiBizModel.getEntityType());
		if(ValidationUtil.isEmpty(currentAsiBiz)){
			currentAsiBiz = Bean.toPo(asiBizModel, new ASIBiz());
			currentAsiBiz.setRecDate(new Date());
			currentAsiBiz.setRecStatus(CoreConstants.COMMON_ACTIVE);
			currentAsiBiz.setRecUserId(RequestContext.getExeUserId());
			asiBizDAO.insertWithCache(currentAsiBiz);
		}else{
			currentAsiBiz.setRecDate(new Date());
			asiBizDAO.updateWithCache(currentAsiBiz);
		}
		return Bean.toModel(currentAsiBiz, asiBizModel);
	}

	@Override
	public GroupModel getValues(String agencyCode, String entityType, String entityId) throws Exception {
		
		ASIBiz currentASIBiz = asiBizDAO.findASIBiz(agencyCode, entityType);
		if(ValidationUtil.isEmpty(currentASIBiz)) return null;
		
		GroupModel rootGroup = groupService.getGroup(agencyCode, currentASIBiz.getGroupCode());
		if(!ValidationUtil.isEmpty(rootGroup)){
			List<GroupModel> children = groupService.getGroupsByParentCode(agencyCode, rootGroup.getGroupCode());
			if(!ValidationUtil.isEmpty(children)){
				rootGroup.setChildren(children);
				List<GroupColumnModel> currentGroupColumns = null;
				Object currentValues = null;
				for(GroupModel child : children){
					currentGroupColumns = groupColumnService.getGroupColumns(agencyCode, child.getGroupCode());
					if (!ValidationUtil.isEmpty(currentGroupColumns)) {
						child.setColumns(currentGroupColumns);
						currentValues = TypeProcessor.getProcessor(ASIType.getASIType(child.getGroupType()))
								.getValues(agencyCode,child.getGroupCode(),entityType, entityId);
						if (!ValidationUtil.isEmpty(currentValues)) {
							child.setValues(currentValues);
						}
					}
				}
			}
		}
		return rootGroup;
	}
}
