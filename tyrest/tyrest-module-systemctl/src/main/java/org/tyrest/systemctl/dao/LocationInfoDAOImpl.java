package org.tyrest.systemctl.dao;

import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.orm.dao.LocationInfoDAO;
import org.tyrest.systemctl.face.orm.entity.LocationInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LocationInfoDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationInfoDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Repository(value="locationInfoDAO")
public class LocationInfoDAOImpl extends GenericDAOImpl<LocationInfo> implements LocationInfoDAO
{



	@Override
	public List<LocationInfo> findByParentCode(String parentCode,String locationType,String orderBy,String order) throws Exception {
		StringBuilder sqlSufix = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (!ValidationUtil.isEmpty(parentCode)) {
			sqlSufix.append(" AND PARENT_CODE = :PARENT_CODE ");
			params.put("PARENT_CODE", parentCode);
		}

		if (!ValidationUtil.isEmpty(locationType)) {
			if("city".equals(locationType))
			{
				sqlSufix.append(" AND LOCATION_TYPE in('city','municipality')");
			}else{
				sqlSufix.append(" AND LOCATION_TYPE  = :locationType");
				params.put("locationType", locationType);
			}
		}
		return this.find(sqlSufix.toString(), params, orderBy, order);
	}

	@Override
	public LocationInfo findByCode(String locationCode) throws Exception {
		StringBuilder sqlSufix = new StringBuilder(" AND LOCATION_CODE = :LOCATION_CODE ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("LOCATION_CODE", locationCode);
		return this.findFirst(sqlSufix.toString(), params);
	}

	@Override
	public String findLocationSummary(String provinceCode, String cityCode, String regionCode) throws Exception {
		StringBuilder sql = new StringBuilder(" SELECT LOCATION_NAME FROM SYSTEMCTL_LOCATION_INFO WHERE LOCATION_CODE = :PROVINCE_CODE ")
											.append(" UNION ")
											.append(" SELECT LOCATION_NAME FROM SYSTEMCTL_LOCATION_INFO WHERE LOCATION_CODE = :CITY_CODE ")
											.append(" UNION ")
											.append(" SELECT LOCATION_NAME FROM SYSTEMCTL_LOCATION_INFO WHERE LOCATION_CODE = :REGION_CODE ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PROVINCE_CODE", provinceCode);
		params.put("CITY_CODE", cityCode);
		params.put("REGION_CODE", regionCode);
		List<String> locationNames = this.findObjects(sql.toString(), params);
		return StringUtil.join(locationNames, "");
	}
}
