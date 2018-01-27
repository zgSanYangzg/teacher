package org.tyrest.resource.systemctl;


import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
 *  File: SupportResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SupportResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/support")
@TyrestResource(module = "systemctl",value = "SupportResourceV1", description = "系统外围支持资源")
public class SupportResourceV1 extends BaseResources
{
	
	@TyrstOperation(name = "forceUpdateCheck", ApiLevel = APILevel.PUBLIC,  description = "检查app是否需要强制升级",needAuth = false)
	@RequestMapping(value = "/app/isneedforceupdate", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseModel<Map<String,String>> forceUpdateCheck() throws Exception{
		return ResponseHelper.buildResponseModel(null);
	}
}
