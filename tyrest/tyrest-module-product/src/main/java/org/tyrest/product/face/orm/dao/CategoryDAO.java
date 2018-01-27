package org.tyrest.product.face.orm.dao;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.product.face.orm.entity.Category;

import java.util.List;

/**
 * <pre>
 * 
 *  freeapis
 *  File: CategoryDAO.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CategoryDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月15日		wuqiang		Initial.
 *
 * </pre>
 */
public interface CategoryDAO extends GenericDAO<Category> {

	/**
	 * TODO.根据分类编码获取分类
	 * @param agencyCode
	 * @param categoryCode
	 * @return
	 * @throws Exception
	 */
	Category findByCategoryCode(String agencyCode, String categoryCode) throws Exception;

	/**
	 * 根据父分类查找所有子分类
	 * @param parentCode
	 * @return
	 * @throws Exception 
	 */
	List<Category> findByParentCode(String agencyCode, String parentCode) throws Exception;
	
	/**
	 * 分页查询分类信息
	 * @param agencyCode
	 * @param parentCode
	 * @param page
	 * @param orderBy
	 * @param order
	 * @return
	 */
	List<Category> findByPage(String agencyCode, String parentCode, Page page, String orderBy, String order) throws Exception;

	/**
	 * 检查是否有子节点
	 * @param agencyCode
	 * @param categoryCode
	 * @return
	 */
	boolean hasChildren(String agencyCode, String categoryCode) throws Exception;

	/**
	 * 验证分类编码是否可用
	 * 
	 * @param agencyCode
	 * @param categoryCode
	 * @param id 如果是修改的时候需要传入主键id
	 * @return
	 */
	Boolean isCategoryCodeAvailable(String agencyCode, String categoryCode, Long id) throws Exception;

	/**
	 * 验证分类名称是否可用
	 * @param agencyCode
	 * @param categoryName
	 * @param id 如果是修改的时候需要传入主键id
	 * @return
	 */
	Boolean isCategoryNameAvailable(String agencyCode, String categoryName, Long id) throws Exception;


	/**
	 * 判断模板分类在商家分类中是否有副本
	 * 
	 * @param agencyCode
	 * @param currentCategory
	 * @return
	 */
	boolean hasCopy(String agencyCode, Category currentCategory) throws Exception;

}
