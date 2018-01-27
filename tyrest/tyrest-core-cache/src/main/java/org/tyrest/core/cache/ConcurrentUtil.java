package org.tyrest.core.cache;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.tyrest.core.foundation.constants.CoreConstants;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ConcurrentUtil.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  用redis实现的全局锁,可实现并发控制
 * 
 *  Notes:
 *  $Id: ConcurrentUtil.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ConcurrentUtil
{
	public static String getUUID()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	/**
	 * TODO.以全局分布式锁的方式执行操作,用于控制规模较小的并发
	 * @param entityKey	全局锁的Key
	 * @param callee
	 * @return
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static <T> T runWithLock(String entityKey,Callable<T> callee) throws InterruptedException, Exception{
		lock(entityKey);
		T result = null;
		try {
			result = callee.call();
		} finally{
			unlock(entityKey);
		}
		return result;
	}
	/**
	 * TODO.获取全局锁
	 * @param entityKey	全局锁的Key
	 * @throws InterruptedException
	 * @throws Exception
	 */
	private static void lock(String entityKey) throws InterruptedException, Exception {
		while (true) {
			if (Redis.setNXWithExpire(entityKey, "monitor", CoreConstants.OPERATION_TOKEN_EXPIRE)) {
				return;
			} else {
				Thread.sleep(10);
			}
		}
	}
	/**
	 * TODO.释放全局锁
	 * @param entityKey	全局锁的Key
	 * @throws Exception
	 */
	private static void unlock(String entityKey) throws Exception{
		Redis.remove(entityKey);
	}
}
