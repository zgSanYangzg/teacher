package org.tyrest.notification.mobilepush;

import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.notification.consumer.JMSMessageHandler;
import org.tyrest.notification.face.model.MobilePushModel;
import org.tyrest.notification.face.typedef.Notification;
import org.tyrest.opendata.push.Pusher;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: EmailPushMessageHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: EmailPushMessageHandler.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value="MobilePushHandler")
public class MobileEndMessageHandler extends JMSMessageHandler{
	
	@Override
	protected void handleMessage(Notification notificaiton) throws Exception {
		Pusher pusher = Pusher.use("wxxcxPusher");
		MobilePushModel mobilePushModel = (MobilePushModel)notificaiton.body();
		String title = mobilePushModel.getMessageTitle();
		String content = mobilePushModel.getMessageContent();
		Map<String,String> extra = new HashMap<String,String>();
		extra.put("eventCode", mobilePushModel.getEventCode());
		extra.put("entityType", mobilePushModel.getEntityType());
		extra.put("entityId", mobilePushModel.getEntityId().toString());
		if(!ValidationUtil.isEmpty(mobilePushModel.getTopic())){
			pusher.push(mobilePushModel.getTopic(), title, content, extra);
		}else if(!ValidationUtil.isEmpty(mobilePushModel.getAlias())){
			pusher.push(mobilePushModel.getTopic(),mobilePushModel.getAlias(), title, content, extra);
		}else if(!ValidationUtil.isEmpty(mobilePushModel.getAliases())){
			pusher.push(mobilePushModel.getTopic(), mobilePushModel.getAliases(), title, content, extra);
		}
	}
}

/*
*$Log: av-env.bat,v $
*/