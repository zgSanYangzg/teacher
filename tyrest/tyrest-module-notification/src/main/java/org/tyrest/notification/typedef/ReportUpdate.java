package org.tyrest.notification.typedef;

import org.tyrest.notification.face.model.ReportTaskModel;
import org.tyrest.notification.face.typedef.AbstractNotification;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ReportUpdate.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ReportUpdate.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ReportUpdate extends AbstractNotification{

	public ReportUpdate(ReportTaskModel body) {
		super(body);
	}

	private static final long serialVersionUID = 1L;

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
		return 0;
	}

}

/*
*$Log: av-env.bat,v $
*/