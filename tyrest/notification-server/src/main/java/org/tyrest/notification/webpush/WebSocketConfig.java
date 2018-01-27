package org.tyrest.notification.webpush;
/*package com.tk.framework.websocket;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
*//**
 * websocket配置类,取代了配置文件,spring官方比较推荐的一种配置方式，
 * 一期的websocket消息推送先使用配置文件,配置文件不支持sockjs,如果后期websocket客户端
 * 硬件水平不能满足的情况下再启用该类，以便支持sockjs模拟的websocket
 * @author wuqiang
 *
 *//*
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
        WebSocketConfigurer {
	
	@Resource(name="handShakeinterceptor")
	private HandshakeInterceptor handShakeinterceptor;
	
	@Resource(name="systemWebSocketHandler")
	private WebSocketHandler systemWebSocketHandler;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler, "/websocket").addInterceptors(handShakeinterceptor);
        registry.addHandler(systemWebSocketHandler, "/sockjs/websocket").addInterceptors(handShakeinterceptor).withSockJS();
        System.out.println("websocket module has been registed successfully!");
    }

}
*/