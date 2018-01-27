package org.tyrest.core.cache;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.transaction.NotSupportedException;

import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: LocalCache.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 本地缓存实现,根据需要实现响应的方法
 *  Notes:
 *  $Id: LocalCache.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component("localCache")
public class LocalCache implements Cache {
	
	private ConcurrentMap<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

	public void set(String key, Object v) {
		cacheMap.put(key, v);
	}

	public void expire(String key, Long expireDuration) throws Exception {
		throw new NotSupportedException("Method: [expire] not support in localCache.");

	}

	public void setWithExpire(String key, Object v, Long expireDuration) throws Exception {
		throw new NotSupportedException("Method: [setWithExpire] not support in localCache.");

	}

	@Override
	public boolean setNXWithExpire(String key, Object value, Long expireDuration) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <V> V get(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V getAndSet(String key, Object value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String key) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Long increment(String key, Long delta) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean zadd(String key, Object value, double score) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double zincrementScore(String key, Object value, double delta) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremove(String key, Object... values) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Set zget(String key, long start, long end, boolean desc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zcard(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremrangebyrank(String key, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rpush(String key, Object... values) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V lpop(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lsize(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V lindex(String key, Long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V lremove(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sadd(String key, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long scard(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V spop(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}

/*
 * $Log: av-env.bat,v $
 */