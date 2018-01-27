package org.tyrest.core.foundation.exceptions;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: SequenceGenerationException.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SequenceGenerationException.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class SequenceGenerationException extends Exception
{ 
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 6608170579046513651L;

	public SequenceGenerationException(String msg,Throwable e)
	{
		super(msg, e);
	}
	
	public SequenceGenerationException(String msg)
	{
		super(msg);
	}
}

/*
*$Log: av-env.bat,v $
*/