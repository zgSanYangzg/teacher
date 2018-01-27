package org.tyrest.opendata.lbs;

import java.util.Map;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.utils.ValidationUtil;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: Lbs.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Lbs.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component
public class Lbs implements InitializingBean{
	
	
	@Value("${map.gaode.enable}")
	private String gaodeEnable;
	
	@Value("${map.gaode.key}")
	private String gaodeKey;
	
	public static String createLocation(String tableId, LocationType locType, String name, CoordinateType coordType,
			String locationValue, Map<String, String> extra, String sig) {
		return null;
	}

	public static void updateLocation(String tableId, LocationType locType, String id,String name, CoordinateType coordType,
			String locationValue, Map<String, String> extra, String sig) {
		
	}
	
	public static void deleteLocation(String tableId,String sig,String... ids){
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(!ValidationUtil.isEmpty(gaodeEnable) 
				&& CoreConstants.COMMON_TRUE.equals(gaodeEnable) 
				&& ValidationUtil.isEmpty(gaodeKey)){
			throw new BeanCreationException("高德云图API配置校验失败!");
		}else if(ValidationUtil.isEmpty(gaodeEnable) ){
			throw new BeanCreationException("高德云图API配置校验失败!");
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */