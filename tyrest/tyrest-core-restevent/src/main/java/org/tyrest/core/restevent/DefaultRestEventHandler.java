package org.tyrest.core.restevent;

import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DefaultEventHandler.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:默认的事件处理器实现
 *  TODO
 * 
 *  Notes:
 *  $Id: DefaultEventHandler.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月10日		wuqiang		Initial.
 *
 * </pre>
 */
@Component(value = "defaultEventHandler")
public class DefaultRestEventHandler extends RestEventHandler{

	@Override
	protected void handleEvent(RestEvent restEvent) throws Exception {
		System.out.println(restEvent.getRestEventName() + " event has occured!");
	}


}

/*
*$Log: av-env.bat,v $
*/