package org.tyrest.core.restevent;

import java.lang.annotation.*;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: freeapisEventTrigger.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:系统业务事件触发器注解类
 *  TODO 用于标示哪些方法是业务事件触发器
 * 
 *  Notes:
 *  $Id: freeapisEventTrigger.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月7日		wuqiang		Initial.
 *
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestEventTrigger
{
	/**
	 * 标示该触发器触发什么系统业务事件,一个事件对应一个事件处理器
	 */
	String[] value();
}

/*
*$Log: av-env.bat,v $
*/