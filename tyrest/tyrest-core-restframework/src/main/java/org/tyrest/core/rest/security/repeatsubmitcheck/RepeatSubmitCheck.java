package org.tyrest.core.rest.security.repeatsubmitcheck;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: RepeatSubmitCheck.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:如果表单需要防止重复提交,加此注解
 *  TODO
 * 
 *  Notes:
 *  $Id: RepeatSubmitCheck.java  Tyrest\magintrursh $ 
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
public @interface RepeatSubmitCheck {
	
}

/*
*$Log: av-env.bat,v $
*/