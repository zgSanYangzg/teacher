package org.tyrest.systemctl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.cache.Cache;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.systemctl.face.constants.SystemConstants;
import org.tyrest.systemctl.face.model.LocationInfoModel;
import org.tyrest.systemctl.face.orm.dao.LocationInfoDAO;
import org.tyrest.systemctl.face.orm.entity.LocationInfo;
import org.tyrest.systemctl.face.service.LocationInfoService;

import java.util.*;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LocationInfoServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationInfoServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Service(value = "locationInfoService")
public class LocationInfoServiceImpl extends BaseServiceImpl<LocationInfoModel, LocationInfo>
		implements LocationInfoService {
	@Autowired
	private LocationInfoDAO locationInfoDAO;

	@Override
	public LocationInfoModel getByCode(String locationCode) throws Exception {
		return Bean.toModel(locationInfoDAO.findByCode(locationCode), new LocationInfoModel());
	}


	public List<Map<String,Object>>  getByLocationType(String locationType) throws Exception {

		String cacheKey = "LOCATION_TYPE"+Cache.VAR_SPLITOR  + locationType;
		Map<String,List<LocationInfoModel>> result = new TreeMap<>(new MapKeyComparator());
		List<Map<String,Object>> resultList = Redis.get(cacheKey);
		if(ValidationUtil.isEmpty(resultList ))
		{
			resultList = new ArrayList<>();
			List<LocationInfoModel> models = Bean.toModels(locationInfoDAO.findByParentCode(null,locationType,
					ParamConstants.LOCATION_INFO_FILED_PINYIN_NAME, null), LocationInfoModel.class);


			for(LocationInfoModel model : models)
			{
				List<LocationInfoModel> list = result.get(model.getPinyinName().substring(0,1));
				if(ValidationUtil.isEmpty(list))
				{
					list = new ArrayList<>();
				}
				list.add(model);
				result.put(model.getPinyinName().substring(0,1),list);
			}
			for(String key : result.keySet())
			{
				Map<String,Object> newMap = new HashMap();
				newMap.put("letter",key);
				newMap.put("locations",result.get(key));
				resultList.add(newMap);
			}
			Redis.set(resultList,cacheKey);
		}
		return resultList;
	}

	@Override
	public List<LocationInfoModel> getProvinces() throws Exception {
		List<LocationInfoModel> result = Redis.get(SystemConstants.CACHE_KEY_PREFIX_PROVINCE);
		if (ValidationUtil.isEmpty(result)) {
			result = Bean.toModels(locationInfoDAO.findByParentCode(ParamConstants.ROOT_LOCATION_CODE,null,
					ParamConstants.LOCATION_INFO_FILED_PINYIN_NAME, null),LocationInfoModel.class);
			if (!ValidationUtil.isEmpty(result)) {
				Redis.set(result, SystemConstants.CACHE_KEY_PREFIX_PROVINCE);
			}
		}
		return result;
	}

	@Override
	public List<LocationInfoModel> getCitiesByProvince(String provinceCode) throws Exception {
		List<LocationInfoModel> result = Redis.get(SystemConstants.CACHE_KEY_PREFIX_PROVINCE, provinceCode,
				SystemConstants.CACHE_KEY_PREFIX_CITY);
		if (ValidationUtil.isEmpty(result)) {
			result = Bean.toModels(locationInfoDAO.findByParentCode(provinceCode,null,
					ParamConstants.LOCATION_INFO_FILED_PINYIN_NAME, null),LocationInfoModel.class);
			if (!ValidationUtil.isEmpty(result)) {
				Redis.set(result, SystemConstants.CACHE_KEY_PREFIX_PROVINCE, provinceCode,
						SystemConstants.CACHE_KEY_PREFIX_CITY);
			}
		}
		return result;
	}

	@Override
	public List<LocationInfoModel> getRegionsByCity(String cityCode) throws Exception {
		List<LocationInfoModel> result = Redis.get(SystemConstants.CACHE_KEY_PREFIX_CITY, cityCode,
				SystemConstants.CACHE_KEY_PREFIX_REGION);
		if (ValidationUtil.isEmpty(result)) {
			result = Bean.toModels(locationInfoDAO.findByParentCode(cityCode,null,
					ParamConstants.LOCATION_INFO_FILED_PINYIN_NAME, null),LocationInfoModel.class);
			if (!ValidationUtil.isEmpty(result)) {
				Redis.set(result, SystemConstants.CACHE_KEY_PREFIX_CITY, cityCode,
						SystemConstants.CACHE_KEY_PREFIX_REGION);
			}
		}
		return result;
	}

	@Override
	public String getLocationSummary(String provinceCode, String cityCode, String regionCode) throws Exception {
		return locationInfoDAO.findLocationSummary(provinceCode,cityCode,regionCode);
	}


	class MapKeyComparator implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {

			return str1.compareTo(str2);
		}
	}
}
