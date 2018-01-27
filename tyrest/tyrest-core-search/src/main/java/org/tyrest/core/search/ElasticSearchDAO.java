package org.tyrest.core.search;

import java.util.List;
import java.util.Map;

import org.tyrest.core.foundation.model.Page;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: ElasticSearchDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  Description:ElasticSearch的数据访问接口
 * 
 *  Notes:
 *  $Id: ElasticSearchDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */

public interface ElasticSearchDAO<T> {
	/**
	 * TODO.创建索引
	 * @param document
	 * @throws Exception
	 */
	void createIndex(T document, String type) throws Exception;
	/**
	 * TODO.删除索引
	 * @param id
	 * @throws Exception
	 */
	void deleteIndex(String id) throws Exception;
	/**
	 * TODO.更新索引
	 * @param document
	 * @throws Exception
	 */
	void updateIndex(T document) throws Exception;
	/**
	 * TODO.全文检索分页查询
	 * @param type
	 * @param page
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<T> findByPage(String type,Page page,Map<String,Object> params) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/