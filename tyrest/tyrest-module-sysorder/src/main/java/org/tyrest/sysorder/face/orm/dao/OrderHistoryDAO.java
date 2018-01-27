package org.tyrest.sysorder.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysorder.face.orm.entity.OrderHistory;


/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderHistoryDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderHistoryDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface OrderHistoryDAO extends GenericDAO<OrderHistory> {
	/**
	 * 根据订单编号查询订单
	 * @param agencyCode
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public OrderHistory findByOrderSn(String agencyCode, String orderSn) throws Exception;
	
}
