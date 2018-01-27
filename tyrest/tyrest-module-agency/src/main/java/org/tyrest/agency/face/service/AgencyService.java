package org.tyrest.agency.face.service;

import org.tyrest.agency.face.model.AgencyModel;
import org.tyrest.agency.face.orm.entity.Agency;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;

/**
 * <pre>
 * 
 *  freeapis
 *  File: AgencyService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgencyService.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年7月18日		wuqiang		Initial.
 *
 * </pre>
 */
public interface AgencyService extends BaseService<AgencyModel, Agency> {

	AgencyModel createAgency(AgencyModel agencyModel) throws Exception;

	AgencyModel updateAgency(AgencyModel agencyModel) throws Exception;

	AgencyModel updateLockStatus(String agencyCode) throws Exception;

	AgencyModel getAgencyByCode(String agencyCode) throws Exception;

	Page getAgencyByPage(String agencyCode, String agencyName, String province, String city, String region,
                         Page page, String orderBy, String order) throws Exception;

	boolean isAgencyNameAvailable(String agencyName, Long id) throws Exception;

	boolean isAgencyCodeAvailable(String agencyCode, Long id) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */