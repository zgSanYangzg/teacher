package org.tyrest.sysorder.order.rule;

import org.tyrest.sysorder.order.BaseOrder;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OperationLimitHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OperationLimitHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface OperationLimitHandler{

	/**
	 * 
	 * 通用操作限制
	 *
	 * @param order
	 * @return
	 * @throws Exception
	 */
	boolean checkOperation(BaseOrder order) throws Exception;
}
