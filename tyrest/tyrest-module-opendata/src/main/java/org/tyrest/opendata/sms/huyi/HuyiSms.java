package org.tyrest.opendata.sms.huyi;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.opendata.sms.Sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: HuyiSms.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: HuyiSms.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "huyiSms")
public class HuyiSms extends Sms{
	
	@Value("${sms.huyi.url}")
	private String huyiUrl;
	
	@Value("${sms.huyi.account}")
	private String huyiAccount;
	
	@Value("${sms.huyi.password}")
	private String huyiPassword;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(ValidationUtil.isEmpty(huyiUrl)
				|| ValidationUtil.isEmpty(huyiAccount)
				|| ValidationUtil.isEmpty(huyiPassword)){
			throw new BeanCreationException("互亿无线短信配置校验失败!");
		}
	}
	
	@Override
	public void sendSms(String content, String mobile) throws Exception {
		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		formData.add("account", huyiAccount);
		formData.add("password", huyiPassword);
		formData.add("content", content);
		formData.add("mobile", mobile);
		this.executeSendSms(formData,requestHeader);
	}
	
	@Override
	public void sendSms(String content,List<String> mobile) throws Exception {
		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		formData.add("account", huyiAccount);
		formData.add("password", huyiPassword);
		formData.add("content", content);
		formData.add("mobile", StringUtils.join(mobile, ','));
		this.executeSendSms(formData,requestHeader);
	}
	
	private void executeSendSms(MultiValueMap<String, String> formData,HttpHeaders requestHeader) throws Exception{
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(formData, requestHeader);
		ResponseEntity<String> response = restTemplate.exchange(this.huyiUrl,HttpMethod.POST,requestEntity,String.class,new HashMap<>());
		String xmlString = response.getBody();
		if (xmlString != null) {
			Document doc = Jsoup.parse(xmlString);
			Element code = doc.getElementsByTag("code").first();
			Element msg = doc.getElementsByTag("msg").first();
			String codeNum = code.text();
			if (!codeNum.equals("2")) {
				throw new Exception("短信发送失败：返回码：" + codeNum + ";返回信息：" + msg.text());
			}
		}	
	}

	@Override
	public void sendSms(String templateCode, Map<String, String> params, List<String> mobiles) throws Exception {
		throw new BusinessException("互亿无线不支持使用模板编码发送短信!");
	}
	
	@Override
	public void sendSms(String templateCode, Map<String, String> params, String mobile) throws Exception {
		throw new BusinessException("互亿无线不支持使用模板编码发送短信!");
	}
}

/*
*$Log: av-env.bat,v $
*/