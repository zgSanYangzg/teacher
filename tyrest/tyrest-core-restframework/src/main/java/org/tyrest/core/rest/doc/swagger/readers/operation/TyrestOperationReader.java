package org.tyrest.core.rest.doc.swagger.readers.operation;

import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.foundation.utils.Encrypt;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrestOperationReader.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrestOperationReader.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class TyrestOperationReader implements RequestMappingReader{

	@Override
	public void execute(RequestMappingContext context) {
		HandlerMethod handlerMethod = context.getHandlerMethod();
	    TyrstOperation tyrestOperation = (TyrstOperation) handlerMethod.getMethodAnnotation(TyrstOperation.class);
	    TyrestResource tyrstResource = handlerMethod.getBeanType().getAnnotation(TyrestResource.class);
	    
	    context.put("apiLevel", null != tyrestOperation && !ValidationUtil.isEmpty(tyrestOperation.ApiLevel())
	    		? tyrestOperation.ApiLevel().name() : "UNKNOWN");
	    
	    context.put("needAuth", String.valueOf(tyrestOperation.needAuth()));
	    
	    String fundId = "UNKNOWN";
	    if(!ValidationUtil.isEmpty(tyrstResource.module())
	    		&& !ValidationUtil.isEmpty(tyrstResource.value())
	    		&& !ValidationUtil.isEmpty(tyrestOperation.name())){
	    	fundId = Encrypt.IrreversibleMD5(
	    			tyrstResource.module() + tyrstResource.value() + tyrestOperation.name());
	    }
	    context.put("funId",fundId);
	}

}

/*
*$Log: av-env.bat,v $
*/