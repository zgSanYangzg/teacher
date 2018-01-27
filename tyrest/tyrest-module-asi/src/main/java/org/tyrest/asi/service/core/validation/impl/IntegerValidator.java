package org.tyrest.asi.service.core.validation.impl;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: IntegerValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: IntegerValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "IntegerValidator")
public class IntegerValidator implements Validator {

	@Override
	public boolean validate(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(new IntegerValidator().validate("123"));
	}
}

/*
 * $Log: av-env.bat,v $
 */