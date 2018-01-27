package org.tyrest.core.mysql;

import java.io.Serializable;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ReferenceModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:删除时检查引用关系的Model
 *  TODO
 * 
 *  Notes:
 *  $Id: ReferenceModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ReferenceModel implements Serializable {

	private static final long serialVersionUID = -634926227077187944L;
	private String table;
	private String[] fkColumns;
	private String[] fkValues;
	private String description;

	/**
	 * ReferenceModel Constructor.
	 * 
	 * @param table
	 *            表名
	 * @param fkColumns
	 *            外键列名
	 * @param description
	 *            描述
	 */
	public ReferenceModel(String table, String[] fkColumns, String[] fkValues, String description) {
		super();
		this.table = table;
		this.fkColumns = fkColumns;
		this.fkValues = fkValues;
		this.description = description;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String[] getFkColumns() {
		return fkColumns;
	}

	public void setFkColumns(String[] fkColumns) {
		this.fkColumns = fkColumns;
	}

	public String[] getFkValues() {
		return fkValues;
	}

	public void setFkValues(String[] fkValues) {
		this.fkValues = fkValues;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

/*
 * $Log: av-env.bat,v $
 */