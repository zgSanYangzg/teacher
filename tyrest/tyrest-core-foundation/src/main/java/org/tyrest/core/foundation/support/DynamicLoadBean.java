package org.tyrest.core.foundation.support;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: DynamicLoadBean.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DynamicLoadBean.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class DynamicLoadBean implements ApplicationContextAware
{

	private ConfigurableApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext context) throws BeansException
	{
		this.applicationContext = (ConfigurableApplicationContext) context;
	}

	public ConfigurableApplicationContext getApplicationContext()
	{
		return this.applicationContext;
	}

	public void loadBean(String configLocationString)
	{
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
				(BeanDefinitionRegistry) getApplicationContext().getBeanFactory());
		beanDefinitionReader.setResourceLoader(getApplicationContext());
		beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(getApplicationContext()));
		try
		{
			String[] configLocations = new String[] {configLocationString};
			for (int i = 0; i < configLocations.length; i++)
				beanDefinitionReader.loadBeanDefinitions(getApplicationContext().getResources(configLocations[i]));
		}
		catch (BeansException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}