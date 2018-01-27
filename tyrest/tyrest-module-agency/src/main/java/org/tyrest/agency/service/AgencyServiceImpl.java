package org.tyrest.agency.service;

import org.tyrest.agency.face.orm.dao.AgencyDAO;
import org.tyrest.agency.face.constants.AgencyConstants;
import org.tyrest.agency.face.orm.entity.Agency;
import org.tyrest.agency.face.model.AgencyModel;
import org.tyrest.agency.face.service.AgencyService;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AgencyServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgencyServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月18日		framework		Initial.
 *
 * </pre>
 */
@Service(value = "agencyService")
public class AgencyServiceImpl extends BaseServiceImpl<AgencyModel, Agency>implements AgencyService {

	@Autowired
	private AgencyDAO agencyDAO;

	@Override
	public AgencyModel createAgency(AgencyModel agencyModel) throws Exception {
		Agency newAgency = this.prepareEntity(agencyModel);
		if (ValidationUtil.isEmpty(newAgency.getCoordinate())) {
			newAgency.setCoordinate(AgencyConstants.DEFAULT_AGENCY_COORDINATE);
		}
		agencyDAO.insert(newAgency);
		return Bean.toModel(newAgency, agencyModel);
	}

	@Override
	public AgencyModel updateAgency(AgencyModel agencyModel) throws Exception {
		Agency currentAgency = agencyDAO.findByAgencyCode(agencyModel.getAgencyCode());
		if(ValidationUtil.isEmpty(currentAgency)){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		Bean.copyExistPropertis(agencyModel, currentAgency);
		agencyDAO.update(currentAgency);
		return Bean.toModel(currentAgency, agencyModel);
	}

	@Override
	public AgencyModel updateLockStatus(String agencyCode) throws Exception {
		Agency currentAgency = agencyDAO.findByAgencyCode(agencyCode);
		if(ValidationUtil.isEmpty(currentAgency)){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		
		String lockStatus = CoreConstants.COMMON_Y.equals(currentAgency.getLockStatus()) ? CoreConstants.COMMON_N : CoreConstants.COMMON_Y;
		currentAgency.setRecDate(new Date());
		currentAgency.setLockStatus(lockStatus);
		if(CoreConstants.COMMON_Y.equals(lockStatus)){
			currentAgency.setLockDate(new Date());
			currentAgency.setLockUserId(RequestContext.getExeUserId());
		}
		agencyDAO.update(currentAgency);
		return Bean.toModel(currentAgency, new AgencyModel());
	}

	@Override
	public AgencyModel getAgencyByCode(String agencyCode) throws Exception {
		return this.setDetail(Bean.toModel(agencyDAO.findByAgencyCode(agencyCode), new AgencyModel()));
	}
	
	private AgencyModel setDetail(AgencyModel agencyModel) throws Exception{
		//设置商家详情
		return agencyModel;
	}

	@Override
	public Page getAgencyByPage(String agencyCode, String agencyName, String province, String city, String region,
			Page page, String orderBy, String order) throws Exception {
		List<AgencyModel> agencyModels = 
				Bean.toModels(agencyDAO.findAgencyByPage(agencyCode, agencyName, province, city, region, page, orderBy, order),AgencyModel.class);
		for(AgencyModel agencyModel : agencyModels){
			this.setDetail(agencyModel);
		}
		page.setList(agencyModels);
		return page;
	}

	@Override
	public boolean isAgencyNameAvailable(String agencyName, Long id) throws Exception {
		return agencyDAO.isAgencyNameAvailable(agencyName, id);
	}

	@Override
	public boolean isAgencyCodeAvailable(String agencyCode, Long id) throws Exception {
		return agencyDAO.isAgencyCodeAvailable(agencyCode, id);
	}

}
