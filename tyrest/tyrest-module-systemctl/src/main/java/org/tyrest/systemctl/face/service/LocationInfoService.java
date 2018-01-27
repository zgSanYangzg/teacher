package org.tyrest.systemctl.face.service;

import org.tyrest.core.mysql.BaseService;
import org.tyrest.systemctl.face.model.LocationInfoModel;
import org.tyrest.systemctl.face.orm.entity.LocationInfo;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LocationInfoService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationInfoService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月28日		yangbochao		Initial.
 *
 * </pre>
 */
public interface LocationInfoService extends BaseService<LocationInfoModel, LocationInfo> {

	/**
	 * TODO. 通过code获得具体的位置信息
	 * @param locationCode
	 * @return
	 */
	 LocationInfoModel getByCode(String locationCode) throws Exception;
	 /**
	  * TODO.获取所有的省
	  * @return
	  * @throws Exception
	  */
	 List<LocationInfoModel> getProvinces() throws Exception;
	 /**
	  * TODO.根据省获取所有的市
	  * @param provinceCode
	  * @return
	  * @throws Exception
	  */
	 List<LocationInfoModel> getCitiesByProvince(String provinceCode) throws Exception;
	 /**
	  * TODO.根据市获取所有的县/区
	  * @param cityCode
	  * @return
	  * @throws Exception
	  */
	 List<LocationInfoModel> getRegionsByCity(String cityCode) throws Exception;
	 /**
	  * TODO.获取省市区名称
	  * @param provinceCode
	  * @param cityCode
	  * @param regionCode
	  * @return
	  * @throws Exception
	  */
	 String getLocationSummary(String provinceCode, String cityCode, String regionCode) throws Exception;


	/**
	 * 按地区类型获取地区列表
	 * @param locationType
	 * @return
	 * @throws Exception
	 */
	List<Map<String,Object>>  getByLocationType(String locationType) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */