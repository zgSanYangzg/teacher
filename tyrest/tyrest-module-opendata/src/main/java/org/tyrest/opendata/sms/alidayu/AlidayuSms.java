package org.tyrest.opendata.sms.alidayu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.opendata.sms.Sms;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import net.sf.json.JSONObject;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AlidayuSms.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AlidayuSms.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "alidayuSms")
public class AlidayuSms extends Sms {

	@Value("${sms.alidayu.url}")
	private String alidayuUrl;
	
	@Value("${sms.alidayu.smsType}")
	private String alidayuSmsType;
	
	@Value("${sms.alidayu.appKey}")
	private String alidayuAppkey;
	
	@Value("${sms.alidayu.appSecret}")
	private String alidayuAppSecret;
	
	@Value("${sms.alidayu.smsFreeSignName}")
	private String alidayuSmsFreeSignName;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(ValidationUtil.isEmpty(alidayuUrl)
				|| ValidationUtil.isEmpty(alidayuSmsType)
				|| ValidationUtil.isEmpty(alidayuAppkey)
				|| ValidationUtil.isEmpty(alidayuAppSecret)
				|| ValidationUtil.isEmpty(alidayuSmsFreeSignName)){
			throw new BeanCreationException("阿里大鱼短信配置校验失败!");
		}
	}
	
	@Override
	public void sendSms(String content, String mobile) throws Exception {
		throw new BusinessException("阿里大鱼不支持直接发送短信，请先配置短信模板");
	}
	
	@Override
	public void sendSms(String content, List<String> mobile) throws Exception {
		throw new BusinessException("阿里大鱼不支持直接发送短信，请先配置短信模板");
	}
	
	public void sendSms(String templateCode, Map<String, String> params, String mobile) throws Exception {
		TaobaoClient client = new DefaultTaobaoClient(alidayuUrl,alidayuAppkey,alidayuAppSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		req.setSmsType(alidayuSmsType);
		req.setSmsFreeSignName(alidayuSmsFreeSignName);
		req.setSmsParam(JSONObject.fromObject(params).toString());
		req.setSmsTemplateCode(templateCode);
		req.setRecNum(mobile);
		rsp = client.execute(req);
		if(!rsp.isSuccess()){
			throw new BusinessException(rsp.getMsg());
		}
	};

	@Override
	public void sendSms(String templateCode, Map<String, String> params, List<String> mobiles) throws Exception {
		TaobaoClient client = new DefaultTaobaoClient(alidayuUrl,alidayuAppkey,alidayuAppSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		AlibabaAliqinFcSmsNumSendResponse rsp = null;

		req.setSmsType(alidayuSmsType);
		req.setSmsFreeSignName(alidayuSmsFreeSignName);
		req.setSmsParam(JSONObject.fromObject(params).toString());
		req.setSmsTemplateCode(templateCode);
		
		//群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码
		Page page = new Page(200, 0);
		page.setTotalRows(mobiles.size());
		
		List<String> sendGroup = null;
		for(int i = 1;i <= page.getTotalPages(); i++){
			if(mobiles.size() - i * 200 >= 200){
				sendGroup = mobiles.subList((i - 1) * 200, i * 200); 
			}else{
				sendGroup = mobiles.subList((i - 1) * 200, mobiles.size()); 
			}
			req.setRecNum(StringUtils.join(sendGroup, ','));
			rsp = client.execute(req);
			if(!rsp.isSuccess()){
				throw new BusinessException(rsp.getMsg());
			}
		}
	};
	
	public static void main(String[] args) throws Exception {
		AlidayuSms sender = new AlidayuSms();
		sender.sendSms("SM_12", new HashMap<String,String>(),"18191973206");
	}
}

/*
*$Log: av-env.bat,v $
*/