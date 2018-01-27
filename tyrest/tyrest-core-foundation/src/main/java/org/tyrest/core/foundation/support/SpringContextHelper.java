package org.tyrest.core.foundation.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: SpringContextHelper.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SpringContextHelper.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class SpringContextHelper implements ApplicationContextAware
{
	private static ApplicationContext context = null;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		context = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name)
	{
		return (T)context.getBean(name);
	}

	public static <T> T getBean(Class<T> beanClass){
		return context.getBean(beanClass);
	}
}

/*
 * $Log: av-env.bat,v $
 */