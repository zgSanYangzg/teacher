package org.tyrest.security.service;

import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.security.face.orm.dao.LoginHistoryDAO;
import org.tyrest.security.face.service.LoginHistoryService;
import org.tyrest.security.face.orm.entity.LoginHistory;
import org.tyrest.security.face.model.LoginHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 *  freeapis
 *  File: LoginHistoryServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  登陆历史业务层处理
 * 
 *  Notes:
 *  $Id: LoginHistoryServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\baijunyan $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月7日		baijunyan		Initial.
 *
 * </pre>
 */
@Service(value = "loginHistoryService")
public class LoginHistoryServiceImpl extends BaseServiceImpl<LoginHistoryModel,LoginHistory> implements LoginHistoryService
{
	@Autowired
	private LoginHistoryDAO loginHistoryDAO;
	
	public LoginHistoryModel getLastLoginFromHistory(String loginId, String agencyCode,String product) throws Exception
	{
		return Bean.toModel(loginHistoryDAO.getLastLoginFromHistory(loginId, agencyCode,product), new LoginHistoryModel());
	}

	@Override
	public void createLoginHistory(LoginHistoryModel loginHistoryModel) throws Exception {
		loginHistoryDAO.insert(Bean.toPo(loginHistoryModel, new LoginHistory()));
	}

}

/*
*$Log: av-env.bat,v $
*/