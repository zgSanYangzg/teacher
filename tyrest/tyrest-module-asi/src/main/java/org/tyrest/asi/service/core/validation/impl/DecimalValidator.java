package org.tyrest.asi.service.core.validation.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: DecimalValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DecimalValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "DecimalValidator")
public class DecimalValidator implements Validator{

	@Override
	public boolean validate(String value) {
		try {
			new BigDecimal(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new DecimalValidator().validate("11.2365489"));
	}
}

/*
*$Log: av-env.bat,v $
*/