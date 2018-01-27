package org.tyrest.core.logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: LogEntityEnum.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:日志实体枚举类型,枚举出需要记录日志的实体
 *  TODO
 * 
 *  Notes:
 *  $Id: LogEntityEnum.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public enum LogEntityEnum {
	DefaultModel,
	AgDepartmentModel,
	UserDetailModel,
	AgencyUserModel;
	
	public Map<String,String> getKeyLabel(){
		Map<String,String> result = new HashMap<String,String>();
		switch(this){
			case AgDepartmentModel:
				result.put("searchKey1","部门编码");
				break;
			case UserDetailModel:
			case AgencyUserModel:
				result.put("searchKey1","用户ID");
				result.put("searchKey2","用户姓名");
				break;
			case DefaultModel:
				result.put("searchKey1","用户ID");
				break;
			default:
				result.put("searchKey1","用户ID");
				break;
		}
		return result;
	}
	
	public Map<String,String> getKeyMapping(){
		Map<String,String> result = new HashMap<String,String>();
		switch(this){
			case AgDepartmentModel:
				result.put("searchKey1","departmentCode");
				break;
			case UserDetailModel:
			case AgencyUserModel:
				result.put("searchKey1","userId");
				result.put("searchKey2","userName");
				break;
			case DefaultModel:
				result.put("searchKey1","userId");
				break;
			default:
				result.put("searchKey1","userId");
				break;
		}
		return result;
	}
	
	public static LogEntityEnum getEntityEnum(String name){
		LogEntityEnum result = DefaultModel;
		for(LogEntityEnum logEntityEnum : LogEntityEnum.values()){
			if(logEntityEnum.name().equals(name)){
				result = logEntityEnum;
				break;
			}
		}
		return result;
	}
}

/*
*$Log: av-env.bat,v $
*/