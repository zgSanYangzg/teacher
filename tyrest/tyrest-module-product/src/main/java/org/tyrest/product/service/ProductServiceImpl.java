package org.tyrest.product.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.product.face.model.ProductModel;
import org.tyrest.product.face.orm.dao.ProductDAO;
import org.tyrest.product.face.orm.entity.Product;
import org.tyrest.product.face.service.ProductService;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ProductServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ProductServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="productService")
public class ProductServiceImpl extends BaseServiceImpl<ProductModel, Product> implements ProductService
{
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@Override
	public ProductModel createProduct(ProductModel productModel) throws Exception{
		Product product = Bean.toPo(productModel, new Product());
		product.setAgencyCode(RequestContext.getAgencyCode());
		product.setSequenceNBR(sequenceGenerator.getNextValue());
		product.setRecDate(new Date());
		product.setRecStatus(CoreConstants.COMMON_ACTIVE);
		product.setRecUserId(RequestContext.getExeUserId());
		
		if(ValidationUtil.isEmpty(product.getLockStatus())){
			product.setLockStatus(CoreConstants.COMMON_N);
		}
		
		productDAO.insertWithCache(product);
		return Bean.toModel(product, new ProductModel());
	}
	
	@Override
	public ProductModel updateProduct(Long id,ProductModel productModel) throws Exception{
		Product product = productDAO.findByIdWithCahce(id);
		if (ValidationUtil.isEmpty(product)) {
			throw new BusinessException(MessageConstants.DATA_NOT_FOUND);
		}
		Bean.copyExistPropertis(productModel, product);
		product.setRecDate(new Date());
		product.setRecUserId(RequestContext.getExeUserId());
		productDAO.updateWithCahce(product);
		return Bean.toModel(product, new ProductModel());
	}
	
	@Override
	public ProductModel getById(Long id) throws Exception{
		Product product = productDAO.findByIdWithCahce(id);
		return Bean.toModel(product, new ProductModel());
	}
	
	@Override
	public String deleteProduct(Long[] ids) throws Exception{
		for(Long id :  ids){
			productDAO.deleteWithCache(id);
		}
		return null;
	}
	
	@Override
	public Page getProductByPage(String agencyCode, String productName, Page page, String orderBy,String order) throws Exception {
		page.setList(productDAO.findByPage(agencyCode,productName,page, orderBy, order));
		return page;
	}
}
