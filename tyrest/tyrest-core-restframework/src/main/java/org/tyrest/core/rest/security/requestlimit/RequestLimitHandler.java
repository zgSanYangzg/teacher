package org.tyrest.core.rest.security.requestlimit;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: RequestLimitHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RequestLimitHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Aspect
@Order(value = 0)
@Component
public class RequestLimitHandler
{

	private static final Logger logger = LoggerFactory.getLogger(RequestLimitHandler.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(description)")
	public void requestLimit(final JoinPoint joinPoint,TyrstOperation description) throws RequestLimitException
	{
		
		try
		{
			String ip = RequestContext.getRequestIP();
			String methodSignature = joinPoint.toShortString();
			
			String key = "req_limit_".concat(methodSignature).concat(ip);

			long count = redisTemplate.opsForValue().increment(key, 1);
			if (count == 1)
			{
				redisTemplate.expire(key, description.limit_time(), TimeUnit.MILLISECONDS);
			}
			if (count > description.limit_count())
			{
				logger.info("用户IP[" + ip + "]访问地址[" + methodSignature + "]超过了限定的次数[" + description.limit_count() + "]");
				throw new RequestLimitException();
			}
		}
		catch (RequestLimitException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			logger.error("发生异常: ", e);
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */