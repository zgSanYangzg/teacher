package org.tyrest.core.cache;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 *
 *  freeapis
 *  File: Redis.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2016
 *
 *  Description:
 *  	如果系统中的缓存只使用redis的实现,则可直接使用此类,
 *  	Redis类的所有方法均为static，调用很方便，
 *  	此类中的三个single标示的方法,可直接在dao层使用
 *  TODO
 *
 *  Notes:
 * 	$Id: Redis.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年7月15日		wuqiang		Initial.
 *
 * </pre>
 */
@Component(value = "redis")
public class Redis{

	public static RedisCache redisCache;

	public static String genKey(String...keyMembers){
		return StringUtils.join(keyMembers, Cache.VAR_SPLITOR).toUpperCase();
	}

	public static void setSingle(Object value,String... keyMembers) throws Exception{
		List<String> keyMemberList = new ArrayList<String>();
		keyMemberList.add(value.getClass().getName());
		keyMemberList.addAll(Arrays.asList(keyMembers));
		set(value,keyMemberList.toArray(new String[] {}));
	}

	public static <T,V> T getSingle(Class<V> clz,String... keyMembers) throws Exception{
		List<String> keyMemberList = new ArrayList<String>();
		keyMemberList.add(clz.getName());
		keyMemberList.addAll(Arrays.asList(keyMembers));
		return get(keyMemberList.toArray(new String[] {}));
	}

	public static <T> void removeSingle(Class<T> clz,String... keyMembers) throws Exception{
		List<String> keyMemberList = new ArrayList<String>();
		keyMemberList.add(clz.getName());
		keyMemberList.addAll(Arrays.asList(keyMembers));
		remove(keyMemberList.toArray(new String[] {}));
	}

	public static void removeByPrefix(String... keyMembers) throws Exception {
		redisCache.removeByPrefix(genKey(keyMembers));
	}

	public static boolean setNXWithExpire(String key, Object value, Long expireDuration) throws Exception {
		return redisCache.setNXWithExpire(key, value, expireDuration);
	}

	public static void set(Object value,String... keyMembers) throws Exception {
		redisCache.set(genKey(keyMembers), value);
	}

	public static void expire(Long expireDuration,String... keyMembers) throws Exception{
		redisCache.expire(genKey(keyMembers),expireDuration);
	}

	public static void setWithExpire(Object value, Long expireDuration,String... keyMembers) throws Exception {
		redisCache.setWithExpire(genKey(keyMembers), value, expireDuration);
	}

	public static <V> V get(String... keyMembers) throws Exception {
		return redisCache.get(genKey(keyMembers));
	}

	public static void remove(String... keyMembers) throws Exception {
		redisCache.remove(genKey(keyMembers));
	}

	public static <V> V getAndSet(Object value,String... keyMembers) throws Exception {
		return redisCache.getAndSet(genKey(keyMembers), value);
	}

	public static Long increment(Long delta,String... keyMembers) throws Exception {
		return redisCache.increment(genKey(keyMembers), delta);
	}

	public static Boolean zadd(Object value, double score,String... keyMembers) throws Exception {
		return redisCache.zadd(genKey(keyMembers), value, score);
	}

	public static Double zincrementScore(Object value, double delta,String... keyMembers) throws Exception {
		return redisCache.zincrementScore(genKey(keyMembers), value, delta);
	}

	public static Long zremove(Object[] values,String... keyMembers) throws Exception {
		return redisCache.zremove(genKey(keyMembers), values);
	}

	@SuppressWarnings("rawtypes")
	public static Set zget(long start, long end, boolean desc,String... keyMembers) throws Exception {
		return redisCache.zget(genKey(keyMembers), start, end, desc);
	}

	public static Long zcard(String... keyMembers) throws Exception {
		return redisCache.zcard(genKey(keyMembers));
	}

	public static Long zremrangebyrank(long start, long end,String... keyMembers) {
		return redisCache.zremrangebyrank(genKey(keyMembers), start, end);
	}

	public static Long rpush(Object[] values,String... keyMembers) throws Exception {
		return redisCache.rpush(genKey(keyMembers), values);
	}

	public static <V> V lpop(String... keyMembers) throws Exception {
		return redisCache.lpop(genKey(keyMembers));
	}

	public static Long lsize(String... keyMembers) throws Exception {
		return redisCache.lsize(genKey(keyMembers));
	}

	public static <V> V lindex(Long index,String... keyMembers) {
		return redisCache.lindex(genKey(keyMembers), index);
	}

	public static <V> V lremove(Object value,String... keyMembers) {
		return redisCache.lremove(genKey(keyMembers), value);
	}

	public static Long sadd(Object[] values,String... keyMembers) {
		return redisCache.sadd(genKey(keyMembers), values);
	}

	public static Long scard(String... keyMembers) {
		return redisCache.scard(genKey(keyMembers));
	}

	public static <V> V spop(String... keyMembers) {
		return redisCache.spop(genKey(keyMembers));
	}
}

/*
 * $Log: av-env.bat,v $
 */