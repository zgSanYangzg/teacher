package org.tyrest.security.face.orm.dao;


import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.Module;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SecurityModuleDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SecurityModuleDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年8月2日		Initial.
 *
 * </pre>
 */
public interface ModuleDAO extends GenericDAO<Module>
{

	/**
	 * 根据模块编号查询模块信息
	 * @param moduleCode
	 * @return
	 * @throws Exception
	 * @throws Exception 
	 */
	Module findByModuleCode(String moduleCode) throws Exception;

	/**
	 *
	 * @param parentCode
	 * @return
	 * @throws Exception
	 */
	List<Module> findByParentCode(String parentCode)throws Exception;

	/**
	 * 根据系统端（SYS|AGENCY）查询模块信息
	 * @param port
	 * @return
	 * @throws Exception
	 */
	List<Module> findByPort(String port) throws Exception;

	/**
	 * 分页查询模块信息
	 * @param moduleCode
	 * @param moduleName
	 * @param parentCode
	 * @param port
	 * @param lockStatus
	 * @param page
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws Exception
	 */
	List<Module> getByPage(String moduleCode, String moduleName, String parentCode, String port,
                           String lockStatus, Page page, String orderBy, String order)throws Exception;

	/**
	 * 删除模块
	 * @param moduleCode
	 * @throws Exception
	 */
	void deleteByModuleCode(String moduleCode)throws Exception;

	/**
	 * 判断模块code是否可用
	 * @param moduleCode
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean isModuleCodeAvailable(String moduleCode, Long id)throws Exception;

}
