package org.tyrest.security.service;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.security.face.orm.dao.OperationDAO;
import org.tyrest.security.face.service.OperationService;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.orm.entity.Operation;
import org.tyrest.security.face.model.OperationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityResourceServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityResourceServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
@Service(value="operationService")
public class OperationServiceImpl extends BaseServiceImpl<OperationModel, Operation> implements OperationService
{

	@Autowired
	OperationDAO operationDAO;
	
	@Override
	public List<OperationModel> createOperations(List<OperationModel> operationModels) throws Exception {
		//删除所有的权限信息
		operationDAO.deleteAllResource();
		//保存API所有的权限信息
		List<OperationModel> returnOperationModels = new ArrayList<OperationModel>();
		//保存全部的资源信息	
		for(OperationModel operationModel:operationModels){
			Operation operation = this.prepareEntity(operationModel);
			operation.setLockStatus(CoreConstants.COMMON_N);
			operationDAO.insert(operation);
			returnOperationModels.add(Bean.toModel(operation, operationModel));
		}
		return returnOperationModels;
	}

	@Override
	public OperationModel createOperation(OperationModel operationModel) throws Exception {
		//数据验证
		if(ValidationUtil.isEmpty(operationModel)
				|| ValidationUtil.isEmpty(operationModel.getResourceCode())
				|| ValidationUtil.isEmpty(operationModel.getResourceName())
				|| ValidationUtil.isEmpty(operationModel.getFuncId())
				|| ValidationUtil.isEmpty(operationModel.getOprateCode())
				|| ValidationUtil.isEmpty(operationModel.getOprateDescription())
				|| ValidationUtil.isEmpty(operationModel.getReqMode())
				|| ValidationUtil.isEmpty(operationModel.getReqUrl())
				|| ValidationUtil.isEmpty(operationModel.getResType())
				|| ValidationUtil.isEmpty(operationModel.getLockStatus())){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		
		Operation operation = this.prepareEntity(operationModel);
		//保存
		operationDAO.insert(operation);
		return Bean.toModel(operation, operationModel);
	}

	@Override
	public void syncOperations(List<OperationModel> operations) throws Exception {
		Operation currentOperation = null;
		for(OperationModel operationModel : operations){
			currentOperation = operationDAO.findByFid(operationModel.getFuncId());
			if(ValidationUtil.isEmpty(currentOperation)){
				currentOperation = this.prepareEntity(operationModel);
				currentOperation.setRecDate(new Date());
				currentOperation.setRecStatus(CoreConstants.COMMON_ACTIVE);
				currentOperation.setRecUserId(CoreConstants.SYSTEM.toString());
				currentOperation.setLockStatus(CoreConstants.COMMON_N);
				operationDAO.insert(currentOperation);
			}else{
				Bean.copyExistPropertis(operationModel, currentOperation);
				currentOperation.setRecDate(new Date());
				operationDAO.update(currentOperation);
			}
		}
	}

	@Override
	public List<Object> getAllResource() throws Exception {
		List<Object> returnList = Redis.get(SecurityConstants.ALL_OPERATION_RESOURCE);
		if(ValidationUtil.isEmpty(returnList)){
			List<Operation> operations =operationDAO.findAllResource();
			Map<String,Object> operationMap = new HashMap<String,Object>();
			Map<String,String> nameMap = new HashMap<String,String>();
			for (Operation operation : operations){
				nameMap.put(operation.getResourceCode(),operation.getResourceName());
				List<Operation> operationList = (List<Operation>)operationMap.get(operation.getResourceCode());
				if (ValidationUtil.isEmpty(operationList)){
					operationList = new ArrayList<Operation>();
				}
				operationList.add(operation);
				operationMap.put(operation.getResourceCode(),operationList);
			}
			returnList = new ArrayList<Object>();
			for(String resourceCode : operationMap.keySet()){
				Object[] objects = new Object[3];
				objects[0] = resourceCode;
				objects[1] = nameMap.get(resourceCode);
				objects[2] = operationMap.get(resourceCode);
				returnList.add(objects);
			}
			Redis.set(returnList,SecurityConstants.ALL_OPERATION_RESOURCE);
		}
		return returnList;
	}
}
