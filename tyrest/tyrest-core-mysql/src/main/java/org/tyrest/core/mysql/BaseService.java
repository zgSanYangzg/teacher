package org.tyrest.core.mysql;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: BaseService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BaseService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
public interface BaseService<M, P> {
	M create(M model) throws Exception;

	String delete(Long... id) throws Exception;

	M update(M model) throws Exception;

	M get(Long id) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */