package org.tyrest.systemctl.face.orm.dao;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.DictionaryEntry;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryEntryDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryEntryDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月12日		framework		Initial.
 *
 * </pre>
 */
public interface DictionaryEntryDAO extends GenericDAO<DictionaryEntry>
{
	/**
	 * TODO.添加字典值-带缓存
	 * @param dictionaryEntry
	 * @throws Exception
	 */
	void insertWithCache(DictionaryEntry dictionaryEntry) throws Exception;
	/**
	 * TODO.删除字典值,同时删除缓存
	 * @param agencyCode
	 * @param dictCode
	 * @param
	 * @throws Exception
	 */
	void deleteWithCache(String agencyCode, String dictCode, String entryKey) throws Exception;
	/**
	 * TODO.更新字典值,同时更新缓存
	 * @param dictionaryEntry
	 * @throws Exception
	 */
	void updateWithCache(DictionaryEntry dictionaryEntry) throws Exception;
	/**
	 * TODO.根据字典key获取字典
	 * @param agencyCode
	 * @param dictCode
	 * @param entryKey
	 * @return
	 * @throws Exception
	 */
	DictionaryEntry findEntryByKey(String agencyCode, String dictCode, String entryKey) throws Exception;
	/**
	 * TODO.根据字典key获取字典value
	 * @param agencyCode
	 * @param dictCode
	 * @param entryKey
	 * @return
	 * @throws Exception
	 */
	String findValueByKey(String agencyCode, String dictCode, String entryKey) throws Exception;
	/**
	 * TODO.分页查询字典值
	 * @param agencyCode
	 * @param dictCode
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<DictionaryEntry> findEntiesByPage(String agencyCode, String dictCode, Page page, String orderBy, String order) throws Exception;
	/**
	 * TODO.获取字典中的所有值
	 * @param agencyCode
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	List<DictionaryEntry> findEntries(String agencyCode, String dictCode) throws Exception;


}
