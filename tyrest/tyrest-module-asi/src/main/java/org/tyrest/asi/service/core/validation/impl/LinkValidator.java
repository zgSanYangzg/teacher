package org.tyrest.asi.service.core.validation.impl;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LinkValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LinkValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "LinkValidator")
public class LinkValidator implements Validator{

	@Override
	public boolean validate(String value) {
		return value.matches("((http|https|ftp)://)?[a-z0-9A-Z]+(\\.[a-z0-9A-Z]+){1,3}(:[0-9]{2,5})?/?.*");
	}
	
	public static void main(String[] args) {
		System.out.println(new LinkValidator().validate("http://www.baidu.com.com/page?pefas=asds"));
		System.out.println(new LinkValidator().validate("www.baidu.com"));
		System.out.println(new LinkValidator().validate("http://192.168.2.219:8080/apis"));
	}
}

/*
*$Log: av-env.bat,v $
*/