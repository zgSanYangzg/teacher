package org.tyrest.notification.typedef;

import org.tyrest.notification.face.model.EmailPushModel;
import org.tyrest.notification.face.typedef.AbstractNotification;

/**
 * Created by ttsw on 2017/1/13.
 */
public class EmailPush extends AbstractNotification {


    public EmailPush(EmailPushModel body) {
        super(body);
    }

    @Override
    public boolean isTopic() {
        return false;
    }//是否订阅

    @Override
    public boolean needPersist() {
        return false;
    }//是否持久化

    @Override
    public int retryTimes() {
        return 3;
    }//重试次数

}
