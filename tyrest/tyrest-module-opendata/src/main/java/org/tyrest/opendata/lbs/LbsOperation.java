package org.tyrest.opendata.lbs;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LbsOperation.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LbsOperation.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum LbsOperation {

	/**
	 * 创建单条数据
	 */
	GAODE_CREATE_POI("http://yuntuapi.amap.com/datamanage/data/create"),
	/**
	 * 批量创建数据
	 */
	GAODE_CREATE_POI_BATCH("http://yuntuapi.amap.com/datamanage/data/batchcreate"),
	/**
	 * 按条件检索数据（可遍历整表数据）
	 */
	GAODE_QUERY_POI_LIST("http://yuntuapi.amap.com/datamanage/data/list?"),
	/**
	 * 查询对应数据id的数据详情
	 */
	GAODE_QUERY_POI("http://yuntuapi.amap.com/datasearch/id?"),
	/**
	 * 更新数据（单条） 
	 */
	GAODE_UPDATE_POI("http://yuntuapi.amap.com/datamanage/data/update"),
	/**
	 * 删除数据（单条/批量） 
	 */
	GAODE_DELETE_POI("http://yuntuapi.amap.com/datamanage/data/delete"),
	/**
	 * 地理编码
	 */
	GAODE_QUERY_ADDR_BY_COORDINATE("http://restapi.amap.com/v3/geocode/geo?"),
	/**
	 * 逆地理编码
	 */
	GAODE_QUERY_ADDR_BY_ATHWART_COORDINATE("http://restapi.amap.com/v3/geocode/regeo?");
	
	
	private String requestUrl;//请求路径
	private LbsOperation(String requestUrl){
		this.requestUrl = requestUrl;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public static LbsOperation getInstance(String str)
	{
		LbsOperation result = null;
		for (LbsOperation lbsOperation : LbsOperation.values() )
		{
			if(lbsOperation.toString().equals(str))
			{
				result = lbsOperation;
			}
		}
		return result;
	}
}

/*
*$Log: av-env.bat,v $
*/