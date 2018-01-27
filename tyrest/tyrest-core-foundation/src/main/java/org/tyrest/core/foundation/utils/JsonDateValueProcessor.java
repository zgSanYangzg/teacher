package org.tyrest.core.foundation.utils;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: JsonDateValueProcessor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: JsonDateValueProcessor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class JsonDateValueProcessor implements JsonValueProcessor
{
	private String dataFormat = "yyyy-MM-dd HH:mm:ss";
	
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2)
	{
		if (arg1 == null)
			return "";
		if (arg1 instanceof Date)
		{
			Date currentDate = (Date)arg1;
			return String.valueOf(currentDate.getTime());
		}
		return arg1.toString();
	}

	public Object processArrayValue(Object arg0, JsonConfig arg1)
	{
		return null;
	}

	public String getDataFormat()
	{
		return dataFormat;
	}

	public void setDataFormat(String dataFormat)
	{
		this.dataFormat = dataFormat;
	}
}

/*
*$Log: av-env.bat,v $
*/