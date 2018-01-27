package org.tyrest.notification.typedef;

import org.tyrest.notification.face.model.MobilePushModel;
import org.tyrest.notification.face.typedef.AbstractNotification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: MobilePush.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: MobilePush.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class MobilePush extends AbstractNotification{

	private static final long serialVersionUID = 1L;

	public MobilePush(MobilePushModel body) {
		super(body);
	}

	@Override
	public boolean isTopic() {
		return false;
	}

	@Override
	public boolean needPersist() {
		return false;
	}

	@Override
	public int retryTimes() {
		return 3;
	}
}

/*
*$Log: av-env.bat,v $
*/