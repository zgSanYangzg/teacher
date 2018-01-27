package org.tyrest.core.foundation.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: Page.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Page.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class Page implements java.io.Serializable {

	private static final long serialVersionUID = -6734719918605413443L;

	private int currentPage = 1;// 当前页:Action控制

	private int pageRecorders = 10; // 每页记录数,默认为10,可以在初始化的时候修改//总数据数

	private int pageStartRow = 0; // 每页的起始数 [这个字段可以去掉]

	private int pageEndRow = 0; // 每页显示数据的终止数 [这个字段也可以去掉]

	private int totalRows;// 总记录数,由底层service提供

	private List<?> list = null;

	public Page(int pageRecorders, int pageStartRow) {
		this.pageRecorders = pageRecorders;
		this.pageStartRow = pageStartRow;
	}

	public int getNextPageIndex() {
		if (this.getCurrentPage() == this.getTotalPages()) {
			return 1;
		} else {
			return this.getCurrentPage() + 1;
		}

	}

	public int getPreviousPageIndex() {
		if (this.getCurrentPage() == 1) {
			return this.getTotalPages();
		} else {
			return this.getCurrentPage() - 1;
		}

	}

	// 是否有上一页
	public boolean isHasPreviousPage() {
		return (this.getCurrentPage() > 1 ? true : false);
	}

	// 共有多少页,service只提供有多少条记录,多少页数由PageBean自己运算
	public int getTotalPages() {
		if (totalRows <= pageRecorders) {
			return 1;
		}
		return (totalRows % pageRecorders == 0 ? totalRows / pageRecorders : totalRows / pageRecorders + 1);
	}

	public int getCurrentPage() {
		if (this.getPageStartRow() > 0) {
			double i = this.getPageStartRow() / this.getPageRecorders();
			int t = new Double(i).intValue();
			if (i < 1) {
				return currentPage = 1;
			}
			if (i >= 1) {
				return currentPage = t + 1;
			}

		}

		if (currentPage <= 1) {
			return 1;
		} else if (currentPage >= this.getTotalPages()) {
			return this.getTotalPages();
		} else {
			return currentPage;
		}

	}

	public int getPageEndRow() {

		return pageEndRow;
	}

	// 是否有下一页
	public boolean isHasNextPage() {
		return (this.getCurrentPage() < this.getTotalPages() ? true : false);
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getPageStartRow() {
		return this.pageStartRow;
	}

	public int getPageRecorders() {
		return pageRecorders;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public void setPageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@SuppressWarnings("static-access")
	@Override
	public int hashCode() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("currentPage");
		list.add("totalPages");
		list.add("pageRecorders");
		list.add("pageStartRow");
		list.add("pageEndRow");
		list.add("hasNextPage");
		list.add("hasPreviousPage");
		list.add("result");
		list.add("totalRows");
		return new HashCodeBuilder(5, 57).reflectionHashCode(this, list);
	}

}

/*
 * $Log: av-env.bat,v $
 */