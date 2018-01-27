package org.tyrest.agency.face.orm.dao;

import org.tyrest.agency.face.orm.entity.Agency;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AgAgencyDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * Description:机构的数据访问接口
 *  Notes:
 *  $Id: AgAgencyDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月20日		yangbochao		Initial.
 *
 * </pre>
 */
public interface AgencyDAO extends GenericDAO<Agency> {

	public Agency findByAgencyCode(String agencyCode) throws Exception;

	public List<Agency> findAgencies(String agencyCode, String agencyName, String agencyAlias, String phone1,
                                     String adminId, String lockStatus) throws Exception;

	public List<Agency> findAgencyByPage(String agencyCode, String agencyName, String province, String city,
                                         String region, Page page, String orderBy, String order) throws Exception;

	boolean isAgencyNameAvailable(String agencyName, Long id) throws Exception;

	boolean isAgencyCodeAvailable(String agencyCode, Long id) throws Exception;
}
/*
 * $Log: av-env.bat,v $
 */