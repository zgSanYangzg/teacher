package org.tyrest.core.rest.doc.swagger.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.tyrest.core.rest.containers.APILevel;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrstOperation.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrstOperation.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TyrstOperation {
	/**
	 * 
	 * 允许访问的次数，默认值MAX_VALUE
	 */
	int limit_count() default 100;

	/**
	 * 
	 * 时间段，单位为毫秒，默认值一分钟
	 */
	long limit_time() default 60000;
	/**
	 * 是否需要验证
	 * TODO.
	 *
	 * @return
	 */
	boolean needAuth() default true;

	/**
	 * 操作名称
	 */
	String name();

	/**
	 * API可见级别 PUBLIC,AGENCY,ALL,SUPERADMIN
	 */
	APILevel ApiLevel();

	/**
	 * 描述
	 */
	String description();
}

/*
*$Log: av-env.bat,v $
*/