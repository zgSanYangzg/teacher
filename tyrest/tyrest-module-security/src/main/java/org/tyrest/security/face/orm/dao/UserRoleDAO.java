package org.tyrest.security.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: AgUserRoleDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AgUserRoleDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
public interface UserRoleDAO extends GenericDAO<UserRole> {
	
	/**
	 * 查询用户角色信息
	 * @param agencyCode
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<UserRole> findUserRoles(String agencyCode, Long userId) throws Exception;
	
	/**
	 * 删除用户角色信息
	 * @param agencyCode
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void deleteUserRoles(String agencyCode, Long userId) throws Exception;

	/**
	 * 根据锁定状态获取用户角色信息
	 * @param agencyCode
	 * @param userId
	 * @param lockStatus 为空表示也获取锁定的角色
	 * @return
	 * @throws Exception
	 * @throws Exception 
	 */
	public List<UserRole> findByLockStatus(String agencyCode, Long userId, String lockStatus)throws Exception, Exception;

	/**
	 * 查找用户角色详细信息
	 * @param agencyCode
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findUserRolesInfo(String agencyCode, Long userId) throws Exception;

}

/*
 * $Log: av-env.bat,v $
 */