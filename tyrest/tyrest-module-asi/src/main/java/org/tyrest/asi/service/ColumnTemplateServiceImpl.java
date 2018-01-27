package org.tyrest.asi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.asi.face.model.ColumnTemplateModel;
import org.tyrest.asi.face.orm.dao.ColumnTemplateDAO;
import org.tyrest.asi.face.orm.entity.ColumnTemplate;
import org.tyrest.asi.face.service.ColumnTemplateService;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ColumnTemplateServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ColumnTemplateServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="columnTemplateService")
public class ColumnTemplateServiceImpl extends BaseServiceImpl<ColumnTemplateModel, ColumnTemplate> implements ColumnTemplateService
{
	@Autowired
	private ColumnTemplateDAO columnTemplateDAO;

	@Override
	public ColumnTemplateModel createColumnTemplate(ColumnTemplateModel columnTemplateModel) throws Exception {
		if(ValidationUtil.isEmpty(columnTemplateModel)){
			throw new BusinessException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		ColumnTemplate newColumn = Bean.toPo(columnTemplateModel, new ColumnTemplate());
		newColumn.setRecDate(new Date());
		newColumn.setRecStatus(CoreConstants.COMMON_ACTIVE);
		newColumn.setRecUserId(RequestContext.getExeUserId());
		columnTemplateDAO.insertWithCache(newColumn);
		return Bean.toModel(newColumn, columnTemplateModel);
	}

	@Override
	public String deleteColumnTemplate(String agencyCode, String columnCode) throws Exception {
		columnTemplateDAO.deleteWithCache(agencyCode, columnCode);
		return null;
	}

	@Override
	public ColumnTemplateModel updateColumnTemplate(ColumnTemplateModel columnTemplateModel) throws Exception {
		ColumnTemplate currentColumn = columnTemplateDAO.getWithCache(columnTemplateModel.getAgencyCode(), columnTemplateModel.getColumnCode());
		if(ValidationUtil.isEmpty(currentColumn)){
			throw new BusinessException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		Bean.copyExistPropertis(columnTemplateModel, currentColumn);
		currentColumn.setRecDate(new Date());
		currentColumn.setRecUserId(RequestContext.getExeUserId());
		columnTemplateDAO.updateWithCache(currentColumn);
		return Bean.toModel(currentColumn, columnTemplateModel);
	}

	@Override
	public ColumnTemplateModel getColumnTemplate(String agencyCode, String columnCode) throws Exception {
		return Bean.toModel(columnTemplateDAO.getWithCache(agencyCode, columnCode), new ColumnTemplateModel());
	}

	@Override
	public Page getColumnTemplateByPage(String agencyCode, String columnCode, String columnName,
			Page page,String orderBy,String order) throws Exception {
		page.setList(Bean.toModels(columnTemplateDAO.findColumnTemplateByPage(agencyCode, columnCode,columnName,page,orderBy,order),ColumnTemplateModel.class));
		return page;
	}

	@Override
	public boolean isColumnCodeAvailable(String agencyCode, String columnCode) throws Exception {
		return ValidationUtil.isEmpty(columnTemplateDAO.getWithCache(agencyCode, columnCode));
	}

}
