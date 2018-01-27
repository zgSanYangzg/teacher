package org.tyrest.notification.webpush;

import org.springframework.web.socket.WebSocketSession;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: NamedWebSocketSession.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: NamedWebSocketSession.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class NamedWebSocketSession {
	private String agencyCode;

	private Long userId;

	private WebSocketSession webSocketSession;

	public NamedWebSocketSession(String agencyCode, Long userId, WebSocketSession webSocketSession) {
		super();
		this.agencyCode = agencyCode;
		this.userId = userId;
		this.webSocketSession = webSocketSession;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public WebSocketSession getWebSocketSession() {
		return webSocketSession;
	}

	public void setWebSocketSession(WebSocketSession webSocketSession) {
		this.webSocketSession = webSocketSession;
	}

	public String toString() {
		return "{agencyCode:" + this.getAgencyCode() + ",userId:" + this.getUserId() + ",sessionInfo:"
				+ this.getWebSocketSession() + "}";
	}
}
