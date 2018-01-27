package org.tyrest.resource.systemctl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.opendata.storage.Storage;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: StorageResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: StorageResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */

@RestController
@RequestMapping(value = "/1/storage")
@TyrestResource(module = "systemctl",value = "StorageResourceV1", description = "存储服务")
public class StorageResourceV1 extends BaseResources {

	@Autowired
	private Storage storage;
	
	@TyrstOperation(name = "flushQiniuToken", ApiLevel = APILevel.ALL,  description = "根据空间获取七牛token",needAuth = false)
	@RequestMapping(value = "/token/{space}", method = RequestMethod.GET)
	public ResponseModel<String> flushQiniuToken(@PathVariable String space) throws Exception {
		return ResponseHelper.buildResponseModel(storage.flushQiniuToken(space));
	}
	
	@TyrstOperation(name = "deleteFile", ApiLevel = APILevel.ALL,  description = "删除文件")
	@RequestMapping(value = "/{space}", method = RequestMethod.DELETE)
	public ResponseModel<String> deleteFile(
			@PathVariable String space,
			@RequestParam(value ="fileName") String fileName) throws Exception {
		storage.deleteFile(space, fileName);
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	}

}
