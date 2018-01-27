package org.tyrest.notification.email;

import org.springframework.stereotype.Component;
import org.tyrest.notification.consumer.JMSMessageHandler;
import org.tyrest.notification.face.model.EmailPushModel;
import org.tyrest.notification.face.typedef.Notification;
import org.tyrest.opendata.emails.EmailSender;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: EmailPushMessageHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: EmailPushMessageHandler.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value="EmailPushHandler")
public class EmailPushMessageHandler extends JMSMessageHandler{
	
	@Override
	protected void handleMessage(Notification notificaiton) throws Exception {

		EmailSender emailSender = EmailSender.use("sendcloudEmail");
		EmailPushModel model = (EmailPushModel)notificaiton.body();
		emailSender.sendEmail(model.getFromName(), model.getFrom(),model.getTo(),model.getSubject(),model.getContent());
	}
}

/*
*$Log: av-env.bat,v $
*/