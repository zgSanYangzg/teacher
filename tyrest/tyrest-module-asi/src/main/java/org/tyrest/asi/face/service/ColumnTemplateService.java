package org.tyrest.asi.face.service;

import org.tyrest.asi.face.model.ColumnTemplateModel;
import org.tyrest.asi.face.orm.entity.ColumnTemplate;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ColumnTemplateService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ColumnTemplateService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ColumnTemplateService extends BaseService<ColumnTemplateModel,ColumnTemplate>
{
	/**
	 * TODO.创建列定义模板
	 * @param columnTemplateModel
	 * @throws Exception
	 */
	ColumnTemplateModel createColumnTemplate(ColumnTemplateModel columnTemplateModel) throws Exception;
	/**
	 * TODO.删除列定义模板
	 * @param agencyCode
	 * @param columnCode
	 * @throws Exception
	 */
	String deleteColumnTemplate(String agencyCode,String columnCode) throws Exception;
	/**
	 * TODO.更新列定义模板
	 * @param columnTemplateModel
	 * @throws Exception
	 */
	ColumnTemplateModel updateColumnTemplate(ColumnTemplateModel columnTemplateModel) throws Exception;
	/**
	 * TODO.获取列定义模板详情
	 * @param agencyCode
	 * @param columnCode
	 * @return
	 * @throws Exception
	 */
	ColumnTemplateModel getColumnTemplate(String agencyCode,String columnCode) throws Exception;
	/**
	 * TODO.获取列定义模板列表
	 * @param agencyCode
	 * @param columnCode
	 * @param page
	 * @return
	 * @throws Exception
	 */
	Page getColumnTemplateByPage(String agencyCode,String columnCode,String columnName,Page page,String orderBy,String order) throws Exception;
	/**
	 * TODO.判断字段编码是否可用
	 * @param agencyCode
	 * @param columnCode
	 * @return
	 * @throws Exception
	 */
	boolean isColumnCodeAvailable(String agencyCode,String columnCode) throws Exception;
}
