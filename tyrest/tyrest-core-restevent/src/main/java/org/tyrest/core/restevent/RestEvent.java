package org.tyrest.core.restevent;

import org.springframework.context.ApplicationEvent;

/**
 * <pre>
 * 
 *  freeapis
 *  File: freeapisEvent.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:系统中的所有业务事件的定义(如分享应用，首次注册登陆等)
 *  TODO
 * 
 *  Notes:
 *  $Id: freeapisEvent.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月23日		wuqiang		Initial.
 *
 * </pre>
 */
public class RestEvent extends ApplicationEvent
{

	private static final long serialVersionUID = -238780245049547377L;
	
	private String restEventName;
	
	public RestEvent(String restEventName,Object eventSource){
		super(eventSource);
		this.restEventName = restEventName;
	}

	public String getRestEventName()
	{
		return restEventName;
	}
}
