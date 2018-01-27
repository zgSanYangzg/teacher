package org.tyrest.resource.asi;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.asi.face.constants.ASIConstants;
import org.tyrest.asi.face.model.GroupModel;
import org.tyrest.asi.face.service.GroupService;
import org.tyrest.core.foundation.utils.ValidationUtil;
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
 *  File: ASIGroupResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIGroupResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/asi/group")
@TyrestResource(module = "asi",value = "ASIGroupResourceV1", description = "动态表单组管理")
public class ASIGroupResourceV1 extends BaseResources {

    @Autowired
    private GroupService groupService;

    @TyrstOperation(name = "createGroup",ApiLevel = APILevel.AGENCY, description = "创建组定义")
    @RequestMapping(value = "/{agencyCode}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseModel<GroupModel> createGroup(@PathVariable String agencyCode,@RequestBody GroupModel groupModel) throws Exception{
    	groupModel.setAgencyCode(agencyCode);
        return ResponseHelper.buildResponseModel(groupService.createGroup(groupModel));
    }
    
    @TyrstOperation(name = "deleteGroup",ApiLevel = APILevel.AGENCY, description = "删除组定义")
    @RequestMapping(value = "/{agencyCode}/{groupCode}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseModel<GroupModel> deleteGroup(@PathVariable String agencyCode,@PathVariable String groupCode)
					throws Exception{
    	String msg = groupService.deleteGroup(agencyCode, groupCode);
    	if(!ValidationUtil.isEmpty(msg)){
    		return ResponseHelper.validationFailure(msg);
    	}
		return ResponseHelper.buildResponseModel(groupService.buildGruopTree(agencyCode));
    }
    
    @TyrstOperation(name = "updateGroup",ApiLevel = APILevel.AGENCY, description = "更新组定义")
    @RequestMapping(value = "/{agencyCode}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseModel<GroupModel> updateGroup(@PathVariable String agencyCode,@RequestBody GroupModel groupModel) throws Exception{
    	groupModel.setAgencyCode(agencyCode);
        return ResponseHelper.buildResponseModel(groupService.updateGroup(groupModel));
    }
    
    @TyrstOperation(name = "getGroup",ApiLevel = APILevel.AGENCY, description = "获取组定义详情")
    @RequestMapping(value = "/{agencyCode}/{groupCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<GroupModel> getGroup(@PathVariable String agencyCode,@PathVariable String groupCode) throws Exception{
        return ResponseHelper.buildResponseModel(groupService.getGroup(agencyCode, groupCode));
    }
    
    @TyrstOperation(name = "syncGroupTree",ApiLevel = APILevel.AGENCY, description = "同步获取分组树")
    @RequestMapping(value = "/{agencyCode}/tree", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<GroupModel> syncGroupTree(@PathVariable String agencyCode)
					throws Exception{
		return ResponseHelper.buildResponseModel(groupService.buildGruopTree(agencyCode));
    }
	
    @TyrstOperation(name = "getGroupList",ApiLevel = APILevel.AGENCY, description = "根据父分组编码获取分组列表")
    @RequestMapping(value = "/{agencyCode}/list", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<List<GroupModel>> getGroupList(@PathVariable String agencyCode,
			@RequestParam(value = "parentCode",required = true,defaultValue = ASIConstants.ROOT_GROUP_CODE) String parentCode) 
					throws Exception{
		return ResponseHelper.buildResponseModel(groupService.getGroupsByParentCode(agencyCode, parentCode));
    }
	
    @TyrstOperation(name = "isGroupCodeAvailable",ApiLevel = APILevel.AGENCY, description = "判断分组编码是否可用")
    @RequestMapping(value = "/{agencyCode}/{groupCode}/available", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseModel<Boolean> isGroupCodeAvailable(@PathVariable String agencyCode,@PathVariable String groupCode) throws Exception{
        return ResponseHelper.buildResponseModel(groupService.isGroupCodeAvailable(agencyCode, groupCode));
    }
    
}


