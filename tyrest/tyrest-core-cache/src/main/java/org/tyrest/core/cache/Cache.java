package org.tyrest.core.cache;

import java.util.Set;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: Cache.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Cache.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public interface Cache {
	
	public static final String VAR_SPLITOR = ":--:";
	
	/**
	 * TODO.添加缓存,并设置过期时间
	 * 
	 * @param key
	 * @param v
	 * @param expireDuration
	 * @return 如果key不存在,则set之后返回true,如果key存在,返回false
	 * @throws Exception
	 */
	public boolean setNXWithExpire(String key, Object value, Long expireDuration) throws Exception;

	/**
	 * TODO.添加缓存
	 * 
	 * @param key
	 * @param v
	 * @throws Exception
	 */
	public void set(String key, Object value) throws Exception;

	/**
	 * TODO.设置过期时间
	 * 
	 * @param key
	 * @param expireDuration 秒
	 * @throws Exception
	 */
	public void expire(String key, Long expireDuration) throws Exception;

	/**
	 * TODO.添加缓存并设置过期时间,如果key存在,则会用新值覆盖旧值
	 * 
	 * @param key
	 * @param v
	 * @param expireDuration
	 * @throws Exception
	 * @throws Exception
	 */
	public void setWithExpire(String key, Object value, Long expireDuration) throws Exception;

	/**
	 * TODO.根据key获取缓存
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <V> V get(String key) throws Exception;

	/**
	 * TODO.原子操作,设置新值,并返回旧值,如果旧值不存在,返回null
	 * 
	 * @param key
	 * @param v
	 * @return
	 * @throws Exception
	 */
	public <V> V getAndSet(String key, Object value) throws Exception;

	/**
	 * TODO.移除缓存
	 * 
	 * @param key
	 * @throws Exception
	 */
	public void remove(String key) throws Exception;

	/**
	 * TODO.缓存值自增
	 * 
	 * @param key
	 * @param delta
	 * @return
	 * @throws Exception
	 */
	Long increment(String key, Long delta) throws Exception;

	/**
	 * TODO.给有序集合添加一个元素
	 * 
	 * @param key
	 * @param value
	 * @param score
	 * @return
	 * @throws Exception
	 */
	Boolean zadd(String key, Object value, double score) throws Exception;

	/**
	 * TODO.改变有序集合中元素的分值
	 * 
	 * @param key
	 * @param value
	 * @param delta
	 * @return
	 * @throws Exception
	 */
	Double zincrementScore(String key, Object value, double delta) throws Exception;

	/**
	 * TODO.从有序集合中移除元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 * @throws Exception
	 */
	Long zremove(String key, Object... values) throws Exception;

	/**
	 * TODO.分页获取有序集合中的元素
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @param desc
	 *            升序还是降序
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Set zget(String key, long start, long end, boolean desc) throws Exception;

	/**
	 * TODO.根据key获取有序集的元素个数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long zcard(String key) throws Exception;

	/**
	 * TODO.根据下标删除有序集合元素
	 * 
	 * @param key
	 * @return
	 */
	Long zremrangebyrank(String key, long start, long end);

	/**
	 * TODO.向队列的末尾添加一个元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 * @throws Exception
	 */
	Long rpush(String key, Object... values) throws Exception;

	/**
	 * TODO.从队列的头部删除一个元素,并返回该元素
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	<V> V lpop(String key) throws Exception;

	/**
	 * TODO.获取指定队列的元素个数
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Long lsize(String key) throws Exception;

	/**
	 * TODO.获取队列中指定下标的元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	<V> V lindex(String key, Long index);

	/**
	 * TODO.移除列表中所有与指定值相同的元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	<V> V lremove(String key, Object value);

	/**
	 * TODO.给集合添加元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	Long sadd(String key, Object... values);

	/**
	 * TODO.获取集合的元素个数
	 * 
	 * @param key
	 * @return
	 */
	Long scard(String key);

	/**
	 * TODO.随机移除并返回集合中的一个元素
	 * 
	 * @param key
	 * @return
	 */
	<V> V spop(String key);

}

/*
 * $Log: av-env.bat,v $
 */