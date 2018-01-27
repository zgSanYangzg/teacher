package org.tyrest.opendata.push.yunba;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: YunbaMethod.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: YunbaMethod.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum YunbaMethod
{
	/**
	 * 给指定的topic发布消息
	 */
	PUBLISH("publish"),
	/**
	 * 给指定的一个用户发消息
	 */
	PUBLISH_TO_ALIAS("publish_to_alias"),
	/**
	 * 给指定的一组用户发消息
	 */
	PUBLISH_TO_ALIAS_BATCH("publish_to_alias_batch"),
	/**
	 * 给指定的主题异步发消息
	 */
	PUBLISH_ASYNC("publish_async"),
	/**
	 * 检测异步消息的发送情况
	 */
	PUBLISH_CHECK("publish_check");
	
	private String methodName;
	
	private YunbaMethod(String methodName){
		this.methodName = methodName;
	}
	
	public String getValue(){
		return this.methodName;
	}
}

/*
*$Log: av-env.bat,v $
*/