package org.tyrest.agency.dao;

import org.tyrest.agency.face.orm.dao.AgencyDAO;
import org.tyrest.agency.face.orm.entity.Agency;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AgencyDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgencyDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Repository(value="agencyDAO")
public class AgencyDAOImpl extends GenericDAOImpl<Agency> implements AgencyDAO
{

	@Override
	public void update(Agency agency) throws Exception {
		super.update(agency);
		Redis.removeSingle(this.getEntityClass(),agency.getAgencyCode());
	}

	@Override
	public Agency findByAgencyCode(String agencyCode) throws Exception {
		Agency agency = Redis.getSingle(this.getEntityClass(), agencyCode);
		if(ValidationUtil.isEmpty(agency)){
			StringBuilder sql = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE ");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("AGENCY_CODE",agencyCode);
			agency = this.findFirst(sql.toString(), params);
			if(!ValidationUtil.isEmpty(agency)){
				Redis.setSingle(agency, agency.getAgencyCode());
			}
		}
		return agency;
	}

	@Override
	public List<Agency> findAgencies(String agencyCode, String agencyName, String agencyAlias, String phone1,
			String adminId, String lockStatus) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE",agencyCode);
		}
		if(!ValidationUtil.isEmpty(phone1)){
			sql.append(" AND PHONE1 = :PHONE1 ");
			params.put("PHONE1",phone1);
		}
		if(!ValidationUtil.isEmpty(lockStatus)){
			sql.append(" AND LOCK_STATUS = :LOCK_STATUS ");
			params.put("LOCK_STATUS",lockStatus);
		}
		if(!ValidationUtil.isEmpty(agencyName)){
			sql.append(" AND AGENCY_NAME like '%"+ agencyName +"%' ");
		}
		if(!ValidationUtil.isEmpty(agencyAlias)){
			sql.append(" AND AGENCY_NAME like '%"+ agencyAlias +"%' ");
		}
		return this.find(sql.toString(), params, null, null);
	}

	@Override
	public List<Agency> findAgencyByPage(String agencyCode, String agencyName, String province, String city,
			String region, Page page, String orderBy, String order) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE",agencyCode);
		}
		if(!ValidationUtil.isEmpty(province)){
			sql.append(" AND PROVINCE = :PROVINCE ");
			params.put("PROVINCE",province);
		}
		if(!ValidationUtil.isEmpty(city)){
			sql.append(" AND CITY = :CITY ");
			params.put("CITY",city);
		}
		if(!ValidationUtil.isEmpty(region)){
			sql.append(" AND REGION = :REGION ");
			params.put("REGION",region);
		}
		if(!ValidationUtil.isEmpty(agencyName)){
			sql.append(" AND AGENCY_NAME like '%"+ agencyName +"%' ");
		}
		return this.paginate(sql.toString(), params, page, orderBy, order);
	}

	@Override
	public boolean isAgencyNameAvailable(String agencyName, Long id) throws Exception {
		StringBuilder sql = new StringBuilder(" AND AGENCY_NAME =  :agencyName");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("agencyName",agencyName);
		if(!ValidationUtil.isEmpty(id)){
			sql.append(" AND SEQUENCE_NBR != :SEQUENCE_NBR ");
			params.put("SEQUENCE_NBR",id);
		}
		return ValidationUtil.isEmpty(this.findFirst(sql.toString(), params));
	}

	@Override
	public boolean isAgencyCodeAvailable(String agencyCode, Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sql.append(" AND AGENCY_CODE = :AGENCY_CODE ");
		params.put("AGENCY_CODE",agencyCode);
		
		if(!ValidationUtil.isEmpty(id)){
			sql.append(" AND SEQUENCE_NBR != :SEQUENCE_NBR ");
			params.put("SEQUENCE_NBR",id);
		}
		return ValidationUtil.isEmpty(this.findFirst(sql.toString(), params));
	}

}
