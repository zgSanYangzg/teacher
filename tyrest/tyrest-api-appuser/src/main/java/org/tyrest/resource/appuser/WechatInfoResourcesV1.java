package org.tyrest.resource.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
import org.tyrest.publicuser.face.model.WechatInfoModel;
import org.tyrest.publicuser.face.service.WechatInfoService;

@RestController
@RequestMapping(value = "/1/wechatinfo")
@TyrestResource(module = "appuser",value = "WechatInfoResourcesV1", description = "微信公众号粉丝信息管理")
public class WechatInfoResourcesV1 extends BaseResources
{
	@Autowired
	private WechatInfoService wechatInfoService;
	
	@TyrstOperation(name = "createWechatInfo", ApiLevel = APILevel.ALL,  description = "添加一条微信粉丝信息")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseModel<WechatInfoModel> createWechatInfo(@RequestBody WechatInfoModel wechatInfoModel) throws Exception{
		wechatInfoService.createWechatInfo(wechatInfoModel);
		return ResponseHelper.buildResponseModel(wechatInfoModel);
	}
	
	@TyrstOperation(name = "updateWechatInfo", ApiLevel = APILevel.ALL,  description = "更新一条微信粉丝信息")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseModel<WechatInfoModel> updateWechatInfo(@RequestBody WechatInfoModel wechatInfoModel,@PathVariable  Long  userId) throws Exception{
		wechatInfoModel.setUserId(userId);
		wechatInfoService.updateWechatInfo(wechatInfoModel);
		return  ResponseHelper.buildResponseModel(wechatInfoModel);
	}
	
	@TyrstOperation(name = "getByOpenId", ApiLevel = APILevel.ALL,  description = "获取一条微信粉丝信息")
	@RequestMapping(value = "/{openId}", method = RequestMethod.GET)
	public ResponseModel<WechatInfoModel> getByOpenId(@PathVariable String openId) throws Exception{
		return  ResponseHelper.buildResponseModel(wechatInfoService.getByOpenId(openId));
	}
}
/*
 * $Log: av-env.bat,v $
 */