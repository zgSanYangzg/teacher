package org.tyrest.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.notification.face.orm.dao.ArchiveMessageDAO;
import org.tyrest.notification.face.orm.entity.ArchiveMessage;
import org.tyrest.notification.face.service.ArchiveMessageService;
import org.tyrest.notification.face.typedef.Notification;

import com.alibaba.fastjson.JSONObject;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ArchiveMessageServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ArchiveMessageServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "archiveMessageService")
public class ArchiveMessageServiceImpl implements ArchiveMessageService {

	private static final String ARCHIVE_MESSAGE_FAILED = "FAILED";

	private static final String ARCHIVE_MESSAGE_SUCCESS = "SUCCESS";
	
	@Autowired
	private ArchiveMessageDAO archiveMessageDAO;
	
	@Override
	public void createArchiveMessage(Notification notification) throws Exception {
		ArchiveMessage archiveMessage = new ArchiveMessage();
		String jasonBody = JSONObject.toJSONString(notification.body());
		archiveMessage.setBody(jasonBody);
		archiveMessage.setCreateTime(notification.createTime());
		archiveMessage.setMessageType(notification.getClass().getSimpleName());
		archiveMessage.setSequenceNBR(notification.id());
		archiveMessageDAO.insert(archiveMessage);
	}

	@Override
	public void updateForFailed(Long messageId, String errorInfo) throws Exception {
		ArchiveMessage currentArchiveMessage = archiveMessageDAO.findById(messageId);
		if (!ValidationUtil.isEmpty(currentArchiveMessage)) {
			currentArchiveMessage.setStatus(ARCHIVE_MESSAGE_FAILED);
			currentArchiveMessage.setErrorInfo(errorInfo);
			archiveMessageDAO.update(currentArchiveMessage);
		}
	}

	@Override
	public boolean hasHandled(Long messageId) throws Exception {
		ArchiveMessage currentArchiveMessage = archiveMessageDAO.findById(messageId);
		if (ValidationUtil.isEmpty(currentArchiveMessage))
			return true;
		return !ValidationUtil.isEmpty(currentArchiveMessage.getStatus());
	}

	@Override
	public void updateForSuccess(Long messageId) throws Exception {
		ArchiveMessage currentArchiveMessage = archiveMessageDAO.findById(messageId);
		if (!ValidationUtil.isEmpty(currentArchiveMessage)) {
			currentArchiveMessage.setStatus(ARCHIVE_MESSAGE_SUCCESS);
			archiveMessageDAO.update(currentArchiveMessage);
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */