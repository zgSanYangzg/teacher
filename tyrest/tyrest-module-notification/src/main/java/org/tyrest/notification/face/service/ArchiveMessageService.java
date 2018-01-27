package org.tyrest.notification.face.service;

import org.tyrest.notification.face.typedef.Notification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ArchiveMessageService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ArchiveMessageService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ArchiveMessageService {
	/**
	 * TODO.发往消息队列之前先记录该消息
	 * @param jmsMessage
	 * @throws Exception
	 */
	void createArchiveMessage(Notification notification) throws Exception;
	/**
	 * TODO.如果任务失败,则更新消息状态
	 * 
	 * @param message
	 * @throws Exception
	 */
	void updateForFailed(Long messageId, String errorInfo) throws Exception;
	
	/**
	 * TODO.如果任务成功，则将消息标记为成功
	 * 
	 * @param message
	 * @throws Exception
	 */
	void updateForSuccess(Long messageId) throws Exception;
	/**
	 * TODO.查询消息是否已经被处理，用于消息去重
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	boolean hasHandled(Long messageId) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/