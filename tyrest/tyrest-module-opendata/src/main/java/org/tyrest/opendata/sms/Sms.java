package org.tyrest.opendata.sms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.tyrest.core.foundation.support.SpringContextHelper;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Sms.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Sms.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class Sms implements InitializingBean{
	
	public static Sms use(String instanceName){
		return SpringContextHelper.getBean(instanceName);
	}
	
	/**
	 * TODO.发送任意内容的短信，只有少部分短信运营商支持
	 * @param content
	 * @throws Exception
	 */
	public abstract void sendSms(String content,String mobile) throws Exception;
	
	/**
	 * TODO.任意短信群发
	 * @param content
	 * @throws Exception
	 */
	public abstract void sendSms(String content,List<String> mobile) throws Exception;
	
	/**
	 * TODO.根据注册过的模板和变量发送短信，比如阿里大鱼
	 * @param templateCode
	 * @param params
	 * @throws Exception
	 */
	public abstract void sendSms(String templateCode,Map<String,String> params,String mobile) throws Exception;
	/**
	 * TODO.模板短信群发
	 * @param templateCode
	 * @param params
	 * @param mobiles
	 * @throws Exception
	 */
	public abstract void sendSms(String templateCode,Map<String,String> params,List<String> mobiles) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/