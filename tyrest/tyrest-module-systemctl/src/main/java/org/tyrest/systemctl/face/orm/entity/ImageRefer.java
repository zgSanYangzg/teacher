package org.tyrest.systemctl.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: ImageRefer.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ImageRefer.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-09 11:30:15		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_image_refer")
public class ImageRefer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String entityType;
	private Long entityId;
	private String imageRefer;

	public ImageRefer(){}
	
	public ImageRefer(Long id,String imageRefer,Integer order){
		this.sequenceNBR = id;
		this.imageRefer = imageRefer;
		this.extend1 = order.toString();
	}
	
	@Column(name = "ENTITY_TYPE", nullable = false)
	public String getEntityType() {
		return this.entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	@Column(name = "ENTITY_ID", nullable = false)
	public Long getEntityId() {
		return this.entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	@Column(name = "IMAGE_REFER")
	public String getImageRefer() {
		return this.imageRefer;
	}
	public void setImageRefer(String imageRefer) {
		this.imageRefer = imageRefer;
	}
	
}

