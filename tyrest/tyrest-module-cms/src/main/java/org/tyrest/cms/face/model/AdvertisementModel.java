package org.tyrest.cms.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * <pre>
 *
 *  Lexing
 *  File: AdvertisementModel.java
 *
 *  Lexing, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: AdvertisementModel.java 31101200-9 2014-10-14 16:43:51Z Lexing\wuqiang $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public class AdvertisementModel extends BaseModel {
    private static final long serialVersionUID = 4067444542658021637L;

    private String imgUrl;
    private String callUrl;
    private String newWindowTitle;// 当广告作为专题时,是专题的名称
    private String fileName;
    private String dispalyAlias;
    private String enable;
    private Integer adType;
    private Date availableStart;
    private Date availableEnd;
    private String targetType;
    private Long targetSequenceNbr;
    private String contentTitle;// 广告关联的活动或者资讯的名称

    private String contentBody;// 常见问题

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCallUrl() {
        return this.callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    public String getNewWindowTitle() {
        return this.newWindowTitle;
    }

    public void setNewWindowTitle(String newWindowTitle) {
        this.newWindowTitle = newWindowTitle;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDispalyAlias() {
        return this.dispalyAlias;
    }

    public void setDispalyAlias(String dispalyAlias) {
        this.dispalyAlias = dispalyAlias;
    }

    public String getEnable() {
        return this.enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getAdType() {
        return this.adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public Date getAvailableStart() {
        return this.availableStart;
    }

    public void setAvailableStart(Date availableStart) {
        this.availableStart = availableStart;
    }

    public Date getAvailableEnd() {
        return this.availableEnd;
    }

    public void setAvailableEnd(Date availableEnd) {
        this.availableEnd = availableEnd;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getTargetSequenceNbr() {
        return this.targetSequenceNbr;
    }

    public void setTargetSequenceNbr(Long targetSequenceNbr) {
        this.targetSequenceNbr = targetSequenceNbr;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

}
