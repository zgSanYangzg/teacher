package org.tyrest.resource.asi;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.asi.face.model.ASIBizModel;
import org.tyrest.asi.face.model.GroupModel;
import org.tyrest.asi.face.service.ASIService;
import org.tyrest.asi.face.service.enums.ASIType;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ASIResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/asi")
@TyrestResource(module = "asi",value = "ASIResourcesV1", description = "动态表单")
public class ASIResourceV1 extends BaseResources {

    @Autowired
    private ASIService asiService;
    
    @TyrstOperation(name = "upsertASIBiz",ApiLevel = APILevel.AGENCY, description = "将具体的业务类型和动态表单关联")
    @RequestMapping(value = "", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseModel<ASIBizModel> upsertASIBiz(@RequestBody ASIBizModel asiBizModel) throws Exception
    {
        return ResponseHelper.buildResponseModel(asiService.upsertASIBiz(asiBizModel));
    }
    
    @TyrstOperation(name = "updateASIForm",ApiLevel = APILevel.AGENCY, description = "更新表单类型数据")
    @RequestMapping(value = "/{agencyCode}/{entityType}/{entityId}/form", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseModel<String> updateASIForm(
    		@PathVariable(value = "agencyCode" )  String agencyCode,
            @PathVariable(value = "entityType") String entityType,
            @PathVariable(value = "entityId") String entityId,
            @RequestBody Map<String,Map<String, String>> formValues) throws Exception
    {
		asiService.updateASIData(agencyCode, entityType, entityId,null,formValues,ASIType.FORM);
        return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
    }

    @TyrstOperation(name = "updateASITable",ApiLevel = APILevel.AGENCY, description = "更新表格类型数据")
    @RequestMapping(value = "/{agencyCode}/{entityType}/{entityId}/table", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseModel<String> updateASITable(
    		@PathVariable(value = "agencyCode") String agencyCode,
            @PathVariable(value = "entityType") String entityType,
            @PathVariable(value = "entityId") String entityId,
            @RequestBody Map<String, List<Map<String, String>>> tableValues) throws Exception
    {
    	asiService.updateASIData(agencyCode, entityType, entityId,tableValues,null,ASIType.TABLE);
        return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
    }
    
    @TyrstOperation(name = "getValuesForGroup",ApiLevel = APILevel.AGENCY, description = "获取某个具体的子分组的元数据和数据(异步根据子分组获取)")
    @RequestMapping(value = "/{agencyCode}/{groupCode}/{entityType}/{entityId}/values", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<GroupModel> getValuesForGroup(
    		 @PathVariable(value = "agencyCode") String agencyCode,
             @PathVariable(value = "groupCode") String groupCode,
             @PathVariable(value = "entityType") String entityType,
             @PathVariable(value = "entityId") String entityId) throws Exception
    {
        return ResponseHelper.buildResponseModel(asiService.getASIData(agencyCode, groupCode, entityType, entityId));
    }
	
    @TyrstOperation(name = "getValues",ApiLevel = APILevel.AGENCY, description = "获取实体的动态表单数据(同步获取所有数据)")
    @RequestMapping(value = "/{agencyCode}/{entityType}/{entityId}/values", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<GroupModel> getValues(
    		 @PathVariable(value = "agencyCode") String agencyCode,
             @PathVariable(value = "entityType") String entityType,
             @PathVariable(value = "entityId") String entityId) throws Exception
    {
        return ResponseHelper.buildResponseModel(asiService.getValues(agencyCode, entityType, entityId));
    }
	
    @TyrstOperation(name = "getASIMeta",ApiLevel = APILevel.AGENCY, description = "获取实体的元数据定义")
    @RequestMapping(value = "/{agencyCode}/{entityType}/meta", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<GroupModel> getASIMeta(
    		 @PathVariable(value = "agencyCode") String agencyCode,
             @PathVariable(value = "entityType") String entityType) throws Exception
    {
        return ResponseHelper.buildResponseModel(asiService.getASIMeta(agencyCode, entityType));
    }
}


