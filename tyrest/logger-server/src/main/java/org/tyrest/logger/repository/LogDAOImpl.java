package org.tyrest.logger.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.logger.LogModel;
import org.tyrest.core.search.ElasticSearchDAOImpl;

/**
 * 
 * <pre>
 * 
 *  File: LogDAOImpl.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  LogDAOImpl.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日					magintursh				   Initial.
 *
 * </pre>
 */
@Component(value = "logDAO")
public class LogDAOImpl extends ElasticSearchDAOImpl<LogModel> implements LogDAO {

	@Override
	public List<LogModel> findLogs(String type, String key1, String key2, String key3, String key4, String key5,
			String key6, Page page) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		if (!ValidationUtil.isEmpty(key1)) {
			params.put("searchKey1", key1);
		}
		if (!ValidationUtil.isEmpty(key2)) {
			params.put("searchKey2", key2);
		}
		if (!ValidationUtil.isEmpty(key3)) {
			params.put("searchKey3", key3);
		}
		if (!ValidationUtil.isEmpty(key4)) {
			params.put("searchKey4", key4);
		}
		if (!ValidationUtil.isEmpty(key5)) {
			params.put("searchKey5", key5);
		}
		if (!ValidationUtil.isEmpty(key6)) {
			params.put("searchKey6", key6);
		}
		return this.findByPage(type, page, params);
	}

}

/*
 * $Log: av-env.bat,v $
 */