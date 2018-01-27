package org.tyrest.systemctl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.core.mysql.ReferenceModel;
import org.tyrest.systemctl.face.model.DictionaryEntryModel;
import org.tyrest.systemctl.face.model.DictionaryModel;
import org.tyrest.systemctl.face.orm.dao.DictionaryDAO;
import org.tyrest.systemctl.face.orm.dao.DictionaryEntryDAO;
import org.tyrest.systemctl.face.orm.entity.Dictionary;
import org.tyrest.systemctl.face.orm.entity.DictionaryEntry;
import org.tyrest.systemctl.face.service.DictionaryService;

import javax.persistence.Table;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Service(value="dictionaryService")
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryModel, Dictionary> implements DictionaryService
{

	@Autowired
	private DictionaryDAO dictionaryDAO;
	
	@Autowired
	private DictionaryEntryDAO dictionaryEntryDAO;
	
	@Override
	public DictionaryModel createDictionary(DictionaryModel dictionaryModel) throws Exception {
		Dictionary dic = Bean.toPo(dictionaryModel, new Dictionary());
		dic.setSequenceNBR(sequenceGenerator.getNextValue());
		dic.setAgencyCode(RequestContext.getAgencyCode());
		dic.setRecDate(new Date());
		dic.setRecStatus(CoreConstants.COMMON_ACTIVE);
		dic.setRecUserId(RequestContext.getExeUserId());
		dictionaryDAO.insertWithCache(dic);
		return Bean.toModel(dic, new DictionaryModel());
	}

	@Override
	public String deleteDictionary(String agencyCode, String... dictCodes) throws Exception {
		for(String dictCode :  dictCodes){
			this.deleteOneDictionary(agencyCode,dictCode);
		}
		return null;
	}

	private void deleteOneDictionary(String agencyCode,String dictCode) throws Exception{
		String checkResult = dictionaryDAO.deleteCheck(new ReferenceModel(DictionaryEntry.class.getAnnotation(Table.class).name(),
				new String[]{"DICT_CODE","AGENCY_CODE"},
				new String[]{dictCode,agencyCode},
				"在字典值中")
		);
		if(!ValidationUtil.isEmpty(checkResult)){
			throw new DataValidateException(checkResult);
		}
		dictionaryDAO.deleteWithCache(agencyCode, dictCode);
	}

	@Override
	public DictionaryModel updateDictionary(DictionaryModel dictionaryModel) throws Exception {
		Dictionary dictionary = dictionaryDAO.findByDictCode(dictionaryModel.getAgencyCode(), dictionaryModel.getDictCode());
		if(ValidationUtil.isEmpty(dictionary)){
			throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
		}
		dictionaryModel.setRecDate(new Date());
		dictionaryModel.setRecUserId(RequestContext.getExeUserId());
		Bean.toPo(dictionaryModel, dictionary);
		dictionaryDAO.updateWithCache(dictionary);
		return dictionaryModel;
	}

	@Override
	public DictionaryModel getByCode(String agencyCode, String dictCode) throws Exception {
		return Bean.toModel(dictionaryDAO.findByDictCode(agencyCode, dictCode), new DictionaryModel());
	}

	@Override
	public Page getDictionaryByPage(String agencyCode, String dictCode, String dictName, Page page, String orderBy,
									String order) throws Exception {
		page.setList(Bean.toModels(dictionaryDAO.findByPage(agencyCode, dictCode, dictName, page, orderBy, order),DictionaryModel.class));
		return page;
	}

	@Override 
	public Boolean isDictionaryCodeAvailable(String agencyCode, String dictCode) throws Exception {
		return ValidationUtil.isEmpty(dictionaryDAO.findByDictCode(agencyCode, dictCode));
	}

	@Override
	public DictionaryEntryModel createEntry(DictionaryEntryModel dictionaryEntryModel) throws Exception {
		DictionaryEntry entry = Bean.toPo(dictionaryEntryModel, new DictionaryEntry());
		entry.setSequenceNBR(sequenceGenerator.getNextValue());
		entry.setAgencyCode(RequestContext.getAgencyCode());
		entry.setRecDate(new Date());
		entry.setRecStatus(CoreConstants.COMMON_ACTIVE);
		entry.setRecUserId(RequestContext.getExeUserId());
		entry.setLockStatus(CoreConstants.COMMON_N);
		dictionaryEntryDAO.insertWithCache(entry);
		return Bean.toModel(entry, dictionaryEntryModel);
	}

	@Override
	public String deleteEntry(String agencyCode,String dictCode, String... entryKeys) throws Exception {
		for(String key : entryKeys){
			dictionaryEntryDAO.deleteWithCache(agencyCode,dictCode, key);
		}
		return null;
	}

	@Override
	public DictionaryEntryModel updateEntry(DictionaryEntryModel dictionaryEntryModel) throws Exception {
		if(ValidationUtil.isEmpty(dictionaryEntryModel.getAgencyCode()))
			dictionaryEntryModel.setAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
		DictionaryEntry entry = dictionaryEntryDAO.findEntryByKey(dictionaryEntryModel.getAgencyCode(),
				dictionaryEntryModel.getDictCode(), dictionaryEntryModel.getEntryKey());
		if(ValidationUtil.isEmpty(entry)){
			throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
		}
		dictionaryEntryModel.setRecDate(new Date());
		dictionaryEntryModel.setRecUserId(RequestContext.getExeUserId());
		Bean.toPo(dictionaryEntryModel, entry);
		dictionaryEntryDAO.updateWithCache(entry);
		return Bean.toModel(entry,dictionaryEntryModel);
	}

	@Override
	public DictionaryEntryModel updateEntryLockStatus(String agencyCode, String dictCode, String key) throws Exception {
		DictionaryEntry entry = dictionaryEntryDAO.findEntryByKey(agencyCode,dictCode, key);
		if(ValidationUtil.isEmpty(entry)){
			throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
		}
		String lockStatus = CoreConstants.COMMON_N.equals(entry.getLockStatus()) ? CoreConstants.COMMON_Y : CoreConstants.COMMON_N;
		if(CoreConstants.COMMON_Y.equals(lockStatus)){
			entry.setLockDate(new Date());
			entry.setLockUserId(RequestContext.getExeUserId());
		}
		entry.setLockStatus(lockStatus);
		dictionaryEntryDAO.updateWithCache(entry);
		return Bean.toModel(entry, new DictionaryEntryModel());
	}

	@Override
	public DictionaryEntryModel getEntry(String agencyCode, String dictCode, String key) throws Exception {
		return Bean.toModel(dictionaryEntryDAO.findEntryByKey(agencyCode, dictCode, key), new DictionaryEntryModel());
	}

	@Override
	public String getValue(String agencyCode, String dictCode, String key) throws Exception {
		return dictionaryEntryDAO.findValueByKey(agencyCode, dictCode, key);
	}

	@Override
	public Boolean isEntryKeyAvailable(String agencyCode, String dictCode, String key) throws Exception {
		return ValidationUtil.isEmpty(dictionaryEntryDAO.findEntryByKey(agencyCode, dictCode, key));
	}

	@Override
	public Page getEntryByPage(String agencyCode, String dictCode, Page page, String orderBy, String order)
			throws Exception {
		page.setList(Bean.toModels(dictionaryEntryDAO.findEntiesByPage(agencyCode, dictCode, page, orderBy, order),DictionaryEntryModel.class));
		return page;
	}

	@Override
	public List<DictionaryEntryModel> getEntries(String agencyCode, String dictCode) throws Exception {
		return Bean.toModels(dictionaryEntryDAO.findEntries(agencyCode, dictCode),DictionaryEntryModel.class);
	}

	@Override
	public Map<String, String> getEntryMap(String agencyCode, String dictCode) throws Exception {
		List<DictionaryEntry> entries = dictionaryEntryDAO.findEntries(agencyCode, dictCode);
		Map<String,String> result = new HashMap<>();
		if(!ValidationUtil.isEmpty(entries)){
			for(DictionaryEntry entry : entries){
				result.put(entry.getEntryKey(),entry.getEntryValue());
			}
		}
 		return result;
	}
}
