package org.tyrest.notification.webpush;

import java.io.IOException;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.notification.consumer.JMSMessageHandler;
import org.tyrest.notification.face.model.WebPushModel;
import org.tyrest.notification.face.typedef.Notification;

import net.sf.json.JSONObject;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: WebEndMessageHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WebEndMessageHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "WebPushHandler")
public class WebEndMessageHandler extends JMSMessageHandler {
	@Override
	protected void handleMessage(Notification notification) throws Exception {
		WebPushModel webPushModel = (WebPushModel) notification.body();
		this.push2Web(webPushModel);
	}

	public void push2Web(WebPushModel webPushModel) throws IOException, JMSException {
		webPushModel.setMessageContent(ValidationUtil.isEmpty(webPushModel.getMessageContent())
				? "there is no message!" : webPushModel.getMessageContent());
		if (!ValidationUtil.isEmpty(webPushModel.getAgencyCode())) {
			// 根据agencyCode获取websocket连接，因为目前系统的agencyCode不区分大小写,所以取得websocket连接时候统一使用大写
			List<NamedWebSocketSession> sessions = SystemWebSocketHandler.clients
					.get(webPushModel.getAgencyCode().toUpperCase());
			// 如果当前没有用户连接到服务器，则直接返回
			if (ValidationUtil.isEmpty(sessions)) return;

			JSONObject realMessage = new JSONObject();
			realMessage.put("eventCode", webPushModel.getEventCode());
			realMessage.put("messageContent", webPushModel.getMessageContent());
			// 1#如果userids为空，说明是给商家的全部用户发消息
			if (ValidationUtil.isEmpty(webPushModel.getUserIds())) {
				WebSocketSession currentSession = null;
				for (NamedWebSocketSession session : sessions) {
					currentSession = session.getWebSocketSession();
					if (currentSession.isOpen()) {
						currentSession.sendMessage(new TextMessage(realMessage.toString()));
					}
				}
				// 2#如果不为空，说明是给商家的指定用户发消息
			} else {
				List<Long> pushUserIds = webPushModel.getUserIds();
				WebSocketSession currentSession = null;
				for (NamedWebSocketSession session : sessions) {
					currentSession = session.getWebSocketSession();
					if (pushUserIds.contains(session.getUserId()) && currentSession.isOpen()) {
						currentSession.sendMessage(new TextMessage(realMessage.toString()));
					}
				}
			}
		}
	}
}
