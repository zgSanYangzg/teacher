package org.tyrest.notification.face.model;

import java.util.List;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: MobilePushModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: MobilePushModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class MobilePushModel extends AbstractPushModel {

	private static final long serialVersionUID = 1L;

	private String topic;// 消息频道,用于给订阅了此频道的app用户推消息

	private String alias;// 用于给指定的一个app用户推消息

	private List<String> aliases;// 用于给指定的一组用户推消息

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

}

/*
 * $Log: av-env.bat,v $
 */