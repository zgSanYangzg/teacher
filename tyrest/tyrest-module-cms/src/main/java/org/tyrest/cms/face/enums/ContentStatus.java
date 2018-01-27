package org.tyrest.cms.face.enums;

/**
 * Created by wuqiang on 2016/12/15.
 * 内容状态，包括帖子状态，问题状态
 */
public enum ContentStatus {

    normal("正常"),
    shield("屏蔽"),
    noreply("禁止回复"),
    waitApprove("待审核"),
    waitPay("待支付"),
    published("已发布"),
    refused("被拒绝");

    private String label;
    private ContentStatus(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

    public static ContentStatus getByName(String name){
        ContentStatus result = null;
        for(ContentStatus contentType : ContentStatus.values()){
            if(contentType.name().equals(name)){
                result = contentType;
                break;
            }
        }
        return result;
    }
}