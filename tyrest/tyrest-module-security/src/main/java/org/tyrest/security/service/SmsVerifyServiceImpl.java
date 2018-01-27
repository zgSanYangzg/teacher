package org.tyrest.security.service;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.security.face.orm.dao.SmsVerifyDAO;
import org.tyrest.security.face.service.SmsVerifyService;
import org.tyrest.security.face.orm.entity.SmsVerify;
import org.tyrest.security.face.enums.SMSType;
import org.tyrest.security.face.model.SmsVerifyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SmsHistoryServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SmsHistoryServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\framework $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016年07月14日		framework		Initial.
 *
 * </pre>
 */
@Service(value = "smsVerifyService")
public class SmsVerifyServiceImpl extends BaseServiceImpl<SmsVerifyModel, SmsVerify>implements SmsVerifyService {

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Autowired
	private SmsVerifyDAO smsDAO;

	@Override
	public Boolean verifyCode(String sessionId,String mobile,String verifyingCode) throws Exception {
		return !ValidationUtil.isEmpty(smsDAO.findByMobile(sessionId, mobile, verifyingCode));
	}
	
	@Override
	public SmsVerifyModel createVerifyingCode(String phoneNum, SMSType smsType) throws Exception {
		return createSmsVerification(phoneNum, smsType,null);
	}
	
	private SmsVerifyModel createSmsVerification(String mobile, SMSType smsType,Map<String, String> params) throws Exception {
		if (ValidationUtil.isEmpty(smsType)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}

		SmsVerify smsVerify = new SmsVerify();
		smsVerify.setSequenceNBR(sequenceGenerator.getNextValue());
		smsVerify.setSmsId(RequestContext.getSessionId());
		smsVerify.setMobile(mobile);
		smsVerify.setSmsType(smsType.name());
		smsVerify.setSendTime(new Date());
		smsVerify.setSmsVerify(produceIdentifyingCode(CoreConstants.COMMON_4_INT));
		smsVerify.setRecDate(new Date());
		smsVerify.setRecStatus(CoreConstants.COMMON_ACTIVE);
		smsVerify.setRecUserId(CoreConstants.SYSTEM.toString());
		smsDAO.insert(smsVerify);
		return Bean.toModel(smsVerify, new SmsVerifyModel());
	}

	private String produceIdentifyingCode(int length) {
		String radamCode = "";
		while (length > 0) {
			String code = String.valueOf((int) (Math.random() * 10));
			radamCode += code;
			length--;
		}
		return String.valueOf(radamCode);
	}
}
