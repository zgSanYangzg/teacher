package org.tyrest.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.cms.face.constants.CmsConstants;
import org.tyrest.cms.face.model.AdvertisementModel;
import org.tyrest.cms.face.orm.dao.AdvertisementDAO;
import org.tyrest.cms.face.orm.dao.ContentDAO;
import org.tyrest.cms.face.orm.entity.Advertisement;
import org.tyrest.cms.face.orm.entity.Content;
import org.tyrest.cms.face.service.AdvertisementService;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.opendata.storage.Storage;

import java.util.Date;
import java.util.List;

/**
 * 
 * <pre>
 * 
 *  Lexing
 *  File: AdvertisementServiceImpl.java
 * 
 *  Lexing, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AdvertisementServiceImpl.java 31101200-9 2014-10-14 16:43:51Z Lexing\leishuaifeng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月27日		leishuaifeng		Initial.
 *
 * </pre>
 */
@Service(value="advertisementService")
public class AdvertisementServiceImpl extends BaseServiceImpl<AdvertisementModel, Advertisement> implements AdvertisementService
{
	@Autowired
	private AdvertisementDAO advertisementDAO;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@Autowired
	private ContentDAO contenDAO;
	
	@Autowired
	private Storage storage;
	

	@Override
	public AdvertisementModel createAdvertisement(AdvertisementModel advertisementModel) throws Exception {
		Advertisement newAd = Bean.toPo(advertisementModel, this.getPoClass().newInstance());
		newAd.setRecDate(new Date());
		newAd.setRecStatus(CoreConstants.COMMON_ACTIVE);
		newAd.setRecUserId(RequestContext.getExeUserId());
		newAd.setSequenceNBR(sequenceGenerator.getNextValue());
		advertisementDAO.insert(newAd);
		return Bean.toModel(newAd,advertisementModel);
	}

	@Override
	public void deleteAdvertisement(Long id,String fileName) throws Exception {
		if(!ValidationUtil.isEmpty(id)){
			Advertisement currentAd = advertisementDAO.findById(id);
			advertisementDAO.delete(id);
		}else if(!ValidationUtil.isEmpty(fileName)){
			storage.deleteFile("common",fileName);
		}
	}

	@Override
	public AdvertisementModel updateAdvertisement(AdvertisementModel advertisementModel) throws Exception {
		Advertisement currentAd = advertisementDAO.findById(advertisementModel.getSequenceNBR());
		Bean.copyExistPropertis(advertisementModel, currentAd);
		advertisementDAO.update(currentAd);
		return Bean.toModel(currentAd,advertisementModel);
	}

	@Override
	public AdvertisementModel getById(Long id) throws Exception {
		return Bean.toModel(advertisementDAO.findById(id),new AdvertisementModel());
	}

	@Override
	public List<AdvertisementModel> getAdList(Integer adType) throws Exception {
		List<AdvertisementModel> result = Bean.toModels(advertisementDAO.findAdvertisementList(adType),this.getModelClass());
		for(AdvertisementModel e : result){
			if(!ValidationUtil.isEmpty(e.getTargetSequenceNbr())){
				Content relatedContent = contenDAO.findById(e.getTargetSequenceNbr());
				if(!ValidationUtil.isEmpty(relatedContent)){
					e.setContentTitle(relatedContent.getContentTitle());
				}
			}
		}
		return result;
	}

	@Override
	public Boolean isTitleAvailable(Integer type,String title, Long id) throws Exception {
		if(CmsConstants.AD_TYPE_BANNER != type){
			throw new BusinessException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		return advertisementDAO.isTitleAvailable(type,title,id);
	}
}