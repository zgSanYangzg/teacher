package org.tyrest.notification.face.typedef;

import java.util.Date;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AbstractNotification.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AbstractNotification.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public abstract class AbstractNotification implements Notification{

	private static final long serialVersionUID = 1L;
	protected Long id;
	protected Date createTime;
	protected Object body;
	
	public AbstractNotification(Object body) {
		this.body = body;
	}

	@Override
	public Long id() {
		return this.id;
	}

	@Override
	public Date createTime() {
		return this.createTime;
	}
	
	@Override
	public Object body() {
		return this.body;
	}
	
	@Override
	public void id(Long id){
		this.id = id;
	}
	
	@Override
	public void createTime(Date createTime){
		this.createTime = createTime;
	}
}

/*
 * $Log: av-env.bat,v $
 */