package org.tyrest.asi.face.orm.entity.pk;

import java.io.Serializable;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: GroupColumnPK.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupColumnPK.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class GroupColumnPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String agencyCode;

	private String groupCode;

	private String columnCode;

	public GroupColumnPK() {
	}

	public GroupColumnPK(String groupCode, String agencyCode, String columnCode) {
		this.groupCode = groupCode;
		this.agencyCode = agencyCode;
		this.columnCode = columnCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}
}
