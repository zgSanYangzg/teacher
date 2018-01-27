package org.tyrest.core.rest.security.repeatsubmitcheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tyrest.core.cache.Cache;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.rest.constants.RestConstants;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: FormSubmitInterceptor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:表单提交拦截器,用于防止重要数据的重复提交,在此校验表单token
 *  TODO
 * 
 *  Notes:
 *  $Id: FormSubmitInterceptor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class FormSubmitInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(FormSubmitInterceptor.class);
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		RepeatSubmitCheck repeatSubmitCheck = handlerMethod.getMethodAnnotation(RepeatSubmitCheck.class);
		
		if(ValidationUtil.isEmpty(repeatSubmitCheck)) return true;
		
		String submitToken = request.getHeader(CoreConstants.SUBMIT_TOKEN);
		if(ValidationUtil.isEmpty(submitToken)){
			throw new BadRequestException(RestConstants.MESSAGE_SUBMIT_TOKEN_REQUIRED);
		}
		
		final String submitTokenKey = RestConstants.CACHE_KEY_PREFIX_SUBMITTOKEN + Cache.VAR_SPLITOR + submitToken;
		Boolean hasRemoved = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] key = serializer.serialize(submitTokenKey);
				Long delNumber  = connection.del(key);
				return delNumber > 0 ? true : false;
			}
		}, true);
		
		if(!hasRemoved){
			logger.error("this form has been submitted already,please do not sbumit it again!");
			throw new BadRequestException(RestConstants.MESSAGE_REPEATE_SUBMIT);
		}
		return true;
	}
}

/*
*$Log: av-env.bat,v $
*/