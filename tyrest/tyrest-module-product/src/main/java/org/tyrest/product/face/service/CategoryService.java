package org.tyrest.product.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.product.face.model.CategoryModel;
import org.tyrest.product.face.orm.entity.Category;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: CategoryService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CategoryService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月28日		yangbochao		Initial.
 *
 * </pre>
 */
public interface CategoryService extends BaseService<CategoryModel, Category> {
	/**
	 * TODO.添加分类
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public CategoryModel createCategory(CategoryModel model) throws Exception;

	/**
	 * TODO.删除分类
	 * 
	 * @param agencyCode
	 * @param categoryCode
	 * @return
	 * @throws Exception
	 */
	public String deleteCategory(String agencyCode, String categoryCode) throws Exception;

	/**
	 * 更新分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public CategoryModel updateCategory(CategoryModel model) throws Exception;
	
	/**
	 * TODO.构造分类树形结构
	 * 
	 * @param agencyCode
	 * @return
	 * @throws Exception
	 */
	CategoryModel buildCategoryTree(String agencyCode) throws Exception;


	/**
	 * 根据父编码获取子分类
	 * 
	 * @param agencyCode
	 * @return
	 */
	List<CategoryModel> getByParentCode(String agencyCode, String parentCode) throws Exception;
	
	/**
	 * TODO.分页查询分类
	 * 
	 * @param agencyCode
	 * @param parentCode
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<CategoryModel> getByPage(String agencyCode, String parentCode, Page page, String sidx, String sort)
			throws Exception;
	
	/**
	 * TODO.通过编码获取分类
	 * 
	 * @param agencyCode
	 * @param categoryCode
	 * @return
	 * @throws Exception
	 */
	CategoryModel getByCategoryCode(String agencyCode, String categoryCode) throws Exception;

	/**
	 * 验证分类编码是否可用
	 * 
	 * @param agencyCode
	 * @param categoryCode
	 * @param id
	 * @return
	 */
	public Boolean isCategoryCodeAvailable(String agencyCode, String categoryCode, Long id) throws Exception;

	/**
	 * 验证分类名称是否可用
	 * 
	 * @param agencyCode
	 * @param categoryName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean isCategoryNameAvailable(String agencyCode, String categoryName, Long id)	throws Exception;

	/**
	 * TODO.根据层级获取分类
	 * 
	 * @param agencyCode
	 * @param levelNum 分类最深层级
	 * @return
	 * @throws Exception
	 */
	public CategoryModel buildLevelCategoryTree(String agencyCode, int levelNum) throws Exception;

	/**
	 * 拼接此分类的树的名称
	 * @param categoryCode
	 * @return
	 * @throws Exception
	 */
	public String getCascadeCategoryName(String categoryCode) throws Exception;

}