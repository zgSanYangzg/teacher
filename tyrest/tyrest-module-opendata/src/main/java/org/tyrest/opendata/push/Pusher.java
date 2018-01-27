package org.tyrest.opendata.push;

import java.util.List;
import java.util.Map;

import org.tyrest.core.foundation.support.SpringContextHelper;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Pusher.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Pusher.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class Pusher {
	
	public static Pusher use(String instanceName){
		return SpringContextHelper.getBean(instanceName);
	}
	/**
	 * TODO.给所有的移动端用户推送消息
	 * @param title
	 * @param content
	 * @param extra
	 * @throws Exception
	 */
	public abstract void push(String topic,String title,String content,Map<String,String> extra) throws Exception;
	/**
	 * TODO.给指定的某个用户推送消息
	 * @param alias
	 * @param title
	 * @param content
	 * @param extra
	 * @throws Exception
	 */
	public abstract void push(String topic,String alias,String title,String content,Map<String,String> extra) throws Exception;
	/**
	 * TODO.给指定的多个用户推送消息
	 * @param aliases
	 * @param title
	 * @param content
	 * @param extra
	 * @throws Exception
	 */
	public abstract void push(String topic,List<String> aliases,String title,String content,Map<String,String> extra) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/