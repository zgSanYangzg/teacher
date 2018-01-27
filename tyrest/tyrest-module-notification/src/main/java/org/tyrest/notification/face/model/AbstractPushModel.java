package org.tyrest.notification.face.model;

import java.io.Serializable;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AbstractPushModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AbstractPushModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class AbstractPushModel implements Serializable {

	private static final long serialVersionUID = 8325094574702561672L;
	
	private String messageTitle;// 消息标题

	private String messageContent;// 消息内容

	private String eventCode;// 触发消息的事件编码

	private String entityType;// 消息实体类型

	private Long entityId;// 消息代表的实体的ID，根据消息类型决定
	
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

}
