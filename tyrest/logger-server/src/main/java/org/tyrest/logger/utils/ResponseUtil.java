package org.tyrest.logger.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 
 * <pre>
 * 
 *  File: ResponseUtil.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  ResponseUtil.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日					magintursh				   Initial.
 *
 * </pre>
 */
public class ResponseUtil {
	
	public static void success(HttpServletResponse response,Object result) throws IOException{
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		JSONObject responseJson = new JSONObject();
		responseJson.put("status",200);
		responseJson.put("result",result);
		response.getWriter().write(responseJson.toString());
	}
}

/*
*$Log: av-env.bat,v $
*/