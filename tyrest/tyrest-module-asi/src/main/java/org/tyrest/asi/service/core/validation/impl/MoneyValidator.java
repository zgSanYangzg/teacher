package org.tyrest.asi.service.core.validation.impl;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: MoneyValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: MoneyValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "MoneyValidator")
public class MoneyValidator implements Validator{

	@Override
	public boolean validate(String value) {
		return value.matches("\\d+\\.?[0-9]{2}");
	}

	public static void main(String[] args) {
		System.out.println(new MoneyValidator().validate("13.12"));
	}
}

/*
*$Log: av-env.bat,v $
*/