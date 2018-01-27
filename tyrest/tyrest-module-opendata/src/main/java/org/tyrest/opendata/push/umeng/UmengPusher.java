package org.tyrest.opendata.push.umeng;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.opendata.push.Pusher;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: UmengPusher.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: UmengPusher.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "umengPusher")
public class UmengPusher extends Pusher{
	
	@Autowired
	private UmengPushClient umengPushClient;
	
	@Override
	public void push(String topic, String title, String content, Map<String, String> extra) throws Exception {
		umengPushClient.broadcast(title,title,title,null, null, null, null,extra);
	}

	@Override
	public void push(String topic, String alias, String title, String content, Map<String, String> extra)
			throws Exception {
		umengPushClient.multicast(Arrays.asList(alias),title,title,title,null, null, null, null,extra);
	}

	@Override
	public void push(String topic, List<String> aliases, String title, String content, Map<String, String> extra)
			throws Exception {
		umengPushClient.multicast(aliases,title,title,title,null, null, null, null,extra);
	}
}

/*
*$Log: av-env.bat,v $
*/