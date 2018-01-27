package org.tyrest.publicuser.message;

/**
 * Created by Administrator on 2016/12/23.
 */
public enum  PushMessageType {

    PRIVATE_MESSAGE_RECEIVED("","收到私信"),

    POST_TO_AUDIT("","新的待审核帖子"),

    QUESTIONS_BE_ANSWERD("","有新的回答"),

    CASHOUT_BE_AUDITED("","提现申请被处理");

    private  String templateId;
    private String messageTypeName;

    PushMessageType(String templateId,String messageTypeName)
    {
        this.templateId      = templateId;
        this.messageTypeName = messageTypeName;
    }

    public String  getTemplateId() {
        return templateId;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }
}
