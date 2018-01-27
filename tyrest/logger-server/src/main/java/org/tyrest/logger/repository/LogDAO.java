package org.tyrest.logger.repository;

import java.util.List;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.logger.LogModel;
import org.tyrest.core.search.ElasticSearchDAO;

/**
 * 
 * <pre>
 * 
 *  File: LogDAO.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  LogDAO.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日					magintursh				   Initial.
 *
 * </pre>
 */
public interface LogDAO extends ElasticSearchDAO<LogModel> {

	List<LogModel> findLogs(String type, String key1, String key2, String key3, String key4, String key5, String key6,
			Page page) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */