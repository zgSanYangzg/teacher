package org.tyrest.product.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;

import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.product.face.orm.dao.ProductStockDAO;
import org.tyrest.product.face.orm.entity.ProductStock;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductStockDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductStockDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value = "productStockDAO")
public class ProductStockDAOImpl extends GenericDAOImpl<ProductStock>implements ProductStockDAO {

	@Override
	public void insertWithCache(ProductStock productStock) throws Exception {
		this.insert(productStock);
		Redis.setSingle(productStock, productStock.getProductSeq().toString());
	}

	@Override
	public ProductStock findStock(Long productId) throws Exception {
		ProductStock productStock = Redis.getSingle(ProductStock.class, productId.toString());
		if(ValidationUtil.isEmpty(productStock)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("PRODUCT_SEQ", productId);
			productStock = this.findFirst(" AND PRODUCT_SEQ=:PRODUCT_SEQ ", params);
			if(!ValidationUtil.isEmpty(productStock)){
				Redis.setSingle(productStock, productId.toString());
			}
		}
		return productStock;
	}

	@Override
	public void deleteWithCache(Long productId) throws Exception {
		ProductStock productStock = this.findStock(productId);
		if(!ValidationUtil.isEmpty(productStock)){
			this.delete(productId);
			Redis.removeSingle(ProductStock.class, productId.toString());
		}
	}

	@Override
	public void updateWithCache(Long productId, Integer count,Integer inflowAdd,Long updateVersion) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("PRODUCT_SEQ", productId);
		params.put("COUNT", count);
		params.put("UPDATE_VERSION", updateVersion);
		params.put("inflowAdd", inflowAdd);
		Integer affectedRow = this.update(" UPDATE  PRODUCT_STOCK SET STOCK_COUNT=:COUNT "
				+ " AND UPDATE_VERSION = UPDATE_VERSION+1 "
				+ " AND TOTAL_INFLOW = TOTAL_INFLOW + :inflowAdd "
				+ " WHERE PRODUCT_SEQ=:PRODUCT_SEQ AND UPDATE_VERSION=:UPDATE_VERSION", params);
		if(affectedRow<=0){
			throw new Exception("update_fail");
		}
		Redis.removeSingle(ProductStock.class, productId.toString());
	}

}
