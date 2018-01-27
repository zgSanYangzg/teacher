package org.tyrest.resource.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.security.face.model.OperationModel;
import org.tyrest.security.face.service.OperationService;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: OperationResourceV1.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OperationResourceV1.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/operations")
@TyrestResource(module = "security",value = "OperationResourceV1", description = "系统接口资源管理")
public class OperationResourceV1 extends BaseResources
{

	@Autowired
	OperationService operationService;

	@TyrstOperation(name = "createSecurityResource", ApiLevel = APILevel.SUPERADMIN,  description = "创建单个的资源操作信息。")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseModel<OperationModel> createResource(@RequestBody OperationModel resourceModel) throws Exception
	{
		resourceModel = operationService.createOperation(resourceModel);
		return ResponseHelper.buildResponseModel(resourceModel);
	}

	@TyrstOperation(name = "getAllResource", ApiLevel = APILevel.SUPERADMIN,  description = "获取系统所有资源。")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseModel<List<Object>> getAllResource() throws Exception
	{
		List<Object> returnList = operationService.getAllResource();
		return ResponseHelper.buildResponseModel(returnList);
	}



}
/*
 * $Log: av-env.bat,v $
 */