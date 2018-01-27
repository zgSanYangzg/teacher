package org.tyrest.asi.face.service.extension;

import org.springframework.data.mongodb.core.query.Update;
import org.tyrest.core.foundation.support.SpringContextHelper;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ASIBizHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIBizHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ASIBizHandler {

	private static final String HANDLER_BEAN_SUFIX = "ASIHandler";

	private static ASIExtension getHandler(String entityType) throws Exception {
		return (ASIExtension) SpringContextHelper.getBean(entityType.concat(HANDLER_BEAN_SUFIX));
	}

	boolean updateBefore(String action, Update update, String entityType, String entityId) throws Exception {
		return getHandler(entityType).updateBefore(action, update, entityType, entityId);
	}

	boolean updateAfter(String action, Update update, String entityType, String entityId) throws Exception {
		return getHandler(entityType).updateAfter(action, update, entityType, entityId);
	}
}

/*
 * $Log: av-env.bat,v $
 */