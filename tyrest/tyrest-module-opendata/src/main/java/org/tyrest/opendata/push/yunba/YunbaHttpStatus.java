package org.tyrest.opendata.push.yunba;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: YunbaHttpStatus.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: YunbaHttpStatus.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum YunbaHttpStatus
{
	/**
	 * 发送成功
	 */
	SUCCESS(0,"success"),
	/**
	 * 参数错误
	 */
	INVALID_PARAMETERS(1,"invalid parameters"),
	/**
	 * 内部服务错误
	 */
	INTERNAL_SERVER_ERROR(2,"internal server error"),
	/**
	 * 没有应用
	 */
	NO_CLIENT_APP(3,"no client app"),
	/**
	 * 发布超时
	 */
	TIMEOUT(4,"timeout"),
	/**
	 * 没有发现alias
	 */
	ALIAS_NOT_FOUND(5,"alias not found");
	
	private int statusCode;
	
	private String statusMessage;
	
	private YunbaHttpStatus(int statusCode,String statusMessage){
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	public int getStatusCode(){
		return this.statusCode;
	}
	
	public String toString(){
		return this.statusMessage;
	}
	
	public static YunbaHttpStatus getStatusByCode(int code){
		YunbaHttpStatus result = null;
		switch(code){
			case 0 :
				result = SUCCESS;
				break;
			case 1 :
				result = INVALID_PARAMETERS;
				break;
			case 2 :
				result = INTERNAL_SERVER_ERROR;
				break;
			case 3 :
				result = NO_CLIENT_APP;
				break;
			case 4 :
				result = TIMEOUT;
				break;
			case 5 :
				result = ALIAS_NOT_FOUND;
				break;
		}
		return result;
	}
}

/*
*$Log: av-env.bat,v $
*/