package org.tyrest.core.rest.constants;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: RestConstants.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RestConstants.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class RestConstants {
	public static final String CACHE_KEY_PREFIX_SUBMITTOKEN = "SUBMITTOKEN";
	
	public static final String MESSAGE_OBJECT_NOTFOUND = "找不到资源";

	public static final String MESSAGE_SUBMIT_TOKEN_REQUIRED = "表单提交没有包含令牌信息,请获取令牌后重试.";

	public static final String MESSAGE_REPEATE_SUBMIT = "不能重复提交表单,请重新获取表单令牌后重试.";

	public static final String MESSAGE_RESOURCE_FOBBIDEN = "您没有该资源的访问权限";
}

/*
 * $Log: av-env.bat,v $
 */