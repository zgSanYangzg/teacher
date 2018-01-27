package org.tyrest.asi.face.service;

import java.util.List;
import java.util.Map;

import org.tyrest.asi.face.model.ASIBizModel;
import org.tyrest.asi.face.model.GroupModel;
import org.tyrest.asi.face.orm.entity.ASIBiz;
import org.tyrest.asi.face.service.enums.ASIType;
import org.tyrest.core.mysql.BaseService;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ASIService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ASIService extends BaseService<ASIBizModel, ASIBiz>
{
	/**
	 * TODO.获取分组的元数据
	 * @param agencyCode
	 * @param groupCode
	 * @return
	 * @throws Exception
	 */
	GroupModel getASIMeta(String agencyCode,String groupCode) throws Exception;
	/**
	 * TODO.根据分组和业务ID获取动态表单数据
	 * @param agencyCode
	 * @param groupCode
	 * @param entityType
	 * @param entityId
	 * @return
	 * @throws Exception
	 */
	GroupModel getASIData(String agencyCode,String groupCode,String entityType,String entityId) throws Exception;
	/**
	 * TODO.更新动态表单数据
	 * @param agencyCode
	 * @param entityType
	 * @param entityId
	 * @param tableValues
	 * @param formValues
	 * @throws Exception
	 */
	public void updateASIData(String agencyCode, String entityType, String entityId,
			Map<String,List<Map<String,String>>> tableValues,Map<String,Map<String,String>> formValues,ASIType asiType) throws Exception;
	/**
	 * TODO.将动态表单组和具体的业务类型关联
	 * @param asiBizModel
	 * @return
	 * @throws Exception
	 */
	ASIBizModel upsertASIBiz(ASIBizModel asiBizModel) throws Exception;
	/**
	 * TODO.获取实体关联的动态表单数据
	 * @param agencyCode
	 * @param entityType
	 * @param entityId
	 * @return
	 * @throws Exception
	 */
	GroupModel getValues(String agencyCode, String entityType, String entityId) throws Exception;
	
}
