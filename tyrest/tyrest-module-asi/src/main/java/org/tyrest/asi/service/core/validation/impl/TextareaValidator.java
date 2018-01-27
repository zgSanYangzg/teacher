package org.tyrest.asi.service.core.validation.impl;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: TextareaValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TextareaValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "TextareaValidator")
public class TextareaValidator implements Validator{

	@Override
	public boolean validate(String value) {
		return value.length() <= 1000;
	}

}

/*
*$Log: av-env.bat,v $
*/