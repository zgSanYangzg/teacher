package org.tyrest.core.rest.utils;

import java.io.Serializable;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ResponseModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ResponseModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ResponseModel<T> implements Serializable
{
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = -1241360949457314497L;

	private int status;

	private T result;
	
	private String traceId;
	
	private String message;
	
	private String code;

	public String getMessage()
	{
		return message;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getTraceId()
	{
		return traceId;
	}

	public void setTraceId(String traceId)
	{
		this.traceId = traceId;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public T getResult()
	{
		return result;
	}

	public void setResult(T result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "ResponseModel [status=" + status + ", result=" + result + ", traceId=" + traceId + ", message="
				+ message + ", code=" + code + "]";
	}
}

/*
*$Log: av-env.bat,v $
*/