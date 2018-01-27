package org.tyrest.product.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.product.face.constants.ProductConstants;
import org.tyrest.product.face.orm.dao.CategoryDAO;
import org.tyrest.product.face.orm.entity.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 
 *  freeapis
 *  File: CategoryDAOImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CategoryDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月15日		wuqiang		Initial.
 *
 * </pre>
 */
@Repository(value="categoryDAO")
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements CategoryDAO
{
	@Override
	public void insert(Category productCategory) throws Exception {
		super.insert(productCategory);
		Redis.remove(ProductConstants.CACHE_KEY_ALL_CATEGORY,productCategory.getAgencyCode());
	}

	@Override
	public void update(Category productCategory) throws Exception {
		super.update(productCategory);
		Redis.removeSingle(this.getEntityClass(), productCategory.getAgencyCode(),productCategory.getCategoryCode());
		Redis.remove(ProductConstants.CACHE_KEY_ALL_CATEGORY,productCategory.getAgencyCode());
	}

	@Override
	public void delete(Long id) throws Exception {
		Category  currentCategory = this.findById(id);
		if(!ValidationUtil.isEmpty(currentCategory)){
			super.delete(id);
			Redis.removeSingle(this.getEntityClass(), currentCategory.getAgencyCode(),currentCategory.getCategoryCode());
			Redis.remove(ProductConstants.CACHE_KEY_ALL_CATEGORY,currentCategory.getAgencyCode());
		}
	}

	@Override
	public Category findByCategoryCode(String agencyCode, String categoryCode) throws Exception {
		Category result = Redis.getSingle(Category.class, agencyCode,categoryCode);
		if(ValidationUtil.isEmpty(result)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("AGENCY_CODE",agencyCode);
			params.put("CATEGORY_CODE",categoryCode);
			result = this.findFirst("  AND AGENCY_CODE = :AGENCY_CODE AND CATEGORY_CODE = :CATEGORY_CODE  ", params);
			if(!ValidationUtil.isEmpty(result)){
				Redis.setSingle(result, result.getAgencyCode(),result.getCategoryCode());
			}
		}
		return result;
	}

	@Override
	public List<Category> findByParentCode(String agencyCode, String parentCode) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE",agencyCode);
		params.put("PARENT_CODE",parentCode);
		return this.find("  AND AGENCY_CODE = :AGENCY_CODE AND PARENT_CODE = :PARENT_CODE ", params, "recDate", "desc");
	}

	@Override
	public List<Category> findByPage(String agencyCode, String parentCode, Page page, String sidx, String sort) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE",agencyCode);
		}
		if(!ValidationUtil.isEmpty(parentCode)){
			sql.append(" AND PARENT_CODE = :PARENT_CODE ");
			params.put("PARENT_CODE",parentCode);
		}
		if(ValidationUtil.isEmpty(sidx)){
			sidx = "recDate";
		}
		if(ValidationUtil.isEmpty(sort)){
			sort = "desc";
		}
		return this.paginate(sql.toString(), params, page, sidx, sort);
	}

	@Override
	public boolean hasChildren(String agencyCode, String categoryCode) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("PARENT_CODE",categoryCode);
		params.put("AGENCY_CODE",agencyCode);
		return this.findCount("  AND PARENT_CODE = :PARENT_CODE AND AGENCY_CODE = :AGENCY_CODE ", params).compareTo(0L) > 0 ? true : false;
	}

	@Override
	public Boolean isCategoryCodeAvailable(String agencyCode, String categoryCode, Long id) throws Exception {
		StringBuilder sql = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND CATEGORY_CODE = :CATEGORY_CODE ");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE",agencyCode);
		params.put("CATEGORY_CODE",categoryCode);
		if(!ValidationUtil.isEmpty(id)){
			sql.append(" AND SEQUENCE_NBR!=:SEQUENCE_NBR");
			params.put("SEQUENCE_NBR", id);
		}
		return this.findCount(sql.toString(), params).compareTo(0L) > 0 ? false : true;
	}

	@Override
	public Boolean isCategoryNameAvailable(String agencyCode, String categoryName, Long id) throws Exception {
		StringBuilder sql = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND CATEGORY_NAME = :CATEGORY_NAME ");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE",agencyCode);
		params.put("CATEGORY_NAME",categoryName);
		if(!ValidationUtil.isEmpty(id)){
			sql.append(" AND SEQUENCE_NBR!=:SEQUENCE_NBR");
			params.put("SEQUENCE_NBR", id);
		}
		return this.findCount(sql.toString(), params).compareTo(0L) > 0 ? false : true;
	}

	@Override
	public boolean hasCopy(String agencyCode, Category currentCategory) throws Exception {
		
		StringBuilder sql = new StringBuilder(" AND AGENCY_CODE = :AGENCY_CODE AND EXTEND1 = :EXTEND1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AGENCY_CODE", agencyCode);
		params.put("EXTEND1",currentCategory.getCategoryCode());
		return this.findCount(sql.toString(), params).compareTo(0L) > 0 ? true : false;
	}
}