package org.tyrest.security.face.service;


import org.tyrest.core.mysql.BaseService;
import org.tyrest.security.face.model.LoginHistoryModel;
import org.tyrest.security.face.orm.entity.LoginHistory;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: LoginHistoryService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LoginHistoryService.java 31101200-9 2014-10-14 16:43:51Z freeapis\baijunyan $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年7月31日		baijunyan		Initial.
 *
 * </pre>
 */
public interface LoginHistoryService extends BaseService<LoginHistoryModel,LoginHistory>
{
	void createLoginHistory(LoginHistoryModel loginHistoryModel) throws Exception;
	
	public LoginHistoryModel getLastLoginFromHistory(String loginId, String agencyCode, String product) throws Exception;
}
