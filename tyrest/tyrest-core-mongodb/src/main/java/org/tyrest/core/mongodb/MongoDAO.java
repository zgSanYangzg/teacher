package org.tyrest.core.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: MongoDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: MongoDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public interface MongoDAO<T>
{
	public MongoOperations getMongoOp();

	public void insert(T t);

	public void save(T t);

	public T findOne(Long id);
	
	public T findOne(String id);

	public List<T> findAll();

	public List<T> findByRegex(String regex);

	public void removeOne(Long id);
	
	public void removeOne(String id);

	public void removeAll();

	public void updateOne(Long id,Map<String,Object> params);
	
	public void updateOne(String id,Map<String,Object> params);

}

/*
 * $Log: av-env.bat,v $
 */