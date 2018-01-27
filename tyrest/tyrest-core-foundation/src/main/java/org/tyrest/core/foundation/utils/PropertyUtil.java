package org.tyrest.core.foundation.utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: PropertyUtil.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PropertyUtil.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer {

	public static Map<String, String> ctxPropertiesMap = new HashMap<String, String>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		for (Object ko : props.keySet()) {
			String key = ko.toString();
			ctxPropertiesMap.put(key, props.getProperty(key));
		}
	}

	public static String get(String key) {
		return ctxPropertiesMap.get(key);
	}

}

/*
 * $Log: av-env.bat,v $
 */