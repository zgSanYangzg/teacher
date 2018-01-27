package org.tyrest.product.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.product.face.orm.entity.ProductPrice;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductPriceDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductPriceDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ProductPriceDAO extends GenericDAO<ProductPrice>
{
	/**
	 * 查询商品价格
	 * @param productSeq
	 * @return
	 * @throws Exception
	 */
	public ProductPrice findProductPriceWithCache(Long productSeq) throws Exception;
	
	/**
	 * 更新商品价格
	 * @param productPrice
	 * @throws Exception
	 */
	public void updateWithCache(ProductPrice productPrice) throws Exception;
	
	/**
	 * 插入商品价格
	 * @param productPrice
	 * @return
	 * @throws Exception
	 */
	public void insertWithCache(ProductPrice productPrice) throws Exception;
}
