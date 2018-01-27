package org.tyrest.security.face.service;

import org.tyrest.core.mysql.BaseService;
import org.tyrest.security.face.enums.SMSType;
import org.tyrest.security.face.model.SmsVerifyModel;
import org.tyrest.security.face.orm.entity.SmsVerify;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SmsHistoryService.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SmsHistoryService.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月13日		yangbochao		Initial.
 *
 * </pre>
 */
public interface SmsVerifyService extends BaseService<SmsVerifyModel, SmsVerify> {

	/**
	 * TODO.验证短信验证码
	 * @param sessionId
	 * @param mobile
	 * @param verifyingCode
	 * @return
	 * @throws Exception
	 */
	 Boolean verifyCode(String sessionId, String mobile, String verifyingCode) throws Exception;

	 /**
	  * TODO.获取短信验证码专用方法
	  * @param mobile
	  * @param smsType
	  * @return-
	  * @throws Exception
	  */
	 SmsVerifyModel createVerifyingCode(String mobile, SMSType smsType) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */