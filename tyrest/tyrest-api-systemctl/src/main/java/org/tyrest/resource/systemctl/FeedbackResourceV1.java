package org.tyrest.resource.systemctl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.systemctl.face.model.FeedbackModel;
import org.tyrest.systemctl.face.service.FeedbackService;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: FeedbackResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: FeedbackResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/feedback")
@TyrestResource(module = "systemctl",value = "FeedBackResourceV1", description = "用户反馈管理")
public class FeedbackResourceV1 extends BaseResources
{

	@Autowired
	private FeedbackService feedbackService;
	
	@TyrstOperation(name = "createFeedBack", ApiLevel = APILevel.PUBLIC,  description = "创建意见反馈")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseModel<FeedbackModel> createFeedBack(@RequestBody FeedbackModel feedBackModel) throws Exception{
		return  ResponseHelper.buildResponseModel(feedbackService.create(feedBackModel));
	}
	
	@TyrstOperation(name = "deleteFeedBack", ApiLevel = APILevel.SUPERADMIN, description = "刪除反馈信息")
	@RequestMapping(value = "", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseModel<String> deleteFeedBack(@RequestBody Long[] ids)throws Exception
	{
		//feedbackService.delete(ids);
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	} 
	
	@TyrstOperation(name = "queryForPage", ApiLevel = APILevel.SUPERADMIN, description = "分頁查詢反馈信息列表。")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseModel<Page> queryForPage(
			@RequestParam(value = ParamConstants.OFFSET,defaultValue = "0") int start,
			@RequestParam(value = ParamConstants.LENGTH,defaultValue = "10") int length,
			@RequestParam(required = false) String contact,
			@RequestParam(required = false) String nickName, 
			@RequestParam(required = false) String recStatus, 
			@RequestParam (value = ParamConstants.SIDX,required = false) String orderBy,
			@RequestParam (value = ParamConstants.SORT,required = false) String order)throws Exception {
		return ResponseHelper.buildResponseModel(
				feedbackService.getByPage(nickName, contact, recStatus, new Page(length,start), orderBy,order));
	}

	@TyrstOperation(name = "queryById", ApiLevel = APILevel.SUPERADMIN, description = "根据ID查询反馈信息。")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseModel<FeedbackModel> queryById(@PathVariable Long id) throws Exception
	{
		return ResponseHelper.buildResponseModel(feedbackService.findById(id));
	}
}
/*
*$Log: av-env.bat,v $
*/