package org.tyrest.resource.asi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.asi.face.model.ColumnTemplateModel;
import org.tyrest.asi.face.model.GroupColumnModel;
import org.tyrest.asi.face.service.ColumnTemplateService;
import org.tyrest.asi.face.service.GroupColumnService;
import org.tyrest.asi.face.service.enums.DataType;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.model.Page;
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
 *  File: ASIColumnResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIColumnResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/asi/column")
@TyrestResource(module = "asi",value = "ASIColumnResourceV1", description = "动态表单列定义管理")
public class ASIColumnResourceV1  extends BaseResources {
	
	@Autowired
	private ColumnTemplateService columnTemplateService;
	
	@Autowired
	private GroupColumnService groupColumnService;
	
	@TyrstOperation(name = "getAllDataTypes",ApiLevel = APILevel.SUPERADMIN, description = "获取所有定义的数据类型")
    @RequestMapping(value = "/datatypes", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<List<Map<String,String>>> getAllDataTypes() throws Exception{
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> datatype = null;
		for(DataType d : DataType.values()){
			datatype = new HashMap<String,String>();
			datatype.put("name",d.description());
			datatype.put("value",d.name());
			result.add(datatype);
		}
        return ResponseHelper.buildResponseModel(result);
    }
	
	@TyrstOperation(name = "createColumnTemplate",ApiLevel = APILevel.SUPERADMIN, description = "创建列定义模板")
    @RequestMapping(value = "/template/{agencyCode}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseModel<ColumnTemplateModel> createColumnTemplate(
    		@PathVariable String agencyCode,@RequestBody ColumnTemplateModel columnTemplate) throws Exception{
		columnTemplate.setAgencyCode(agencyCode);
        return ResponseHelper.buildResponseModel(columnTemplateService.createColumnTemplate(columnTemplate));
    }
    
	@TyrstOperation(name = "deleteColumnTemplate",ApiLevel = APILevel.SUPERADMIN, description = "删除列定义模板")
    @RequestMapping(value = "/template/{agencyCode}/{columnCode}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseModel<String> deleteColumnTemplate(
    		@PathVariable String agencyCode,@PathVariable String columnCode) throws Exception{
    	 columnTemplateService.deleteColumnTemplate(agencyCode, columnCode);
    	 return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
    }
    
	@TyrstOperation(name = "updateASIColulnMeta",ApiLevel = APILevel.SUPERADMIN, description = "更新列定义模板")
    @RequestMapping(value = "/template/{agencyCode}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseModel<ColumnTemplateModel> updateASIColulnMeta(@PathVariable String agencyCode,
    		@RequestBody ColumnTemplateModel columnTemplate) throws Exception{
		columnTemplate.setAgencyCode(agencyCode);
        return ResponseHelper.buildResponseModel(columnTemplateService.updateColumnTemplate(columnTemplate));
    }
	
	@TyrstOperation(name = "getColumnTemplate",ApiLevel = APILevel.SUPERADMIN, description = "获取列定义模板详情")
    @RequestMapping(value = "/template/{agencyCode}/{columnCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<ColumnTemplateModel> getColumnTemplate(
    		@PathVariable String agencyCode,@PathVariable String columnCode) throws Exception{
        return ResponseHelper.buildResponseModel(columnTemplateService.getColumnTemplate(agencyCode, columnCode));
    }
	
	@TyrstOperation(name = "getASIColulnMetas",ApiLevel = APILevel.SUPERADMIN, description = "分页获取列定义模板列表")
    @RequestMapping(value = "/template/{agencyCode}/page", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<Page> getASIColulnMetas(
    		@PathVariable String agencyCode,
    		@RequestParam(value = ParamConstants.OFFSET,defaultValue = "0") int start,
			@RequestParam(value = ParamConstants.LENGTH,defaultValue = "10") int length,
			@RequestParam(value = ParamConstants.SIDX, required = false) String orderBy,
			@RequestParam(value = ParamConstants.SORT, required = false) String order,
			@RequestParam(value="columnCode",required=false) String columnCode,
			@RequestParam(value="columnName",required=false) String columnName
			) throws Exception{
        return ResponseHelper.buildResponseModel(
        		columnTemplateService.getColumnTemplateByPage(
        				agencyCode, columnCode, columnName, new Page(length, start), orderBy, order));
    }
    
	@TyrstOperation(name = "isColumnTemplateCodeAvailable",ApiLevel = APILevel.SUPERADMIN, description = "判断模板列的编码是否可用")
    @RequestMapping(value = "/template/{agencyCode}/{columnCode}/available", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<Boolean> isColumnTemplateCodeAvailable(
    		@PathVariable String agencyCode,@PathVariable String columnCode) throws Exception{
        return ResponseHelper.buildResponseModel(columnTemplateService.isColumnCodeAvailable(agencyCode, columnCode));
    }
	
	@TyrstOperation(name = "upsertGroupColumn",ApiLevel = APILevel.AGENCY, description = "创建/更新子分组的自定义列")
    @RequestMapping(value = "/custom/{agencyCode}/{groupCode}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseModel<GroupColumnModel> upsertGroupColumn(
    		@PathVariable String agencyCode,
    		@PathVariable String groupCode,
    		@RequestBody GroupColumnModel groupColumn) throws Exception{
		groupColumn.setAgencyCode(agencyCode);
		groupColumn.setGroupCode(groupCode);
        return ResponseHelper.buildResponseModel(groupColumnService.createGroupColumn(groupColumn));
    }
    
	@TyrstOperation(name = "deleteGroupColumn",ApiLevel = APILevel.AGENCY, description = "删除子分组的自定义列")
    @RequestMapping(value = "/custom/{agencyCode}/{groupCode}/{columnCode}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseModel<String> deleteGroupColumn(
    		@PathVariable String agencyCode,
    		@PathVariable String groupCode,
    		@PathVariable String columnCode) throws Exception{
    	groupColumnService.deleteGroupColumn(agencyCode, groupCode, columnCode);
        return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
    }
	
	@TyrstOperation(name = "getGroupColumn",ApiLevel = APILevel.AGENCY, description = "获取自定义列详情")
    @RequestMapping(value = "/custom/{agencyCode}/{groupCode}/{columnCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<GroupColumnModel> getGroupColumn(
    		@PathVariable String agencyCode,
    		@PathVariable String groupCode,
    		@PathVariable String columnCode) throws Exception{
        return ResponseHelper.buildResponseModel(groupColumnService.getGroupColumn(agencyCode, groupCode, columnCode));
    }
	
	@TyrstOperation(name = "getGroupColumns",ApiLevel = APILevel.AGENCY, description = "根据子分组获取列定义列表")
    @RequestMapping(value = "/custom/{agencyCode}/{groupCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<List<GroupColumnModel>> getGroupColumns(
    		@PathVariable String agencyCode,
    		@PathVariable String groupCode) throws Exception{
        return ResponseHelper.buildResponseModel(groupColumnService.getGroupColumns(agencyCode, groupCode));
    }
	
	@TyrstOperation(name = "copyASIColumns",ApiLevel = APILevel.AGENCY, description = "将列定义模板复制到子分组中")
    @RequestMapping(value = "/custom/replication/{agencyCode}/{groupCode}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseModel<String> copyASIColumns(
    		@PathVariable String agencyCode,
    		@PathVariable String groupCode,
    		@RequestBody List<Map<String,String>> templates) throws Exception{
		groupColumnService.copyFromTemplate(agencyCode, groupCode,templates);
        return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
    }
	
	@TyrstOperation(name = "isGroupColumnCodeAvailable",ApiLevel = APILevel.AGENCY, description = "判断自定义列编码是否可用")
    @RequestMapping(value = "/custom/{agencyCode}/{groupCode}/{columnCode}/available", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<Boolean> isGroupColumnCodeAvailable(
    		@PathVariable String agencyCode,
    		@PathVariable String groupCode,
    		@PathVariable String columnCode) throws Exception{
        return ResponseHelper.buildResponseModel(groupColumnService.isGroupColumnCodeAvailable(agencyCode, groupCode, columnCode));
    }
}

/*
*$Log: av-env.bat,v $
*/