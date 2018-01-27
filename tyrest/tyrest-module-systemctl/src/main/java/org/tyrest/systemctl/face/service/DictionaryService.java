package org.tyrest.systemctl.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.systemctl.face.model.DictionaryEntryModel;
import org.tyrest.systemctl.face.model.DictionaryModel;
import org.tyrest.systemctl.face.orm.entity.Dictionary;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryService.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月12日		framework		Initial.
 *
 * </pre>
 */
public interface DictionaryService extends BaseService<DictionaryModel,Dictionary>
{
	/**
	 * TODO.创建字典
	 * @param dictionaryModel
	 * @return
	 * @throws Exception
	 */
	DictionaryModel createDictionary(DictionaryModel dictionaryModel) throws Exception;
	/**
	 * TODO.删除字典
	 * @param agencyCode
	 * @param dictCodes
	 * @return
	 * @throws Exception
	 */
	String deleteDictionary(String agencyCode, String... dictCodes) throws Exception;
	/**
	 * TODO.更新字典
	 * @param dictionaryModel
	 * @return
	 * @throws Exception
	 */
	DictionaryModel updateDictionary(DictionaryModel dictionaryModel) throws Exception;
	/**
	 * TODO.根据字典编码获取字典
	 * @param agencyCode
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	DictionaryModel getByCode(String agencyCode, String dictCode) throws Exception;
	/**
	 * TODO.分页查询字典
	 * @param agencyCode
	 * @param dictCode
	 * @param dictName
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page getDictionaryByPage(String agencyCode, String dictCode, String dictName, Page page, String orderBy, String order) throws Exception;
	/**
	 * TODO.判断字典编码是否可用
	 * @param agencyCode
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	Boolean isDictionaryCodeAvailable(String agencyCode, String dictCode) throws Exception;
	/**
	 * TODO.创建字典值
	 * @param dictionaryEntryModel
	 * @return
	 * @throws Exception
	 */
	DictionaryEntryModel createEntry(DictionaryEntryModel dictionaryEntryModel) throws Exception;
	/**
	 * TODO.删除字典值
	 * @param agencyCode
	 * @param dictCode
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	String deleteEntry(String agencyCode, String dictCode, String... keys) throws Exception;
	/**
	 * TODO.更新字典值
	 * @param dictionaryEntryModel
	 * @return
	 * @throws Exception
	 */
	DictionaryEntryModel updateEntry(DictionaryEntryModel dictionaryEntryModel) throws Exception;
	/**
	 * TODO.启用/禁用字典值
	 * @param agencyCode
	 * @param dictCode
	 * @param key
	 * @return
	 * @throws Exception
	 */
	DictionaryEntryModel updateEntryLockStatus(String agencyCode, String dictCode, String key) throws Exception;
	/**
	 * TODO.根据字典值key获取字典值
	 * @param agencyCode
	 * @param dictCode
	 * @param key
	 * @return
	 * @throws Exception
	 */
	DictionaryEntryModel getEntry(String agencyCode, String dictCode, String key) throws Exception;
	/**
	 * TODO.根据字典值key获取value
	 * @param agencyCode
	 * @param dictCode
	 * @param key
	 * @return
	 * @throws Exception
	 */
	String getValue(String agencyCode, String dictCode, String key) throws Exception;
	/**
	 * TODO.判断字典值key是否可用
	 * @param agencyCode
	 * @param dictCode
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Boolean isEntryKeyAvailable(String agencyCode, String dictCode, String key) throws Exception;
	/**
	 * TODO.分页查询字典值
	 * @param agencyCode
	 * @param dictCode
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page getEntryByPage(String agencyCode, String dictCode, Page page, String orderBy, String order) throws Exception;
	/**
	 * TODO.根据字典编码获取字典中的所有值
	 * @param agencyCode
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	List<DictionaryEntryModel> getEntries(String agencyCode, String dictCode) throws Exception;

	/**
	 * 根据字典编码获取字典的所有值，以map的形式返回
	 * @param agencyCode
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	Map<String,String> getEntryMap(String agencyCode, String dictCode) throws Exception;
}
