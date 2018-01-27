package org.tyrest.security.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.LoginHistory;

import java.util.List;

/**
 * <pre>
 * 
 *  freeapis
 *  File: LoginHistoryDAO.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  LoginHisttorydao接口
 * 
 *  Notes:
 *  $Id: LoginHistoryDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis\baijunyan $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月7日		baijunyan		Initial.
 *
 * </pre>
 */
public interface LoginHistoryDAO extends GenericDAO<LoginHistory> {
	
	public LoginHistory getLastLoginFromHistory(String loginId, String agencyCode, String product) throws Exception;

	/**
	 * TODO.查询指定用户的所有登录记录
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<LoginHistory> findLoginHistorys(Long userId) throws Exception;
	/**
	 * TODO.根据sessionId获取最后一次的登录记录
	 * @param sessionId
	 * @param product
	 * @return
	 * @throws Exception
	 */
	LoginHistory findLastLoginBySessionId(String sessionId, String product) throws Exception;
}
