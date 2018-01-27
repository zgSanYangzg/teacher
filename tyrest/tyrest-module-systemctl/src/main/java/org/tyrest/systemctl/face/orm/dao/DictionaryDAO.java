package org.tyrest.systemctl.face.orm.dao;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.Dictionary;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: DictionaryDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月12日		framework		Initial.
 *
 * </pre>
 */
public interface DictionaryDAO extends GenericDAO<Dictionary>
{
	/**
	 * TODO.添加字典-带缓存
	 * @param dictionary
	 * @throws Exception
	 */
	void insertWithCache(Dictionary dictionary) throws Exception;
	/**
	 * TODO.删除字典-带缓存
	 * @param dictCode
	 * @throws Exception
	 */
	void deleteWithCache(String agencyCode, String dictCode) throws Exception;
	/**
	 * TODO.更新字典-带缓存
	 * @param dictionary
	 * @throws Exception
	 */
	void updateWithCache(Dictionary dictionary) throws Exception;
	/**
	 * TODO.根据字典编码查询字典-先从缓存中取,缓存中没有再从数据库取
	 * @param dictCode
	 * @return
	 * @throws Exception
	 */
	Dictionary findByDictCode(String agencyCode, String dictCode) throws Exception;
	/**
	 * TODO.分页查询字典
	 * @param agencyCode
	 * @param dictCode
	 * @param dictName
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<Dictionary> findByPage(String agencyCode, String dictCode, String dictName, Page page, String orderBy, String order) throws Exception;
}
