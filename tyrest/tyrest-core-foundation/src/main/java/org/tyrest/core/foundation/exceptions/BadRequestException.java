package org.tyrest.core.foundation.exceptions;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: BadRequestException.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BadRequestException.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 2579648938096403373L;
	String code;

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(String msg, String code) {
		super(msg);
		this.code = code;

	}
}

/*
 * $Log: av-env.bat,v $
 */