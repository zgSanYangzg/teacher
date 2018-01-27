package org.tyrest.systemctl.face.constants;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.utils.PropertyUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 *  freeapis
 *  File: SystemConstants.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: SystemConstants.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年8月25日		wuqiang		Initial.
 * 
 * </pre>
 */
public class SystemConstants {
	
	public static final String MESSAGE_SMS_TEMPLATE_NOT_FOUND = "短信模板没有找到！";

	public static final String MESSAGE_TEMPLATE_NOT_FOUND = "未找到消息模板!";
	/**
	 * 七牛token的缓存key
	 */
	public static final String CACHE_KEY_PREFIX_QINIUTOKEN = "QINIUTOKEN";
	
	public static final String CACHE_KEY_PREFIX_PROVINCE = "province";

	public static final String CACHE_KEY_PREFIX_CITY = "city";

	public static final String CACHE_KEY_PREFIX_REGION = "region";
	
	public static final int QN_GET_FILES_MAX_LIMIT = 1000;
	
	public static Map<String, String> testAccounts = new HashMap<String, String>();

	public static String PRODUCTION_MODE = "N";

	public static String TEST_ACCOUNT_PREFIX = "FREEAPIS-TEST-";

	static {
		// 测试环境开关
		PRODUCTION_MODE = PropertyUtil.get("PRODUCTION_MODE");
		//如果是测试环境，加载测试账号
		if (CoreConstants.COMMON_N.equals(PRODUCTION_MODE)) {
			int i = 1;
			String key = null;
			String value = null;
			while (true) {
				key = TEST_ACCOUNT_PREFIX + i;
				value = PropertyUtil.get(key);
				if (!ValidationUtil.isEmpty(value)) {
					testAccounts.put(key, value);
					i++;
				} else {
					break;
				}
			}
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */