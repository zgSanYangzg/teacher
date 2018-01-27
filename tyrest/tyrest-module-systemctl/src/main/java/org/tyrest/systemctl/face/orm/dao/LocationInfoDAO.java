package org.tyrest.systemctl.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.LocationInfo;

import java.util.List;

/**
 * <pre>
 * 
 *  freeapis
 *  File: AgMemCardTypeDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: AgMemCardTypeDao.java 72642 2015年1月9日 freeapis\zhaoqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年1月9日		zhaoqiang		Initial.
 *  
 * </pre>
 */
public interface LocationInfoDAO extends GenericDAO<LocationInfo>
{
	/**
	 * TODO.根据父级位置编码获取子位置列表
	 * @param parentCode
	 * @return
	 * @throws Exception
	 */
	List<LocationInfo> findByParentCode(String parentCode,String locationType, String orderBy, String order) throws Exception;
	/**
	 * TODO.根据位置编码获取位置信息
	 * @param locationCode
	 * @return
	 * @throws Exception
	 */
	LocationInfo findByCode(String locationCode) throws Exception;
	/**
	 * TODO.查询位置的省市区信息
	 * @param provinceCode
	 * @param cityCode
	 * @param regionCode
	 * @return
	 * @throws Exception 
	 */
	String findLocationSummary(String provinceCode, String cityCode, String regionCode) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/