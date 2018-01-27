package org.tyrest.systemctl.face.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Comment.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Comment.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_comment")
public class Comment extends BaseEntity
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

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "TARGET_TYPE", nullable = false)
	public String getTargetType() {
		return this.targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
	@Column(name = "TARGET_SEQUENCE_NBR", nullable = false)
	public Long getTargetSequenceNbr() {
		return this.targetSequenceNbr;
	}
	public void setTargetSequenceNbr(Long targetSequenceNbr) {
		this.targetSequenceNbr = targetSequenceNbr;
	}
	
	@Column(name = "COMMENT_BODY")
	public String getCommentBody() {
		return this.commentBody;
	}
	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}
	
	@Column(name = "IMG1")
	public String getImg1() {
		return this.img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	
	@Column(name = "IMG2")
	public String getImg2() {
		return this.img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	
	@Column(name = "IMG3")
	public String getImg3() {
		return this.img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	
	@Column(name = "HAS_IMAGE")
	public String getHasImage() {
		return this.hasImage;
	}
	public void setHasImage(String hasImage) {
		this.hasImage = hasImage;
	}
	
	@Column(name = "IS_AUDIT")
	public String getIsAudit() {
		return this.isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	
	@Column(name = "AUDIT_STATUS")
	public String getAuditStatus() {
		return this.auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Column(name = "STAR_SCORE")
	public Double getStarScore() {
		return this.starScore;
	}
	public void setStarScore(Double starScore) {
		this.starScore = starScore;
	}
	
}

