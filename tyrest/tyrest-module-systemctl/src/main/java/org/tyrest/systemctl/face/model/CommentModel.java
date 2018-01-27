package org.tyrest.systemctl.face.model;

import org.tyrest.core.mysql.BaseModel;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: CommentModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CommentModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class CommentModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String targetType;
	private Long targetSequenceNbr;
	private String commentBody;
	private String img1;
	private String img2;
	private String img3;
	private String hasImage;
	private String isAudit;
	private String auditStatus;
	private Double starScore;

	//评论人的信息
	private String avatar;
	private String nickName;

	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	
	public String getCommentBody() {
		return this.commentBody;
	}
	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}
	
	public String getImg1() {
		return this.img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	
	public String getImg2() {
		return this.img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	
	public String getImg3() {
		return this.img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	
	public String getHasImage() {
		return this.hasImage;
	}
	public void setHasImage(String hasImage) {
		this.hasImage = hasImage;
	}
	
	public String getIsAudit() {
		return this.isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	
	public String getAuditStatus() {
		return this.auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public Double getStarScore() {
		return this.starScore;
	}
	public void setStarScore(Double starScore) {
		this.starScore = starScore;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}

