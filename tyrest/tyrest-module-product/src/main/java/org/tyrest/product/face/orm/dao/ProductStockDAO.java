package org.tyrest.product.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.product.face.orm.entity.ProductStock;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductStockDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductStockDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ProductStockDAO extends GenericDAO<ProductStock>
{
	/**
	 * 插入库存
	 * @param productStock
	 * @throws Exception
	 */
	public void insertWithCache(ProductStock productStock) throws Exception;

	/**
	 * 查询库存
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public ProductStock findStock(Long productId) throws Exception;

	/**
	 * 删除库存
	 * @param productId
	 * @throws Exception
	 */
	public void deleteWithCache(Long productId) throws Exception;

	/**
	 * 修改库存
	 * @param productId
	 * @param count
	 * @param updateVersion
	 * @return 
	 * @throws Exception
	 */
	public void updateWithCache(Long productId, Integer count,Integer inflowAdd,Long updateVersion) throws Exception;
}
