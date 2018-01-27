package org.tyrest.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.PyKit;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.core.mysql.ReferenceModel;
import org.tyrest.product.face.constants.ProductConstants;
import org.tyrest.product.face.model.CategoryModel;
import org.tyrest.product.face.orm.dao.CategoryDAO;
import org.tyrest.product.face.orm.entity.Category;
import org.tyrest.product.face.service.CategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 
 *  freeapis
 *  File: CategoryServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CategoryServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月15日		wuqiang		Initial.
 *
 * </pre>
 */
@Service(value="categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<CategoryModel, Category> implements CategoryService
{

	@Autowired
	private CategoryDAO productCategoryDAO ;

	@Override
	public CategoryModel createCategory(CategoryModel model) throws Exception {
		CategoryModel parentCategory = null;
		if(ProductConstants.ROOT_CATEGORY_CODE.equals(model.getParentCode())){
			parentCategory = new CategoryModel();
			parentCategory.setLevelNum(ProductConstants.ROOT_CATEGORY_LEVEL);
			parentCategory.setCategoryCode(ProductConstants.ROOT_CATEGORY_CODE);
			parentCategory.setCategoryName(ProductConstants.ROOT_CATEGORY_NAME);
		}else{
			Category parentCategoryEntity = productCategoryDAO.findByCategoryCode(RequestContext.getAgencyCode(), model.getParentCode());
			if(ValidationUtil.isEmpty(parentCategoryEntity)){
				throw new BadRequestException(MessageConstants.DATA_NOT_FOUND);
			}
			parentCategory = Bean.toModel(parentCategoryEntity,new CategoryModel());
		}
		model.setLevelNum(parentCategory.getLevelNum()+1);
		model.setAgencyCode(RequestContext.getAgencyCode());
		Category entity = this.prepareEntity(model);
		entity.setParentCode(parentCategory.getCategoryCode());
		entity.setCategoryKeywords(model.getCategoryName());
		entity.setOrderNum(0);
		entity.setCategoryPy(PyKit.pin(model.getCategoryName()));
		entity.setSequenceNBR(sequenceGenerator.getNextValue());
		entity.setRecDate(new Date());
		entity.setRecUserId(RequestContext.getExeUserId());
		entity.setRecStatus(CoreConstants.COMMON_ACTIVE);
		productCategoryDAO.insert(entity);
		return Bean.toModel(entity, model);
	}

	@Override
	public String deleteCategory(String agencyCode, String categoryCode) throws Exception {
		Category currentCategory = productCategoryDAO.findByCategoryCode(agencyCode,categoryCode);
		if(!ValidationUtil.isEmpty(currentCategory)){
			this.doDelete(agencyCode,currentCategory);
		}
		return null;
	}
	
	/**
	 * 递归删除子分类
	 * @param agencyCode
	 * @param category
	 * @return
	 * @throws Exception
	 */
	private void doDelete(String agencyCode,Category category) throws Exception{
		//先删除子分类
		for(Category child : productCategoryDAO.findByParentCode(agencyCode,category.getCategoryCode())){
			this.doDelete(agencyCode,child);
		}
		String checkResult = productCategoryDAO.deleteCheck(
				new ReferenceModel("PRODUCT_CATEGORY",
						new String[]{"AGENCY_CODE","PARENT_CODE"},
						new String[]{agencyCode,category.getCategoryCode()}, "子分类"),
				new ReferenceModel("WBSJ_CONTENT",
						new String[]{"CATEGORY_CODE"},
						new String[]{category.getCategoryCode()},"帖子表"));
		if(!ValidationUtil.isEmpty(checkResult)){
			throw new DataValidateException(checkResult);
		}
		//删除分类
		this.productCategoryDAO.delete(category.getSequenceNBR());
	}
	
	@Override
	public CategoryModel updateCategory(CategoryModel model) throws Exception {
		Category currentCategory = productCategoryDAO.findByCategoryCode(model.getAgencyCode(),model.getCategoryCode());
		CategoryModel productCategoryModel = null;
		if(!ValidationUtil.isEmpty(currentCategory)){
			Bean.copyExistPropertis(model, currentCategory);
			//不复制描述信息
			if(ValidationUtil.isEmpty(model.getDescription())){
				currentCategory.setDescription(null);
			}
			currentCategory.setRecDate(new Date());
			currentCategory.setRecUserId(RequestContext.getExeUserId());
			this.productCategoryDAO.update(currentCategory);
			productCategoryModel = Bean.toModel(currentCategory, new CategoryModel());
		}
		return Bean.toModel(currentCategory,productCategoryModel);
	}

	@Override
	public CategoryModel buildCategoryTree(String agencyCode) throws Exception {
		CategoryModel result = Redis.get(ProductConstants.CACHE_KEY_ALL_CATEGORY,agencyCode);
		if(ValidationUtil.isEmpty(result)){
			result = new CategoryModel();
			result.setSequenceNBR(ProductConstants.ROOT_CATEGORY_SEQUENCE);
			result.setLevelNum(ProductConstants.ROOT_CATEGORY_LEVEL);
			result.setCategoryCode(ProductConstants.ROOT_CATEGORY_CODE);
			result.setCategoryName(ProductConstants.ROOT_CATEGORY_NAME);
			this.buildChildCategory(agencyCode, result);
			//如果分类较多，构造一次分类树不容易，所以放入缓存
			Redis.set(result, ProductConstants.CACHE_KEY_ALL_CATEGORY,agencyCode);
		}
		return result;
	}
	
	/**
	 * TODO.递归查找子分类
	 * 
	 * @throws Exception
	 */
	private void buildChildCategory(String agencyCode,CategoryModel rootCategory) throws Exception {
		List<Category> entites = productCategoryDAO.findByParentCode(agencyCode,rootCategory.getCategoryCode());
		List<CategoryModel> children = getModels(entites);
		if (!ValidationUtil.isEmpty(children)) {
			rootCategory.setHasChildren(true);
			rootCategory.setChildren(children);
			for (CategoryModel pc : children) {
				this.buildChildCategory(agencyCode,pc);
			}
		} else {
			rootCategory.setHasChildren(false);
		}
	}

	@Override
	public List<CategoryModel> getByParentCode(String agencyCode, String parentCode) throws Exception {
		List<CategoryModel> result = Bean.toModels(productCategoryDAO.findByParentCode(agencyCode,parentCode),CategoryModel.class);
		for(CategoryModel each : result){
            each.setHasChildren(productCategoryDAO.hasChildren(agencyCode,each.getCategoryCode()));
        }
		return result;
	}

	@Override
	public List<CategoryModel> getByPage(String agencyCode, String parentCode, Page page, String sidx,
										 String sort) throws Exception {
		return this.getModels(productCategoryDAO.findByPage(agencyCode,parentCode,page,sidx,sort));
	}

	@Override
	public CategoryModel getByCategoryCode(String agencyCode, String categoryCode) throws Exception {
		return Bean.toModel(productCategoryDAO.findByCategoryCode(agencyCode, categoryCode), this.getModelClass().newInstance());
	}

	@Override
	public Boolean isCategoryCodeAvailable(String agencyCode, String categoryCode, Long id) throws Exception {
		return productCategoryDAO.isCategoryCodeAvailable(agencyCode,categoryCode,id);
	}

	@Override
	public Boolean isCategoryNameAvailable(String agencyCode, String categoryName, Long id) throws Exception {
		return productCategoryDAO.isCategoryNameAvailable(agencyCode,categoryName,id);
	}

	@Override
	public CategoryModel buildLevelCategoryTree(String agencyCode, int levelNum) throws Exception {
		CategoryModel result = new CategoryModel();
		result.setSequenceNBR(ProductConstants.ROOT_CATEGORY_SEQUENCE);
		result.setLevelNum(ProductConstants.ROOT_CATEGORY_LEVEL);
		result.setCategoryCode(ProductConstants.ROOT_CATEGORY_CODE);
		result.setCategoryName(ProductConstants.ROOT_CATEGORY_NAME);
		this.buildLevelChildCategory(agencyCode, result,levelNum);
		return result;
	}

	@Override
	public String getCascadeCategoryName(String categoryCode) throws Exception {
		Category category = productCategoryDAO.findByCategoryCode(CoreConstants.CODE_SUPER_ADMIN,categoryCode);
		if(ValidationUtil.isEmpty(category)){
			throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
		}
		if(ProductConstants.ROOT_CATEGORY_CODE.equals(category.getParentCode())){
			return category.getCategoryName();
		}
		String resultCategoryName = getCascadeCategoryName(category.getParentCode());

		return resultCategoryName + ">" + category.getCategoryName();
	}

	/**
	 * TODO.
	 * @param agencyCode
	 * @param rootCategory
	 * @throws Exception
	 */
	private void buildLevelChildCategory(String agencyCode, CategoryModel rootCategory,int levelNum) throws Exception {
		List<Category> entities = productCategoryDAO.findByParentCode(agencyCode,rootCategory.getCategoryCode());
		List<CategoryModel> children = this.getModels(entities);
		if (!ValidationUtil.isEmpty(children)) {
			rootCategory.setHasChildren(true);
			rootCategory.setChildren(children);
			for (CategoryModel pc : children) {
				if(pc.getLevelNum() < levelNum){
					this.buildLevelChildCategory(agencyCode,pc,levelNum);
				}
			}
		} else {
			rootCategory.setHasChildren(false);
		}
	}

	/**
	 * 设置分类详细信息
	 * @param entities
	 * @return
	 * @throws Exception
	 */
	private List<CategoryModel> getModels(List<Category> entities) throws Exception{
		List<CategoryModel> models = new ArrayList<CategoryModel>();
		if(!ValidationUtil.isEmpty(entities)){
			for(Category productCategory:entities){
				CategoryModel model = Bean.toModel(productCategory, new CategoryModel());
				models.add(model);
			}
		}
		return models;
	}
}