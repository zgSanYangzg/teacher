package org.tyrest.resource.systemctl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.notification.face.model.SmsSendModel;
import org.tyrest.notification.face.model.WebPushModel;
import org.tyrest.notification.producer.Notifier;
import org.tyrest.notification.typedef.Sms;
import org.tyrest.notification.typedef.WebPush;
import org.tyrest.security.face.enums.SMSType;
import org.tyrest.security.face.model.SmsVerifyModel;
import org.tyrest.security.face.service.SmsVerifyService;
import org.tyrest.systemctl.face.constants.DictionaryConstants;
import org.tyrest.systemctl.face.service.DictionaryService;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <pre>
 *
 *  freeapis
 *  File: SmsVerifyResourceV1.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: SmsVerifyResourceV1.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月13日		yangbochao		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/sms")
@TyrestResource(module = "systemctl",value = "SmsVerifyResourceV1", description = "短信验证码")
public class SmsVerifyResourceV1 extends BaseResources
{
	@Autowired
	private SmsVerifyService smsService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private Notifier notifier;
	
	@TyrstOperation(name = "getIdentifyingCode", ApiLevel = APILevel.ALL,description = "获取短信验证码")
	@RequestMapping(value = "/verifyingcode/{smstype}/{mobile}", method = RequestMethod.GET)
	public ResponseModel<String> getIdentifyingCode(
			@PathVariable String smstype,@PathVariable String mobile) throws Exception {
		doSendVerifyCode(smsService.createVerifyingCode(mobile, SMSType.getSMSType(smstype)));
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	}

	@TyrstOperation(name = "checkSmsCode", ApiLevel = APILevel.ALL,description = "验证短信验证码是否正确")
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ResponseModel<Boolean> checkCode(
			@RequestParam String sessionId, @RequestParam String identifyingCode, @RequestParam String mobileNumber)throws Exception {
		return ResponseHelper.buildResponseModel(smsService.verifyCode(sessionId, mobileNumber, identifyingCode));
	}
	
	@TyrstOperation(name = "webPushTest", ApiLevel = APILevel.ALL,description = "web端推送测试",needAuth = false)
	@RequestMapping(value = "/test/webpush", method = RequestMethod.GET)
	public ResponseModel<String> webPushTest()throws Exception {
		WebPushModel webPushModel = new WebPushModel();
		webPushModel.setAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
		webPushModel.setMessageContent("this is a test message!");
		webPushModel.setEventCode("test");
		notifier.notify(new WebPush(webPushModel), WebPush.class.getSimpleName());
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	}
	
	private void doSendVerifyCode(SmsVerifyModel smsVerifyModel) throws Exception {
		String templateCode = dictionaryService.getValue(CoreConstants.CODE_SUPER_ADMIN,
				DictionaryConstants.DICT_CODE_SMS_TEMPLATE,smsVerifyModel.getSmsType());
		String productName = dictionaryService.getValue(CoreConstants.CODE_SUPER_ADMIN,
				DictionaryConstants.DICT_CODE_SYS_PARAMS,DictionaryConstants.DICT_ENTRY_KEY_PRODUCT_NAME);
		Map<String,String> params = new HashMap<String,String>();
		params.put("code", smsVerifyModel.getSmsVerify());
		params.put("product", productName);
		SmsSendModel smsModel = new SmsSendModel();
		smsModel.setMobiles(smsVerifyModel.getMobile());
		smsModel.setTemplateCode(templateCode);
		smsModel.setParams(params);
		notifier.notify(new Sms(smsModel),Sms.class.getSimpleName());
	}
}

/*
 * $Log: av-env.bat,v $
 */