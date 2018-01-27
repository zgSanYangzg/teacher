package org.tyrest.asi.service.extension;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.tyrest.asi.face.service.extension.ASIExtension;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: DefaultASIHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DefaultASIHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "defaultASIHandler")
public class DefaultASIHandler implements ASIExtension{

	@Override
	public boolean updateBefore(String action, Update update, String entityType, String entityId) {
		return false;
	}

	@Override
	public boolean updateAfter(String action, Update update, String entityType, String entityId) {
		return false;
	}

}

/*
*$Log: av-env.bat,v $
*/