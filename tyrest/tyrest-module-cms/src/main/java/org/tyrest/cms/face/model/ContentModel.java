package org.tyrest.cms.face.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.tyrest.core.mysql.BaseModel;

/**
 * <pre>
 *
 *  Lexing
 *  File: ContentModel.java
 *
 *  Lexing, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: ContentModel.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-12-15 00:42:52		freeapis		Initial.
 *
 * </pre>
 */
public class ContentModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String avatar;
    private String businessType;
    private String contentTitle;
    private String contentProfile;
    private String contentKeywords;
    private Date createTime;
    private String thumbnail;
    private int viewCount;
    private int favoriteCount;
    private int upvoteCount;
    private int commentCount;
    private int shareCount;
    private String categoryCode;
    private String paid;
    private String postStatus;
    private String locationCode;
    private String locationName;
    private String isTop;
    private Integer topDay;//置顶天数
    private String topType;
    private Date topStartTime;
    private Date topExpireTime;
    private Integer payAmount;
    private String commentable;
    private Long proxyUserId;
    private String rewardType;
    private Integer rewardAmount;
    private String questionStatus;
    private Long bestAnswerId;

    private String contentBody;
    private List<String> images;

    private String categoryName;

    private String refType; //关联操作类型：点赞、分享、收藏。。。。
    private Long correlationUserId;//操作人userId
    private String operateType;



    private Map<String,Object> rewardResult;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getContentTitle() {
        return this.contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentProfile() {
        return this.contentProfile;
    }

    public void setContentProfile(String contentProfile) {
        this.contentProfile = contentProfile;
    }

    public String getContentKeywords() {
        return this.contentKeywords;
    }

    public void setContentKeywords(String contentKeywords) {
        this.contentKeywords = contentKeywords;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getFavoriteCount() {
        return this.favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getUpvoteCount() {
        return this.upvoteCount;
    }

    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public int getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getShareCount() {
        return this.shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Integer getTopDay() {
        return topDay;
    }

    public void setTopDay(Integer topDay) {
        this.topDay = topDay;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    public Date getTopStartTime() {
        return topStartTime;
    }

    public void setTopStartTime(Date topStartTime) {
        this.topStartTime = topStartTime;
    }

    public Date getTopExpireTime() {
        return topExpireTime;
    }

    public void setTopExpireTime(Date topExpireTime) {
        this.topExpireTime = topExpireTime;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public String getCommentable() {
        return commentable;
    }

    public void setCommentable(String commentable) {
        this.commentable = commentable;
    }

    public Long getProxyUserId() {
        return proxyUserId;
    }

    public void setProxyUserId(Long proxyUserId) {
        this.proxyUserId = proxyUserId;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Long getBestAnswerId() {
        return bestAnswerId;
    }

    public void setBestAnswerId(Long bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }


    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public Long getCorrelationUserId() {
        return correlationUserId;
    }

    public void setCorrelationUserId(Long correlationUserId) {
        this.correlationUserId = correlationUserId;
    }


    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public Map<String, Object> getRewardResult() {
        return rewardResult;
    }

    public void setRewardResult(Map<String, Object> rewardResult) {
        this.rewardResult = rewardResult;
    }


}

