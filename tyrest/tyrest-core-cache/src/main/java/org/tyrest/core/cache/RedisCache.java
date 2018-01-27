package org.tyrest.core.cache;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: RedisCache.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  redis缓存的实现,现在一般项目直接使用redis缓存就行,如需切换缓存,则使用CacheAdapter
 *  TODO
 * 
 *  Notes:
 *  $Id: RedisCache.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "redisCache")
public class RedisCache implements Cache, InitializingBean {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final TimeUnit timeUnit = TimeUnit.SECONDS;

	public static final String ASTERISK = "*";

	@Override
	public void afterPropertiesSet() throws Exception {
		Redis.redisCache = this;
	}

	public void set(String key, Object v) {
		redisTemplate.opsForValue().set(key, v);
	}

	public void expire(String key, Long expireDuration) {
		redisTemplate.expire(key, expireDuration, timeUnit);
	}

	public void setWithExpire(String key, Object v, Long expireDuration) {
		redisTemplate.opsForValue().set(key, v);
		redisTemplate.expire(key, expireDuration, timeUnit);
	}

	@SuppressWarnings("unchecked")
	public <V> V get(String key) {
		return (V) redisTemplate.opsForValue().get(key);
	}

	public void remove(String key) {
		redisTemplate.delete(key);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeByPrefix(String pkey) {
		Set matchKeys = redisTemplate.keys(pkey + ASTERISK);
		redisTemplate.delete(matchKeys);
	}

	@Override
	public Long increment(String key, Long delta) throws Exception {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Boolean zadd(String key, Object value, double score) throws Exception {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	@Override
	public Long zremove(String key, Object... values) throws Exception {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Set zget(String key, long start, long end, boolean desc) throws Exception {
		ZSetOperations<String, Object> zsetOptions = redisTemplate.opsForZSet();
		if (desc) {
			return zsetOptions.reverseRange(key, start, end);
		} else {
			return zsetOptions.range(key, start, end);
		}
	}

	@Override
	public Double zincrementScore(String key, Object value, double delta) throws Exception {
		return redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	@Override
	public Long zcard(String key) throws Exception {
		return redisTemplate.opsForZSet().zCard(key);
	}

	@Override
	public Long zremrangebyrank(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	public Long rpush(String key, Object... values) throws Exception {
		return redisTemplate.opsForList().rightPushAll(key, values);
	}

	@SuppressWarnings("unchecked")
	public <V> V lpop(String key) throws Exception {
		return (V) redisTemplate.opsForList().leftPop(key);
	}

	public Long lsize(String key) throws Exception {
		return redisTemplate.opsForList().size(key);
	}

	@SuppressWarnings("unchecked")
	public <V> V lindex(String key, Long index) {
		return (V) redisTemplate.opsForList().index(key, index);
	}

	@SuppressWarnings("unchecked")
	public <V> V lremove(String key, Object value) {
		return (V) redisTemplate.opsForList().remove(key, 0, value);
	}

	public Long sadd(String key, Object... values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	public Long scard(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	@SuppressWarnings("unchecked")
	public <V> V spop(String key) {
		return (V) redisTemplate.opsForSet().pop(key);
	}

	@Override
	public boolean setNXWithExpire(String key, Object v, Long expireDuration) throws Exception {
		boolean flag = false;
		if (redisTemplate.opsForValue().setIfAbsent(key, v)) {
			flag = redisTemplate.expire(key, expireDuration, timeUnit);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V getAndSet(String key, Object v) throws Exception {
		return (V) redisTemplate.opsForValue().getAndSet(key, v);
	}
}

/*
 * $Log: av-env.bat,v $
 */