package org.tyrest.security.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.Operation;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityResourceDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityResourceDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public interface OperationDAO extends GenericDAO<Operation>
{

	/**
	 * 删除所有的资源操作信息
	 * @return 返回受影响的行数
	 * @throws Exception
	 */
	int deleteAllResource() throws Exception;

	/**
	 * 根据fid查询资源详细信息
	 * @param funcId
	 * @return
	 * @throws Exception
	 */
	Operation findByFid(String funcId) throws Exception;

	/**
	 * 获取系统所有资源信息
	 * @return
	 */
	 List<Operation> findAllResource()throws Exception;
}
