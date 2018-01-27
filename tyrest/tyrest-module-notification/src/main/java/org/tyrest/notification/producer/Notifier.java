package org.tyrest.notification.producer;

import java.io.Serializable;
import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.notification.face.service.ArchiveMessageService;
import org.tyrest.notification.face.typedef.Notification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Notifier.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Notifier.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component
public class Notifier {
	
	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Autowired
	private ArchiveMessageService archiveMessageservice;

	@Autowired
	private JmsTemplate jmsTemplate;
	
	/**
	 * TODO.给指定的队列发送JMS通知
	 * @param notification
	 * @param destinationName
	 * @throws Exception
	 */
	public void notify(Notification notification,String destinationName) throws Exception{
		if(ValidationUtil.isEmpty(notification) || ValidationUtil.isEmpty(destinationName)) return;
		notification.id(sequenceGenerator.getNextValue());
		notification.createTime(new Date());
		if(notification.needPersist()){
			archiveMessageservice.createArchiveMessage(notification);
		}
		Destination destination = notification.isTopic() 
				? new ActiveMQTopic(destinationName) : new ActiveMQQueue(destinationName);
		this.executeSend(notification, destination);
	}

	private void executeSend(final Serializable message, Destination destination) {
		this.jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(message);
				return objectMessage;
			}
		});
	}
}

/*
 * $Log: av-env.bat,v $
 */