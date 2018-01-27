package org.tyrest.opendata.push.yunba;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tyrest.opendata.push.Pusher;

import net.sf.json.JSONObject;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: YunbaPusher.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: YunbaPusher.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "yunbaPusher")
public class YunbaPusher extends Pusher
{
	private static final String IOS_NOTIFICATION_KEY_APS = "aps";
	private static final String IOS_NOTIFICATION_KEY_ALERT = "alert";
	private static final String IOS_NOTIFICATION_KEY_BADGE = "badge";
	private static final int IOS_NOTIFICATION_VALUE_BADGE = 0;
	private static final String IOS_NOTIFICATION_KEY_SOUND = "sound";
	private static final String IOS_NOTIFICATION_VALUE_SOUND = "bingbong.aiff";
	private static final String YUNBA_POST_PARAM_APNJSON = "apn_json";
	
	@Value("${yunbaUrl}")
	private String YunbaUrl;
	
	@Value("${yunbaAppkey}")
	private String yunbaAppkey;
	
	@Value("${yunbaSeckey}")
	private String yunbaSeckey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void push(String topic,String title, String content, Map<String, String> extra) throws Exception {
		YunbaPushModel yunbaPushModel = new YunbaPushModel(this.yunbaAppkey, this.yunbaSeckey);
		yunbaPushModel.setMethod(YunbaMethod.PUBLISH.getValue());
		yunbaPushModel.setTopic(topic);
		this.doPush(yunbaPushModel, title, content, extra);
	}

	@Override
	public void push(String topic,String alias, String title, String content, Map<String, String> extra) throws Exception {
		YunbaPushModel yunbaPushModel = new YunbaPushModel(this.yunbaAppkey, this.yunbaSeckey);
		yunbaPushModel.setMethod(YunbaMethod.PUBLISH_TO_ALIAS.getValue());
		yunbaPushModel.setAlias(alias);
		this.doPush(yunbaPushModel, title, content, extra);
	}

	@Override
	public void push(String topic,List<String> aliases, String title, String content, Map<String, String> extra)
			throws Exception {
		YunbaPushModel yunbaPushModel = new YunbaPushModel(this.yunbaAppkey, this.yunbaSeckey);
		yunbaPushModel.setMethod(YunbaMethod.PUBLISH_TO_ALIAS_BATCH.getValue());
		yunbaPushModel.setAliases(aliases);
		this.doPush(yunbaPushModel, title, content, extra);
	}
	
	private void doPush(YunbaPushModel yunbaPushModel,String title,String content,Map<String, String> extra) throws Exception{
		//构造给移动端推送的消息数据
		Map<String,String> realMessage = new HashMap<String,String>();
		realMessage.putAll(extra);
		yunbaPushModel.setMsg(JSONObject.fromObject(realMessage).toString());
		//构造ios apn数据
		Map<String,Object> aps = new HashMap<String,Object>();
		aps.put(IOS_NOTIFICATION_KEY_ALERT,content);
		aps.put(IOS_NOTIFICATION_KEY_BADGE,IOS_NOTIFICATION_VALUE_BADGE);
		aps.put(IOS_NOTIFICATION_KEY_SOUND,IOS_NOTIFICATION_VALUE_SOUND);
		aps.putAll(extra);
		
		JSONObject apn_json = new JSONObject();
		apn_json.put(IOS_NOTIFICATION_KEY_APS, aps);
		JSONObject opts = new JSONObject();
		opts.put(YUNBA_POST_PARAM_APNJSON,apn_json);
		yunbaPushModel.setOpts(opts);
		
		HttpHeaders requestHeader = new HttpHeaders();
		requestHeader.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(JSONObject.fromObject(yunbaPushModel).toString(), requestHeader);
		ResponseEntity<String> response = restTemplate.exchange(this.YunbaUrl,HttpMethod.POST,requestEntity,String.class,new HashMap<>());
		JSONObject responseObject = JSONObject.fromObject(response.getBody());
		//获取http响应，如果消息推送失败，解析失败原因
		int yunbaStatusCode = responseObject.getInt("status");
		if(yunbaStatusCode != 0){
			throw new Exception(YunbaHttpStatus.getStatusByCode(yunbaStatusCode).toString());
		}
		System.out.println("message has been pushed successfully!");		
	}
	
}

/*
*$Log: av-env.bat,v $
*/