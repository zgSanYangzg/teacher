package org.tyrest.cms.face.enums;

/**
 * Created by wuqiang on 2016/12/15.
 * 内容类型，微帮世界的内容类型
 */
public enum ContentType {
    /**
     * 广告帖
     */
    ad,
    /**
     * 普通帖
     */
    commonPost,
    /**
     * 视频帖
     */
    videoPost,
    /**
     * 问题
     */
    question,

    /**
     * 反馈信息
     */
    feedback;

    public static ContentType getByName(String name){
        ContentType result = null;
        for(ContentType contentType : ContentType.values()){
            if(contentType.name().equals(name)){
                result = contentType;
                break;
            }
        }
        return result;
    }
}