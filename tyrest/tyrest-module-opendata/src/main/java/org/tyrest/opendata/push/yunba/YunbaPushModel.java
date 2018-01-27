package org.tyrest.opendata.push.yunba;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: YunbaPushModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: YunbaPushModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class YunbaPushModel implements Serializable
{
	private static final long serialVersionUID = 4793252962814065722L;
	
	private String method;
	private String appkey;
	private String seckey;
	private String topic;
	private String alias;
	private List<String> aliases;
	private String msg;
	private JSONObject opts;
	
	public YunbaPushModel(){}
	
	public YunbaPushModel(String appkey,String seckey){
		this.appkey = appkey;
		this.seckey = seckey;
	}
	
	public JSONObject getOpts()
	{
		return opts;
	}
	public void setOpts(JSONObject opts)
	{
		this.opts = opts;
	}
	public String getMethod()
	{
		return method;
	}
	public void setMethod(String method)
	{
		this.method = method;
	}
	public String getAppkey()
	{
		return appkey;
	}
	public void setAppkey(String appkey)
	{
		this.appkey = appkey;
	}
	public String getSeckey()
	{
		return seckey;
	}
	public void setSeckey(String seckey)
	{
		this.seckey = seckey;
	}
	public String getTopic()
	{
		return topic;
	}
	public void setTopic(String topic)
	{
		this.topic = topic;
	}
	public String getAlias()
	{
		return alias;
	}
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public List<String> getAliases()
	{
		return aliases;
	}

	public void setAliases(List<String> aliases)
	{
		this.aliases = aliases;
	}
}

/*
*$Log: av-env.bat,v $
*/