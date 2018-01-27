package org.tyrest.opendata.emails;

import org.tyrest.core.foundation.support.SpringContextHelper;

/**
 * Created by ttsw on 2017/1/13.
 */
public abstract class EmailSender {


    public static EmailSender use(String instanceName){
        return SpringContextHelper.getBean(instanceName);
    }


    /**
     * 给一个email发送邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     * @throws Exception
     */
    public  abstract void sendEmail(String fromName,String from,String to,String subject,String content) throws Exception;

}
