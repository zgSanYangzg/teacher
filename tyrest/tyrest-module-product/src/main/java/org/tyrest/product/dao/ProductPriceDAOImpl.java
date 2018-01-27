package org.tyrest.product.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.product.face.orm.dao.ProductPriceDAO;
import org.tyrest.product.face.orm.entity.ProductPrice;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductPriceDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductPriceDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="productPriceDAO")
public class ProductPriceDAOImpl extends GenericDAOImpl<ProductPrice> implements ProductPriceDAO
{

	@Override
	public ProductPrice findProductPriceWithCache(Long productSeq) throws Exception {
		ProductPrice productPrice = Redis.getSingle(ProductPrice.class, productSeq.toString());
		if(ValidationUtil.isEmpty(productPrice)){
			StringBuilder sql = new StringBuilder();
			Map<String, Object> params = new HashMap<String, Object>();
			if (!ValidationUtil.isEmpty(productSeq)) {
				sql.append(" AND PRODUCT_SEQ = :PRODUCT_SEQ ");
				params.put("PRODUCT_SEQ", productSeq);
			}
			productPrice = this.findFirst(sql.toString(), params);
			if(!ValidationUtil.isEmpty(productPrice)){
				Redis.setSingle(productPrice, productSeq.toString());
			}
		}
		return productPrice;
	}

	@Override
	public void updateWithCache(ProductPrice productPrice) throws Exception {
		this.update(productPrice);
		Redis.setSingle(productPrice, productPrice.getProductSeq().toString());
	}

	@Override
	public void insertWithCache(ProductPrice productPrice) throws Exception {
		this.insert(productPrice);
		Redis.setSingle(productPrice, productPrice.getProductSeq().toString());
	}
}
