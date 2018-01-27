package org.tyrest.notification.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.notification.face.typedef.Notification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: JMSMessageHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: JMSMessageHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class JMSMessageHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(JMSMessageHandler.class);
	
	public static JMSMessageHandler getMessageHandler(String messageType){
		JMSMessageHandler messageHandler = null;
		try {
			messageHandler = (JMSMessageHandler) SpringContextHelper.getBean(messageType + "Handler");
		} catch (NoSuchBeanDefinitionException e) {
			messageHandler = (JMSMessageHandler) SpringContextHelper.getBean("defaultMessageHandler");
			logger.warn("not found MessageHandler for this messageType : {} the DefaultMessageHandler will be used!",messageType);
		}
		return messageHandler;
	}
	
	protected abstract void handleMessage(Notification notification) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/