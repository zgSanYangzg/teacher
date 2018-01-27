package org.tyrest.opendata.push.wxxcx;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.utils.HttpClientHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.opendata.push.Pusher;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: JPusher.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: JPusher.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value="wxxcxPusher")
public class WxxcxPusher extends Pusher
{


	private static final String ACCESS_TOKEN_URL  = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
		/*	"&appid=APPID" +
			"&secret=APPSECRET";*/

	private  static  final  String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

	private static  final  String WX_ACCESS_TOKEN_KEY  = "WX_ACCESS_TOKEN_KEY";


	@Value("${xcx.appId}")
	private static String appId;

	@Value("${xcx.secret}")
	private static String secret;



	
	@Override
	public void push(String topic,String title, String content, Map<String, String> extra) throws Exception {
		throw new BusinessException("不支持的操作.");
	}

	@Override
	public void push(String topic,String alias, String title, String content, Map<String, String> extra) throws Exception {

		//发送微信小程序服务消息

		this.sendMessage(alias,extra);
	}

	@Override
	public void push(String topic,List<String> aliases, String title, String content, Map<String, String> extra)
			throws Exception {
		throw new BusinessException("不支持的操作.");

	}






	public String getAccessToken() throws Exception {
		String accessToken = Redis.get(WX_ACCESS_TOKEN_KEY);
		if(ValidationUtil.isEmpty(accessToken))
		{
			HttpClientHelper helper = new HttpClientHelper();
			String result  = helper.get(ACCESS_TOKEN_URL+"&appid="+appId+"&secret="+secret);
			if(!ValidationUtil.isEmpty(result))
			{
				ObjectMapper mapper = new ObjectMapper();
				try {
					Map<String,Object> resultMap = mapper.readValue(result,Map.class);
					accessToken = (String)resultMap.get("access_token");
					int expires_in = (Integer)resultMap.get("expires_in");
					Redis.setWithExpire(accessToken,Long.valueOf(expires_in-10),WX_ACCESS_TOKEN_KEY);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return accessToken;

	}


	public boolean sendMessage(String  openId,Map<String, String> extr)
	{
		HttpClientHelper helper = new HttpClientHelper();

		boolean returnFlag = false;

		//组装参数
		Map<String, Object> params = new HashMap<>();
		Map<String,Object> value = new HashMap<>();
		Map<String,String> keyword = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();

		int dataCount = Integer.parseInt(extr.get("dataCount"));

		params.put("touser",openId);
		params.put("template_id",extr.get("template_id"));
		params.put("page",extr.get("page"));
		params.put("form_id",extr.get("form_id"));
		for(int i=1;i<=dataCount;i++)
		{
			String keywordStr = "keyword"+i;
			keyword.put("value",extr.get(keywordStr+".value"));
			value.put(keywordStr,keyword);
		}
		params.put("value",value);
		try {

			String result = helper.post(SEND_MESSAGE_URL+this.getAccessToken(), mapper.writeValueAsString(params));

			if(!ValidationUtil.isEmpty(result))
			{
				Map resultMap = mapper.readValue(result,Map.class);
				if(0 == (int)resultMap.get("errcode"))
				{
					returnFlag = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnFlag;
	}



	public static  void main(String [] args)
	{
		appId = "wxccf08eaa87876595";
		secret="24ad467735f1904e552bb8ac69aaf178";

		Map<String, String> extr = new HashMap<>();

		extr.put("openId","o5b750N_mF4rhhrZN_15Q1k1Jpr4");
		extr.put("template_id","kbC81SHwk2pv1aNpNTf26jhgFD0C-EPKcTXEiBaVp3A");
		extr.put("page","");
			extr.put("form_id","1482576616339");
		extr.put("dataCount","4");
		extr.put("keyword1.value","测试信息");
		extr.put("keyword2.value","测试信息");
		extr.put("keyword3.value","测试信息");
		extr.put("keyword4.value","测试信息");

		new WxxcxPusher().sendMessage("o5b750N_mF4rhhrZN_15Q1k1Jpr4",extr);

	}




}
