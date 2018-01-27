package org.tyrest.asi.service.core.validation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.tyrest.asi.service.core.validation.Validator;
import org.tyrest.core.foundation.utils.ValidationUtil;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: DatetimeValidator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DatetimeValidator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "DatetimeValidator")
public class DatetimeValidator implements Validator {

	private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
		private static final long serialVersionUID = 3695151962960376577L;
		{
			add(new SimpleDateFormat("yyyy-MM-dd"));
			add(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
			add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			add(new SimpleDateFormat("yyyy/MM/dd"));
			add(new SimpleDateFormat("yyyy/MM/dd HH:mm"));
			add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
		}
	};
	
	private static Date convertToDate(String input) {
		Date date = null;
		if(null == input) {
			return null;
		}
		for (SimpleDateFormat format : dateFormats) {
			try {
				format.setLenient(false);
				date = format.parse(input);
			} catch (ParseException e) {
				//Shhh.. try other formats
			}
			if (date != null) {
				break;
			}
		}
		return date;
	}

	@Override
	public boolean validate(String value) {
		if(!ValidationUtil.isEmpty(convertToDate(value))) return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new DatetimeValidator().validate("2016-08-19 14:56"));
	}
}

/*
 * $Log: av-env.bat,v $
 */