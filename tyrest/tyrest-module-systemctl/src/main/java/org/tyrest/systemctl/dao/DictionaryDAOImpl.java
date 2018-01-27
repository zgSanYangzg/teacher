package org.tyrest.systemctl.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.constants.DictionaryConstants;
import org.tyrest.systemctl.face.orm.dao.DictionaryDAO;
import org.tyrest.systemctl.face.orm.entity.Dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Repository(value = "dictionaryDAO")
public class DictionaryDAOImpl extends GenericDAOImpl<Dictionary> implements DictionaryDAO {

	@Override
	public void insertWithCache(Dictionary dictionary) throws Exception {
		this.insert(dictionary);
		Redis.setSingle(dictionary,dictionary.getAgencyCode(), dictionary.getDictCode());
	}

	@Override
	public void deleteWithCache(String agencyCode, String dictCode) throws Exception {
		Dictionary currentDic = this.findByDictCode(agencyCode, dictCode);
		if (!ValidationUtil.isEmpty(currentDic)) {
			this.delete(currentDic.getSequenceNBR());
			Redis.removeSingle(this.getEntityClass(),agencyCode, dictCode);
			Redis.remove(DictionaryConstants.DICTIONARYENTRY_LIST_CACHE_KEY,agencyCode,dictCode);
		}
	}

	@Override
	public void updateWithCache(Dictionary dictionary) throws Exception {
		this.update(dictionary);
		Redis.setSingle(dictionary,dictionary.getAgencyCode(), dictionary.getDictCode());
	}

	@Override
	public Dictionary findByDictCode(String agencyCode, String dictCode) throws Exception {
		Dictionary dictionary = Redis.getSingle(this.getEntityClass(),agencyCode, dictCode);
		if (ValidationUtil.isEmpty(dictionary)) {
			StringBuilder sqlSufix = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND DICT_CODE = :DICT_CODE ");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("AGENCY_CODE", agencyCode);
			params.put("DICT_CODE", dictCode);
			dictionary = this.findFirst(sqlSufix.toString(), params);
			if (!ValidationUtil.isEmpty(dictionary)) {
				Redis.setSingle(dictionary,dictionary.getAgencyCode(), dictionary.getDictCode());
			}
		}
		return dictionary;
	}

	@Override
	public List<Dictionary> findByPage(String agencyCode, String dictCode, String dictName, Page page, String orderBy,
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
		if (!ValidationUtil.isEmpty(dictName)) {
			sqlSufix.append(" AND DICT_NAME = :DICT_NAME ");
			params.put("DICT_NAME", (dictName));
		}
		return this.paginate(sqlSufix.toString(), params, page, orderBy, order);
	}
}
