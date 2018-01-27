package org.tyrest.product.face.orm.dao;

import java.util.List;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.product.face.orm.entity.Product;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ProductDAO extends GenericDAO<Product>
{
	void insertWithCache(Product product) throws Exception;
	
	void deleteWithCache(Long id) throws Exception;
	
	void updateWithCahce(Product product) throws Exception;
	
	Product findByIdWithCahce(Long id) throws Exception;
	
	List<Product> findByPage(String agencyCode,String productName,Page page,String orderBy,String order) throws Exception;

}
