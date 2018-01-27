package org.tyrest.core.foundation.exceptions;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: Exception.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Exception.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class DAOException extends Exception
{

	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 6307348989496885272L;

	public DAOException(String msg)
	{
		super(msg);
	}

	public DAOException(String msg, Throwable e)
	{
		super(msg, e);
	}

}

/*
 * $Log: av-env.bat,v $
 */