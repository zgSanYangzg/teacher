package org.tyrest.resource.systemctl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.systemctl.face.model.LocationInfoModel;
import org.tyrest.systemctl.face.service.LocationInfoService;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: LocationInfoResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationInfoResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/location")
@TyrestResource(module = "systemctl",value = "LocationInfoResourceV1", description = "位置信息管理")
public class LocationInfoResourceV1 extends BaseResources {

	@Autowired
	private LocationInfoService locationService;
	
	@TyrstOperation(name = "getProvince", ApiLevel = APILevel.ALL,  description = "获取所有省份列表")
	@RequestMapping(value = "/provinces", method = RequestMethod.GET)
	public ResponseModel<List<LocationInfoModel>> getProvince() throws Exception {
		return ResponseHelper.buildResponseModel(locationService.getProvinces());
	}

	@TyrstOperation(name = "getCitiesByProvince", ApiLevel = APILevel.ALL,  description = "根据省份获取城市列表")
	@RequestMapping(value = "/{provinceCode}/cities", method = RequestMethod.GET)
	public ResponseModel<List<LocationInfoModel>> getCitiesByProvince(
			@PathVariable String provinceCode) throws Exception {
		return ResponseHelper.buildResponseModel(locationService.getCitiesByProvince(provinceCode));
	}
	
	@TyrstOperation(name = "getRegionsByCity", ApiLevel = APILevel.ALL,  description = "根据市获取县/区列表")
	@RequestMapping(value = "/{cityCode}/regions", method = RequestMethod.GET)
	public ResponseModel<List<LocationInfoModel>> getRegionsByCity(
			@PathVariable String cityCode) throws Exception {
		return ResponseHelper.buildResponseModel(locationService.getRegionsByCity(cityCode));
	}

	@TyrstOperation(name = "getByCode", ApiLevel = APILevel.ALL,  description = "根据位置编码获取位置信息")
	@RequestMapping(value = "/{locationCode}", method = RequestMethod.GET)
	public ResponseModel<LocationInfoModel> getByCode(@PathVariable String locationCode)
			throws Exception {
		return ResponseHelper.buildResponseModel(locationService.getByCode(locationCode));
	}

	@TyrstOperation(name = "getByLocationType", ApiLevel = APILevel.ALL,  description = "根据地理位置类型查询")
	@RequestMapping(value = "/{LocationType}/list", method = RequestMethod.GET)
	public ResponseModel<List<Map<String,Object>>> getByLocationType(@PathVariable String LocationType)
			throws Exception {
		return ResponseHelper.buildResponseModel(locationService.getByLocationType(LocationType));
	}




}

/*
 * $Log: av-env.bat,v $
 */