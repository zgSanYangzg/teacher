package org.tyrest.core.rest.utils;

import org.springframework.http.HttpStatus;
import org.tyrest.core.foundation.context.RequestContext;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ResponseHelper.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ResponseHelper.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ResponseHelper {

	public static <T> ResponseModel<T> notFound(String message) {
		ResponseModel<T> response = new ResponseModel<T>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setCode(HttpStatus.NOT_FOUND.getReasonPhrase());
		response.setMessage(message);
		response.setTraceId(RequestContext.getTraceId());
		return response;
	}

	public static <T> ResponseModel<T> internal_server_error(String message) {
		ResponseModel<T> response = new ResponseModel<T>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		response.setMessage(message);
		response.setTraceId(RequestContext.getTraceId());
		return response;
	}

	/**
	 * Wrap response for validation failure.
	 * @param message the message
	 * @return the response model
	 */
	public static <T> ResponseModel<T> validationFailure(String message) {
		ResponseModel<T> response = new ResponseModel<T>();
		response.setStatus(HttpStatus.OK.value());
		response.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
		response.setMessage(message);
		response.setTraceId(RequestContext.getTraceId());
		return response;
	}

	/**
	 * 更新请求成功后的返回结果 TODO.
	 * @param result
	 * @return
	 */
	public static <T> ResponseModel<T> buildResponseModel(T result) {
		ResponseModel<T> response = new ResponseModel<T>();
		response.setStatus(HttpStatus.OK.value());
		response.setCode(HttpStatus.OK.getReasonPhrase());
		response.setMessage(HttpStatus.OK.getReasonPhrase());
		response.setResult(result);
		response.setTraceId(RequestContext.getTraceId());
		return response;
	}
}

/*
 * $Log: av-env.bat,v $
 */