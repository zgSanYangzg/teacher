package org.tyrest.core.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: CacheAdapter.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  缓存适配器,如果系统中缓存不能确定具体需要哪一种实现,则使用此类获取实现
 * 
 *  Notes:
 *  $Id: CacheAdapter.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component("cacheAdapter")
public class CacheAdapter {

	@Value("${cache.cacheName}")
	static String cacheName;

	static String DEFAULT_CACHE_NAME = "redisCache";

	public static Cache get() {
		if (ValidationUtil.isEmpty(cacheName)) {
			return (Cache) SpringContextHelper.getBean(DEFAULT_CACHE_NAME);
		} else {
			return (Cache) SpringContextHelper.getBean(cacheName);
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */