package org.tyrest.systemctl.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.constants.DictionaryConstants;
import org.tyrest.systemctl.face.orm.dao.DictionaryEntryDAO;
import org.tyrest.systemctl.face.orm.entity.DictionaryEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryEntryDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryEntryDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Repository(value = "dictionaryEntryDAO")
public class DictionaryEntryDAOImpl extends GenericDAOImpl<DictionaryEntry> implements DictionaryEntryDAO {


	@Override
	public void insertWithCache(DictionaryEntry dictionaryEntry) throws Exception {
		this.insert(dictionaryEntry);
		Redis.setSingle(dictionaryEntry, dictionaryEntry.getAgencyCode(), dictionaryEntry.getDictCode(),
				dictionaryEntry.getEntryKey());
		Redis.remove(DictionaryConstants.DICTIONARYENTRY_LIST_CACHE_KEY,dictionaryEntry.getAgencyCode(), dictionaryEntry.getDictCode());
	}

	@Override
	public void deleteWithCache(String agencyCode,String dictCode,String entryKey) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("DICT_CODE", dictCode);
		params.put("ENTRY_KEY",entryKey);
		this.update("DELETE FROM "+ this.tableName() +" WHERE AGENCY_CODE = :AGENCY_CODE AND DICT_CODE = :DICT_CODE AND ENTRY_KEY = :ENTRY_KEY",params);
		Redis.removeSingle(DictionaryEntry.class, agencyCode, dictCode,entryKey);
		Redis.remove(DictionaryConstants.DICTIONARYENTRY_LIST_CACHE_KEY,agencyCode,dictCode);
	}

	@Override
	public void updateWithCache(DictionaryEntry dictionaryEntry) throws Exception {
		this.update(dictionaryEntry);
		Redis.setSingle(dictionaryEntry, dictionaryEntry.getAgencyCode(), dictionaryEntry.getDictCode(),
				dictionaryEntry.getEntryKey());
		Redis.remove(DictionaryConstants.DICTIONARYENTRY_LIST_CACHE_KEY,dictionaryEntry.getAgencyCode(), dictionaryEntry.getDictCode());
	}


	@Override
	public DictionaryEntry findEntryByKey(String agencyCode, String dictCode, String entryKey) throws Exception {
		DictionaryEntry dictionaryEntry = Redis.getSingle(this.getEntityClass(), agencyCode, dictCode, entryKey);
		if (ValidationUtil.isEmpty(dictionaryEntry)) {
			StringBuilder sqlSufix = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND DICT_CODE = :DICT_CODE ")
					.append(" AND ENTRY_KEY = :ENTRY_KEY ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("AGENCY_CODE", agencyCode);
			params.put("DICT_CODE", dictCode);
			params.put("ENTRY_KEY", entryKey);
			dictionaryEntry = this.findFirst(sqlSufix.toString(), params);
			if (!ValidationUtil.isEmpty(dictionaryEntry)) {
				Redis.setSingle(dictionaryEntry, dictionaryEntry.getAgencyCode(), dictionaryEntry.getDictCode(),
						dictionaryEntry.getEntryKey());
			}
		}
		return dictionaryEntry;
	}

	@Override
	public String findValueByKey(String agencyCode, String dictCode, String entryKey) throws Exception {
		DictionaryEntry dictionaryEntry = this.findEntryByKey(agencyCode, dictCode, entryKey);
		return !ValidationUtil.isEmpty(dictionaryEntry) ? dictionaryEntry.getEntryValue() : null;
	}

	@Override
	public List<DictionaryEntry> findEntiesByPage(String agencyCode, String dictCode, Page page, String orderBy,
												  String order) throws Exception {
		StringBuilder sqlSufix = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (!ValidationUtil.isEmpty(agencyCode)) {
			sqlSufix.append(" AND AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if (!ValidationUtil.isEmpty(dictCode)) {
			sqlSufix.append(" AND DICT_CODE = :DICT_CODE ");
			params.put("DICT_CODE", dictCode);
		}
		return this.paginate(sqlSufix.toString(), params, page, orderBy, order);
	}

	@Override
	public List<DictionaryEntry> findEntries(String agencyCode, String dictCode) throws Exception {
		List<DictionaryEntry> result = Redis.get(DictionaryConstants.DICTIONARYENTRY_LIST_CACHE_KEY,agencyCode,dictCode);
		if(ValidationUtil.isEmpty(result)){
			StringBuilder sqlSufix = new StringBuilder();
			Map<String, Object> params = new HashMap<String, Object>();
			if (!ValidationUtil.isEmpty(agencyCode)) {
				sqlSufix.append(" AND AGENCY_CODE = :AGENCY_CODE ");
				params.put("AGENCY_CODE", agencyCode);
			}
			if (!ValidationUtil.isEmpty(dictCode)) {
				sqlSufix.append(" AND DICT_CODE = :DICT_CODE ");
				params.put("DICT_CODE", dictCode);
			}
			result = this.find(sqlSufix.toString(), params, null, null);
			if(!ValidationUtil.isEmpty(result)){
				Redis.set(result,DictionaryConstants.DICTIONARYENTRY_LIST_CACHE_KEY,agencyCode,dictCode);
			}
		}
		return result;
	}

}
