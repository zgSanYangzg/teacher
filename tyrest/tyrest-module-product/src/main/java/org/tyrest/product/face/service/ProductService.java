package org.tyrest.product.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.product.face.model.ProductModel;
import org.tyrest.product.face.orm.entity.Product;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ProductService extends BaseService<ProductModel,Product>
{

	ProductModel createProduct(ProductModel productModel) throws Exception;

	ProductModel updateProduct(Long id, ProductModel productModel) throws Exception;

	ProductModel getById(Long id) throws Exception;

	String deleteProduct(Long[] ids) throws Exception;

	Page getProductByPage(String agencyCode, String productName, Page page, String orderBy, String order)
			throws Exception;
	
}
