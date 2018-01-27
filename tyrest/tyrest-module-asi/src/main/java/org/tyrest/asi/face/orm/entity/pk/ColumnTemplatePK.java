package org.tyrest.asi.face.orm.entity.pk;

import java.io.Serializable;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ColumnTemplatePK.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ColumnTemplatePK.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ColumnTemplatePK implements Serializable {
	private static final long serialVersionUID = 869807748949837944L;

	private String agencyCode;

	private String columnCode;

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
}
