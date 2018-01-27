package org.tyrest.resource.human.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.restevent.RestEvent;
import org.tyrest.core.restevent.RestEventHandler;
import org.tyrest.human.face.model.SocialUserModel;
import org.tyrest.human.face.orm.entity.SocialUser;
import org.tyrest.notification.face.model.EmailPushModel;
import org.tyrest.notification.producer.Notifier;
import org.tyrest.notification.typedef.EmailPush;

/**
 * Created by adm on 2017/1/13.
 */
@Component(value = "saveSocialUserEventHandler")
public class SaveSocialUserEventHandler extends RestEventHandler{

    @Autowired
    Notifier notifier;

    /**
     * TODO.用于处理系统的业务事件
     *
     * @param restEvent
     * @throws Exception
     */
    protected  void handleEvent(RestEvent restEvent) throws Exception
    {
        //拿到信息后，然后在handler 处理业务
            SocialUserModel so=( SocialUserModel)restEvent.getSource();
            SocialUser socialUser= Bean.copyExistPropertis(so,new SocialUser());

            EmailPushModel emailPushModel = new EmailPushModel();
            emailPushModel.setSubject("保存人员成功邮件");
            emailPushModel.setContent("已经保存成功,请查收邮件！");
            emailPushModel.setTo("2582565296@qq.com");
            emailPushModel.setFrom("522853371@qq.com");
            emailPushModel.setFromName("吴柱");

            EmailPush emailPush = new EmailPush(emailPushModel);
            notifier.notify(emailPush,"email");

    };
}
