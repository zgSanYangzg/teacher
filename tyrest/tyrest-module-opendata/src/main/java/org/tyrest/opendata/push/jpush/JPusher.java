package org.tyrest.opendata.push.jpush;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.opendata.push.Pusher;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import net.sf.json.JSONObject;
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
@Component(value="jPusher")
public class JPusher extends Pusher
{
	@Value("${masterSecret}")
	private String masterSecret;
	
	@Value("${appKey}")
	private String appKey;
	
	@Value("${apnsProduction}")
	private Boolean apnsProduction;
	
	private JPushClient jPushClient;
	
	private JPushClient getJPushClient(){
		if(this.jPushClient == null){
			this.jPushClient = new JPushClient(masterSecret,appKey);
		}
		return this.jPushClient;
	}
	
	@Override
	public void push(String topic,String title, String content, Map<String, String> extra) throws Exception {
		Builder payloadBuilder = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios()).setAudience(Audience.all());
		this.doPush(payloadBuilder, title, content, extra);
		
	}

	@Override
	public void push(String topic,String alias, String title, String content, Map<String, String> extra) throws Exception {
		Builder payloadBuilder = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias));
		this.doPush(payloadBuilder, title, content, extra);
	}

	@Override
	public void push(String topic,List<String> aliases, String title, String content, Map<String, String> extra)
			throws Exception {
		Builder payloadBuilder = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios()).setAudience(Audience.alias(aliases));
		this.doPush(payloadBuilder, title, content, extra);
	}
	
	private void doPush(Builder payloadBuilder,String title, String content, Map<String, String> extra) throws Exception{
		payloadBuilder.setNotification(Notification.newBuilder()
                .addPlatformNotification(IosNotification.newBuilder()
                        .setAlert(content)
                        .setBadge(0)
                        .setSound("happy.caf")
                        .addExtras(extra)
                        .build())
                .addPlatformNotification(AndroidNotification.newBuilder()
                		.setAlert(content)
                		.setTitle(title)
                		.addExtras(extra)
                		.build())
                .build())
         .setMessage(Message.content(JSONObject.fromObject(extra).toString()))
         .setOptions(Options.newBuilder()
                 .setApnsProduction(apnsProduction)
                 .build());
		this.getJPushClient().sendPush(payloadBuilder.build());
	}

}
