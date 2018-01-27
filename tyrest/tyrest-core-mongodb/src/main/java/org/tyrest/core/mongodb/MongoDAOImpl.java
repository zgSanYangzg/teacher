package org.tyrest.core.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: MongoDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: MongoDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component("mongoDAO")
public class MongoDAOImpl<T> implements MongoDAO<T> {

	@Autowired
	private MongoTemplate mongoTemplate;

	private Class<T> entityClass = null;

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return entityClass;
	}

	@Override
	public MongoOperations getMongoOp() {
		return mongoTemplate;
	}

	@Override
	public void insert(T t) {
		mongoTemplate.insert(t);
	}
	@Override
	public void save(T t)
	{
		mongoTemplate.save(t);
	}

	@Override
	public void removeAll() {
		List<T> list = this.findAll();
		if (list != null) {
			for (T t : list) {
				mongoTemplate.remove(t);
			}
		}
	}

	@Override
	public void removeOne(Long id) {
		Criteria criteria = Criteria.where("_id").in(id);
		if (criteria != null) {
			Query query = new Query(criteria);
			T currentEntity = mongoTemplate.findOne(query, getEntityClass());
			if (query != null && currentEntity != null)
				mongoTemplate.remove(currentEntity);
		}
	}

	@Override
	public void removeOne(String id) {
		Criteria criteria = Criteria.where("_id").in(id);
		if (criteria != null) {
			Query query = new Query(criteria);
			T currentEntity = mongoTemplate.findOne(query, getEntityClass());
			if (query != null && currentEntity != null)
				mongoTemplate.remove(currentEntity);
		}
	}

	@Override
	public void updateOne(Long id, Map<String, Object> params) {
		Update update = new Update();
		if (!params.isEmpty()) {
			for (String key : params.keySet()) {
				update.set(key, params.get(key));
			}
			mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), update, getEntityClass());
		}
	}

	@Override
	public void updateOne(String id, Map<String, Object> params) {
		Update update = new Update();
		if (!params.isEmpty()) {
			for (String key : params.keySet()) {
				update.set(key, params.get(key));
			}
			mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), update, getEntityClass());
		}
	}

	@Override
	public List<T> findAll() {
		return mongoTemplate.find(new Query(), getEntityClass());
	}

	@Override
	public List<T> findByRegex(String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Criteria criteria = new Criteria("name").regex(pattern.toString());
		return mongoTemplate.find(new Query(criteria), getEntityClass());
	}

	@Override
	public T findOne(Long id) {
		return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), getEntityClass());
	}

	@Override
	public T findOne(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), getEntityClass());
	}

}

/*
 * $Log: av-env.bat,v $
 */