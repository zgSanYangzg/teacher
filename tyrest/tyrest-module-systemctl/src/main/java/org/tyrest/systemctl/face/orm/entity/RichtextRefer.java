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
 *  File: RichtextRefer.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RichtextRefer.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-09 11:30:16		freeapis		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "systemctl_richtext_refer")
public class RichtextRefer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String entityType;
	private Long entityId;
	private String richtextType;
	private Long richtextRefer;

	public RichtextRefer(){}
	
	public RichtextRefer(Long id, String entityType, Long entityId, String richtextType,Long richtextRefer) {
		this.sequenceNBR = id;
		this.entityType = entityType;
		this.entityId = entityId;
		this.richtextType = richtextType;
		this.richtextRefer = richtextRefer;
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
	
	@Column(name = "RICHTEXT_REFER", nullable = false)
	public Long getRichtextRefer() {
		return this.richtextRefer;
	}
	public void setRichtextRefer(Long richtextRefer) {
		this.richtextRefer = richtextRefer;
	}

	@Column(name = "RICHTEXT_TYPE", nullable = false)
	public String getRichtextType() {
		return richtextType;
	}

	public void setRichtextType(String richtextType) {
		this.richtextType = richtextType;
	}
}

