package org.tyrest.security.dao;

import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.security.face.orm.dao.SmsVerifyDAO;
import org.tyrest.security.face.orm.entity.SmsVerify;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SmsHistoryDaoImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SmsHistoryDaoImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Repository(value="smsHistoryDAO")
public class SmsVerifyDAOImpl extends GenericDAOImpl<SmsVerify> implements SmsVerifyDAO
{
	@Override
	public SmsVerify findByMobile(String sessionId, String mobile, String verifyingCode) throws Exception {
		StringBuilder sqlSufix = new StringBuilder(" AND MOBILE = :MOBILE ")
				.append(" AND SMS_ID = :SMS_ID ").append(" AND SMS_VERIFY = :SMS_VERIFY ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MOBILE", mobile);
		params.put("SMS_ID", sessionId);
		params.put("SMS_VERIFY", verifyingCode);
		return this.findFirst(sqlSufix.toString(), params);
	}
}
