package org.tyrest.security.face.service;

import org.tyrest.core.mysql.BaseService;
import org.tyrest.security.face.model.OperationModel;
import org.tyrest.security.face.orm.entity.Operation;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityResourceService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityResourceService.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public interface OperationService extends BaseService<OperationModel,Operation>
{

	/**
	 * 创建系统的所有API接口信息
	 * @param operations
	 * @return
	 * @throws Exception 
	 */
	List<OperationModel> createOperations(List<OperationModel> operations) throws Exception;

	/**
	 * 创建单个的资源操作信息
	 * @param operation
	 * @return
	 * @throws Exception 
	 */
	OperationModel createOperation(OperationModel operation) throws Exception;
	/**
	 * TODO.同步模块的所有操作到数据库中
	 * @param operations
	 * @throws Exception
	 */
	void syncOperations(List<OperationModel> operations) throws Exception;

	/**
	 * 获取系统所有资源
	 * @return
	 */
     List<Object> getAllResource() throws Exception;
}
