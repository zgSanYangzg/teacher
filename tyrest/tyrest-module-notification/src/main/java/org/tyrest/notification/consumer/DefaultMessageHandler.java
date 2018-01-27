package org.tyrest.notification.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tyrest.notification.face.typedef.Notification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: DefaultMessageHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DefaultMessageHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value="defaultMessageHandler")
public class DefaultMessageHandler extends JMSMessageHandler{

	private static final Logger logger = LoggerFactory.getLogger(DefaultMessageHandler.class);
	
	@Override
	protected void handleMessage(Notification notification) throws Exception {
		logger.info("recieved notification [messageType : {},body : {}]",
				notification.getClass().getSimpleName(),notification.body().getClass().getSimpleName());
	}

}

/*
*$Log: av-env.bat,v $
*/