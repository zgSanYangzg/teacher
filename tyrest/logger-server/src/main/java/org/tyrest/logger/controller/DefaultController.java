package org.tyrest.logger.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.hppc.cursors.ObjectObjectCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.JsonDateValueProcessor;
import org.tyrest.core.foundation.utils.StringUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.logger.LogEntityEnum;
import org.tyrest.logger.repository.LogDAO;
import org.tyrest.logger.utils.ResponseUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 * @author magintursh
 *
 */
@Controller
public class DefaultController {
	@Autowired
	private LogDAO logDAO;

	@RequestMapping("logquery")
	public void logquery(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "searchKey1", required = false) String searchKey1,
			@RequestParam(value = "searchKey2", required = false) String searchKey2,
			@RequestParam(value = "searchKey3", required = false) String searchKey3,
			@RequestParam(value = "searchKey4", required = false) String searchKey4,
			@RequestParam(value = "searchKey5", required = false) String searchKey5,
			@RequestParam(value = "searchKey6", required = false) String searchKey6,
			@RequestParam(value = "start", required = true, defaultValue = "0") int start,
			@RequestParam(value = "length", required = true, defaultValue = "10") int length) throws Exception {
		Page page = new Page(length, start);
		searchKey1 = StringUtil.iso2UTF8(searchKey1);
		searchKey2 = StringUtil.iso2UTF8(searchKey2);
		page.setList(
				logDAO.findLogs(type, searchKey1, searchKey2, searchKey3, searchKey4, searchKey5, searchKey6, page));
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		ResponseUtil.success(response, JSONObject.fromObject(page, jsonConfig));
	}

	@RequestMapping("initquery")
	public void initquery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Map<String, String>> searchKeyMapping = new HashMap<String, Map<String, String>>();

		Client esclient = (TransportClient) SpringContextHelper.getBean("client");
		List<String> typeList = null;
		ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> f = esclient.admin().indices()
				.getMappings(new GetMappingsRequest().indices("tyrest-log")).actionGet().getMappings();
		Object[] indexList = f.keys().toArray();
		for (Object indexObj : indexList) {
			String index = indexObj.toString();
			ImmutableOpenMap<String, MappingMetaData> mapping = f.get(index);
			typeList = new ArrayList<String>();
			for (ObjectObjectCursor<String, MappingMetaData> c : mapping) {
				typeList.add(c.key);
			}
		}

		LogEntityEnum currentItem = null;
		for (String currentType : typeList) {
			currentItem = LogEntityEnum.getEntityEnum(currentType);
			if(!ValidationUtil.isEmpty(currentItem)){
				searchKeyMapping.put(currentType, currentItem.getKeyLabel());
			}
		}
		result.put("searchKeyMapping", searchKeyMapping);
		ResponseUtil.success(response, JSONObject.fromObject(result));
	}
}

/*
 * $Log: av-env.bat,v $
 */