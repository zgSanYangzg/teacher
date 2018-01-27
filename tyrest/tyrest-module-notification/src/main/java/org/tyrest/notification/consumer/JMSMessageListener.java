package org.tyrest.notification.consumer;

import java.util.concurrent.Callable;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tyrest.core.cache.ConcurrentUtil;
import org.tyrest.core.foundation.utils.RetryUtil;
import org.tyrest.core.foundation.utils.RetryUtil.Result;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.notification.face.service.ArchiveMessageService;
import org.tyrest.notification.face.typedef.Notification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: JMSMessageListener.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: JMSMessageListener.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class JMSMessageListener implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(JMSMessageListener.class);

	private static String MESSAGE_HANDLING_LOCK_KEY = "MESSAGE_HANDLING_LOCK_KEY";

	@Autowired
	private ArchiveMessageService archiveMessageService;

	@Override
	public void onMessage(Message message) {
		try {
			if (!(message instanceof ObjectMessage)) {
				logger.error("the message must be an ObjectMessage!");
				throw new Exception("the message must be an ObjectMessage!");
			} 
			Notification notification = (Notification) ((ObjectMessage) message).getObject();
			if (!ValidationUtil.isEmpty(notification)) {
				JMSMessageHandler messageHandler = JMSMessageHandler.getMessageHandler(notification.getClass().getSimpleName());
				if (!ValidationUtil.isEmpty(messageHandler)) {
					this.doProcessMessage(messageHandler, notification);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * TODO.处理消息,先校验消息->消息去重->处理消息->消息归档
	 * 
	 * @param originalMessage
	 * @param messageHandler
	 * @param message
	 * @throws Exception
	 */
	private void doProcessMessage(final JMSMessageHandler messageHandler, final Notification notification) throws Exception {
		// 如果消息不需要持久化，比如一些无关紧要的非业务数据，直接处理
		if (!notification.needPersist()) {
			RetryUtil.retryOnException(new Callable<Result>() {
				public Result call() throws Exception {
					messageHandler.handleMessage(notification);
					return new Result() {
						public boolean isSuccess() {
							return true;
						}

						public String result() {
							return null;
						}
					};
				}
			}, notification.retryTimes());
		} else {
			// 如果是需要持久化的消息，说明这些消息是业务相关的重要数据，需要经过去重，校验等处理
			ConcurrentUtil.runWithLock(MESSAGE_HANDLING_LOCK_KEY + notification.id(), new Callable<String>() {
				@Override
				public String call() throws Exception {
					//#1.校验消息(根据状态判断该消息是否已经被处理,已处理则直接返回)
					if(archiveMessageService.hasHandled(notification.id())) return null;
					//#2.处理消息,redis全局锁实现消息去重机制,这样保证每个消息只被消费一次。
					Result handleResult = RetryUtil.retryOnException(new Callable<Result>(){
						public Result call() throws Exception {
							messageHandler.handleMessage(notification);
							return new Result(){
								public boolean isSuccess() {
									return true;
								}
								public String result() {
									return null;
								}
							};
						}
					}, notification.retryTimes());
					//#3.如果处理失败则改变status状态为failed
					if(!handleResult.isSuccess()){
						archiveMessageService.updateForFailed(notification.id(),handleResult.result());
					}else{
						archiveMessageService.updateForSuccess(notification.id());
					}
					return null;
				}
			});
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */