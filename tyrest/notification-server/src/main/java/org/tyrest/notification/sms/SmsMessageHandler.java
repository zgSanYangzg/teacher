package org.tyrest.notification.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.exceptions.ResourceForbiddenException;
import org.tyrest.core.foundation.utils.PropertyUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.notification.consumer.JMSMessageHandler;
import org.tyrest.notification.face.model.SmsSendModel;
import org.tyrest.notification.face.typedef.Notification;
import org.tyrest.opendata.sms.Sms;
import org.tyrest.systemctl.face.constants.DictionaryConstants;
import org.tyrest.systemctl.face.service.DictionaryService;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  freeapis
 *  File: SmsMessageHandler.java
 *
 *  freeapis, Inc.
 *  Copyright (C): 2016
 *
 *  Description:短信消息处理器，负责调用互易无限发送短信
 *  TODO
 *
 *  Notes:
 * 	$Id: SmsMessageHandler.java 72642 2009-01-01 20:01:57Z freeapis\gaoyuanteng $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年3月18日		gaoyuanteng		Initial.
 *
 * </pre>
 */
@Component(value = "SmsHandler")
public class SmsMessageHandler extends JMSMessageHandler {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    protected void handleMessage(Notification notification) throws Exception {
        SmsSendModel smsModel = (SmsSendModel) notification.body();
        List<String> mobiles = smsModel.getMobiles();
        Sms sms = Sms.use("alidayuSms");
        if (!ValidationUtil.isEmpty(smsModel) && !ValidationUtil.isEmpty(mobiles)) {
            if (!validateSmsProductionModel(mobiles)) throw new ResourceForbiddenException("当前是测试环境,请配置测试账号列表后再发短信!");
            sms.sendSms(smsModel.getTemplateCode(), smsModel.getParams(), mobiles);
        }
    }

    // 校验短信发送模式,如果是测试环境,判断当前要发送的手机号是否在测试列表中,如果不在测试号列表,则不发送短信
    protected boolean validateSmsProductionModel(List<String> mobiles) throws Exception {
        boolean canSend = true;
        boolean isProductionMode = CoreConstants.COMMON_Y.equals(PropertyUtil.get("PRODUCTION_MODE"));
        if (!isProductionMode) {
            Map<String, String> testAccounts = dictionaryService.getEntryMap(CoreConstants.CODE_SUPER_ADMIN, DictionaryConstants.DICT_CODE_TEST_MOBILES);
            if (ValidationUtil.isEmpty(testAccounts)) {
                canSend = false;
            } else {
                for (String mobile : mobiles) {
                    if (!testAccounts.containsValue(mobile)) {
                        canSend = false;
                        break;
                    }
                }
            }
        }
        return canSend;
    }
}

/*
 * $Log: av-env.bat,v $
 */