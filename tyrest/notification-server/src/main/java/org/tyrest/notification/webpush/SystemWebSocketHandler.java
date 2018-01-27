package org.tyrest.notification.webpush;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: SystemWebSocketHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SystemWebSocketHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value="systemWebSocketHandler")
public class SystemWebSocketHandler implements WebSocketHandler {
	/**
	 * 存放已经连接到websocket服务器的客户终端session,key存放商家编码agencyCode
	 * ,value存放所有属于该商家的终端session,采用线程安全的HashMap,
	 * 现实场景:一个商家店内可能有多个客户端与服务器建立了websocket连接
	 */
	public static final Map<String,List<NamedWebSocketSession>> clients = Collections.synchronizedMap(new HashMap<String,List<NamedWebSocketSession>>());

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	//建立连接之后将session存储起来，agencyCode标示消息推送的group tags,userId标示消息推送的alias
    	Map<String,Object> handShakeAttrs = session.getHandshakeAttributes();
    	String agencyCode = (String)handShakeAttrs.get("agencyCode");
    	Long userId = (Long)handShakeAttrs.get("userId");
    	if(clients.get(agencyCode) != null){
    		clients.get(agencyCode).add(new NamedWebSocketSession(agencyCode, userId, session));
    	}else{
    		List<NamedWebSocketSession> sessions = new ArrayList<NamedWebSocketSession>();
    		sessions.add(new NamedWebSocketSession(agencyCode, userId, session));
    		clients.put(agencyCode,sessions);
    	}
    }

    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
    	//IE11的websocket会自动给服务器发送心跳包，所以这里不处理心跳包
    }

    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        if(wss.isOpen()){
            wss.close();
        }
       System.out.println("websocket connection closed......");
    }

    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
    	//关闭连接之后删除当前的断开的session
    	String agencyCode = (String)wss.getHandshakeAttributes().get("agencyCode");
    	String userId = (String)wss.getHandshakeAttributes().get("userId");
    	List<NamedWebSocketSession> sessions = clients.get(agencyCode);
    	for(NamedWebSocketSession nws : sessions){
    		if(nws.getUserId().equals(userId)){
    			sessions.remove(nws);
    			System.out.println(nws);
    			return;
    		}
    	}
    }

    public boolean supportsPartialMessages() {
        return false;
    }
    
}
