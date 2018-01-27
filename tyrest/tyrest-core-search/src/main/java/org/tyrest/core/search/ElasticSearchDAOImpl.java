package org.tyrest.core.search;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ElasticSearchDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ElasticSearchDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="elasticSearchDAO")
public class ElasticSearchDAOImpl<T> implements ElasticSearchDAO<T>{

	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	private Class<T> entityClass = null;
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass(){
		if(entityClass == null){
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}

	@Override
	public void createIndex(T document,String type) throws Exception {
		
		IndexQuery indexQuery = new IndexQueryBuilder().withObject(document).withType(type).build();
		esTemplate.index(indexQuery);
	}

	@Override
	public void deleteIndex(String id) throws Exception {
		esTemplate.delete(getEntityClass(), id);
	}

	@Override
	public void updateIndex(T document) throws Exception {
		esTemplate.delete(getEntityClass(),"");
		IndexQuery indexQuery = new IndexQueryBuilder().withId("").withObject(document).build();
		esTemplate.index(indexQuery);
	}

	@Override
	public List<T> findByPage(String type, Page page, Map<String, Object> params) throws Exception {
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		if(!ValidationUtil.isEmpty(params)){
			for(String key : params.keySet()){
				if(!ValidationUtil.isEmpty(key)){
					boolQuery.must(QueryBuilders.matchQuery(key, params.get(key)));
				}
			}
		}
    	NativeSearchQuery nsq = new NativeSearchQuery(boolQuery);
    	nsq.addSort(new Sort(Direction.DESC, "recDate"));
    	
    	if(!ValidationUtil.isEmpty(type)){
    		nsq.addTypes(new String[]{type});
    	}
    	
    	nsq.setPageable(new PageRequest(page.getPageStartRow() / page.getPageRecorders(),page.getPageRecorders()));
    	org.springframework.data.domain.Page<T> resultPage = esTemplate.queryForPage(nsq, getEntityClass());
    	page.setTotalRows((int)resultPage.getTotalElements());
    	return resultPage.getContent();
	}
}

/*
*$Log: av-env.bat,v $
*/