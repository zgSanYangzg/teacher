package org.tyrest.asi.service.core.validation.impl;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: KeyvalueValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: KeyvalueValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "KeyvalueValidator")
public class KeyvalueValidator implements Validator{

	@Override
	public boolean validate(String value) {
		try {
			JSONObject.parse(value);
		} catch (JSONException e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(new KeyvalueValidator().validate("{\"name\":\"wuqiang\"}"));
	}

}

/*
*$Log: av-env.bat,v $
*/