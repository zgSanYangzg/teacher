package org.tyrest.notification.face.typedef;

import java.io.Serializable;
import java.util.Date;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Notification.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Notification.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface Notification extends Serializable{
	
	Long id();
	
	void id(Long id);
	
	Date createTime();
	
	void createTime(Date createTime);
	
	Object body();
	
	boolean isTopic();
	
	boolean needPersist();
	
	int retryTimes();
}

/*
*$Log: av-env.bat,v $
*/