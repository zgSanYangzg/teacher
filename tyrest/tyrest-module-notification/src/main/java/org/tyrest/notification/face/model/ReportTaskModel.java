package org.tyrest.notification.face.model;

import java.io.Serializable;
import java.util.Map;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ReportTaskModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ReportTaskModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ReportTaskModel implements Serializable{
	
	private static final long serialVersionUID = -1906081722483603662L;
	
	private String reportName;//报表名称
	private DataUpdateWay dataUpdateWay;//报表数据更新方式
	Map<String,Object> queryParams;//报表查询参数
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public DataUpdateWay getDataUpdateWay() {
		return dataUpdateWay;
	}
	public void setDataUpdateWay(DataUpdateWay dataUpdateWay) {
		this.dataUpdateWay = dataUpdateWay;
	}
	public Map<String, Object> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
	
	//报表数据的更新方式
	public enum DataUpdateWay{
		/**
		 *刷新报表,这种方式会刷新整个报表 
		 */
		refresh,
		/**
		 * 增量更新,这种方式根据参数查询出报表的增量数据进行更新,要求参数不能为空
		 */
		increment;
	}
}

/*
*$Log: av-env.bat,v $
*/