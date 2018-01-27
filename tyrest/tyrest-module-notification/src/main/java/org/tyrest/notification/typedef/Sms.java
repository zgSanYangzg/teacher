package org.tyrest.notification.typedef;

import org.tyrest.notification.face.model.SmsSendModel;
import org.tyrest.notification.face.typedef.AbstractNotification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Sms.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Sms.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class Sms extends AbstractNotification{

	private static final long serialVersionUID = 1L;

	public Sms(SmsSendModel body) {
		super(body);
	}

	@Override
	public boolean isTopic() {
		return false;
	}

	@Override
	public boolean needPersist() {
		return true;
	}

	@Override
	public int retryTimes() {
		return 3;
	}

}

/*
*$Log: av-env.bat,v $
*/