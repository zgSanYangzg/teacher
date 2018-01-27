package org.tyrest.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.product.face.orm.dao.ProductDAO;
import org.tyrest.product.face.orm.entity.Product;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="productDAO")
public class ProductDAOImpl extends GenericDAOImpl<Product> implements ProductDAO
{
	
	@Override
	public void insertWithCache(Product product) throws Exception {
		this.insert(product);
		Redis.setSingle(product, product.getSequenceNBR().toString());
	}

	@Override
	public void deleteWithCache(Long id) throws Exception {
		this.delete(id);
		Redis.removeSingle(this.getEntityClass(),id.toString());
	}

	@Override
	public void updateWithCahce(Product product) throws Exception {
		this.update(product);
		Redis.setSingle(product,product.getSequenceNBR().toString());
	}

	@Override
	public Product findByIdWithCahce(Long id) throws Exception {
		Product product = Redis.get(id.toString());
		if(ValidationUtil.isEmpty(product)){
			product = this.findById(id);
			if(!ValidationUtil.isEmpty(product)){
				Redis.setSingle(product,product.getSequenceNBR().toString());
			}
		}
		return product;
	}

	@Override
	public List<Product> findByPage(String agencyCode, String productName, Page page, String orderBy, String order)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (!ValidationUtil.isEmpty(agencyCode)) {
			sql.append(" AND AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if (!ValidationUtil.isEmpty(productName)) {
			sql.append(" AND PRODUCT_NAME = :PRODUCT_NAME ");
			params.put("PRODUCT_NAME", productName);
		}
		return this.paginate(sql.toString(), params, page, orderBy, order);
	}
}
