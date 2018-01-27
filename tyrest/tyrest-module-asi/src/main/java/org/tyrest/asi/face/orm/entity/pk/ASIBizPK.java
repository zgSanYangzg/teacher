package org.tyrest.asi.face.orm.entity.pk;

import java.io.Serializable;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ASIBizPK.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIBizPK.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ASIBizPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String agencyCode;

	private String entityType;

	private String groupCode;

	public ASIBizPK() {
	}

	public ASIBizPK(String groupCode, String entityType, String agencyCode) {
		this.groupCode = groupCode;
		this.entityType = entityType;
		this.agencyCode = agencyCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
}
