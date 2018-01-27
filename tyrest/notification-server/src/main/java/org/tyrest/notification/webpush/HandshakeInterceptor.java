package org.tyrest.notification.webpush;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.tyrest.core.foundation.utils.ValidationUtil;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: HandshakeInterceptor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: HandshakeInterceptor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value="handShakeinterceptor")
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		/* 握手之前解析连接请求,获取当前握手请求的agencyCode以及userId,用于唯一标示一个websocket连接
		 * 方便后续的消息推送。*/
		String queryString = request.getURI().getQuery();
		String agencyCode = null;
		Long userId = null;
		if (!ValidationUtil.isEmpty(queryString))
		{
			for (String keyValue : queryString.split("&"))
			{
				String[] pair = keyValue.split("=");
				if (pair[0].equals("agencyCode"))
				{
					agencyCode = pair[1];
				}
				if(pair[0].equals("userId")){
					userId = Long.parseLong(pair[1]);
				}
			}
		}
		//如果解析请求获取到了agencyCode和userId,那就接受握手请求，握手成功,否则握手失败
		if (!ValidationUtil.isEmpty(agencyCode) && !ValidationUtil.isEmpty(userId))
		{
			attributes.put("agencyCode", agencyCode.toUpperCase());
			attributes.put("userId", userId);
			return super.beforeHandshake(request, response, wsHandler, attributes);
		}
		else
		{
			return false;
		}
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
