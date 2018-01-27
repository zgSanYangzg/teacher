package org.tyrest.core.restevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.support.SpringContextHelper;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: freeapisEventHandler.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:系统业务事件抽象处理器
 *  TODO
 * 
 *  Notes:
 *  $Id: freeapisEventHandler.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月10日		wuqiang		Initial.
 *
 * </pre>
 */
public abstract class RestEventHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestEventHandler.class);

	private static final String DEFAULT_EVENT_HANDLER_BEAN_NAME = "defaultEventHandler";

	private static final String DEFAULT_BEAN_NAME_SUFIX = "EventHandler";

	/**
	 * 根据事件类型获取相应的事件处理器实例 TODO.
	 *
	 * @param restEvent
	 * @return
	 */
	public static RestEventHandler getEventHandler(RestEvent restEvent) {
		String beanNamePrefix = restEvent.getRestEventName();
		RestEventHandler eventHandler = null;
		try {
			eventHandler = (RestEventHandler) SpringContextHelper
					.getBean(restEvent.getRestEventName() + DEFAULT_BEAN_NAME_SUFIX);
		} catch (NoSuchBeanDefinitionException e) {
			eventHandler = (RestEventHandler) SpringContextHelper.getBean(DEFAULT_EVENT_HANDLER_BEAN_NAME);
			logger.warn("not found EventHandler for this event : {} the DefaultEventHandler will be used!",
					beanNamePrefix);
		}
		return eventHandler;
	}

	/**
	 * TODO.用于处理系统的业务事件
	 *
	 * @param restEvent
	 * @throws Exception
	 */
	protected abstract void handleEvent(RestEvent restEvent) throws Exception;

	/**
	 * 绑定事件元数据
	 * 
	 * @param eventSource
	 */
	public static void attachEventSource(String key,Object eventSource) {
		RequestContext.setAttribute(key, eventSource);
	}

	/**
	 * 获取事件元数据
	 * 
	 * @return
	 */
	public static Object obtainEventSource(String key) {
		return RequestContext.getAttribute(key);
	}

}

/*
 * $Log: av-env.bat,v $
 */