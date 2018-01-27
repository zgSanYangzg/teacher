package org.tyrest.cms.face.orm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;

/**
 * <pre>
 *
 *  Lexing
 *  File: Content.java
 *
 *  Lexing, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: Content.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-12-15 00:42:52		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "wbsj_content")
public class Content extends BaseEntity {
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
    private String contentStatus;
    private Integer viewCount;
    private Integer favoriteCount;
    private Integer upvoteCount;
    private Integer commentCount;
    private Integer shareCount;
    private String categoryCode;
    private String paid;
    private String locationCode;
    private String locationName;
    private String isTop;
    private String topType;
    private Date topStartTime;
    private Date topExpireTime;
    private Integer payAmount;
    private String commentable;
    private Long proxyUserId;
    private String rewardType;
    private Integer rewardAmount;
    private Long bestAnswerId;

    @Column(name = "USER_ID", nullable = false)
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "USER_NAME", nullable = false)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "AVATAR")
    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "BUSINESS_TYPE", nullable = false)
    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Column(name = "CONTENT_TITLE")
    public String getContentTitle() {
        return this.contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    @Column(name = "CONTENT_PROFILE")
    public String getContentProfile() {
        return this.contentProfile;
    }

    public void setContentProfile(String contentProfile) {
        this.contentProfile = contentProfile;
    }

    @Column(name = "CONTENT_KEYWORDS")
    public String getContentKeywords() {
        return this.contentKeywords;
    }

    public void setContentKeywords(String contentKeywords) {
        this.contentKeywords = contentKeywords;
    }

    @Column(name = "CREATE_TIME", nullable = false)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "THUMBNAIL")
    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    @Column(name = "VIEW_COUNT")
    public Integer getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Column(name = "FAVORITE_COUNT")
    public Integer getFavoriteCount() {
        return this.favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @Column(name = "UPVOTE_COUNT")
    public Integer getUpvoteCount() {
        return this.upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    @Column(name = "COMMENT_COUNT")
    public Integer getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Column(name = "SHARE_COUNT")
    public Integer getShareCount() {
        return this.shareCount;
    }

    public void setShareCount(Integer shareCount) {
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

    public Long getBestAnswerId() {
        return bestAnswerId;
    }

    public void setBestAnswerId(Long bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }
}

