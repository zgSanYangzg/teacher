package org.tyrest.security.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.SmsVerify;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SendSMSDao.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SendSMSDao.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月13日		yangbochao		Initial.
 *
 * </pre>
 */
public interface SmsVerifyDAO extends GenericDAO<SmsVerify> {
	/**
	 * TODO.根据手机号获取短信记录
	 * @param sessionId
	 * @param mobile
	 * @param verifyingCode
	 * @return
	 * @throws Exception
	 */
	public SmsVerify findByMobile(String sessionId, String mobile, String verifyingCode) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */