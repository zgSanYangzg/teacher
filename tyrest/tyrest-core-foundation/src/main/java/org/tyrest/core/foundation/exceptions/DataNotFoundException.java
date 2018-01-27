package org.tyrest.core.foundation.exceptions;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: DataNotFoundException.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DataNotFoundException.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7921892239930203176L;

	public DataNotFoundException(String msg) {
        super(msg);
    }
}

/*
*$Log: av-env.bat,v $
*/