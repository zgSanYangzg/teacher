package org.tyrest.asi.face.service.extension;

import org.springframework.data.mongodb.core.query.Update;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ASIExtension.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIExtension.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ASIExtension {
	
	boolean updateBefore(String action,Update update,String entityType,String entityId);
	
	boolean updateAfter(String action,Update update,String entityType,String entityId);
	
}

/*
*$Log: av-env.bat,v $
*/