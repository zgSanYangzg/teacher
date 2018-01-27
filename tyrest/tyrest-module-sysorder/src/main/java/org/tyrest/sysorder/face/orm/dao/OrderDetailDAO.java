package org.tyrest.sysorder.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysorder.face.orm.entity.OrderDetail;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderDetailDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderDetailDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface OrderDetailDAO extends GenericDAO<OrderDetail>
{

	/**
	 * 根据orderSn查询订单
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public OrderDetail findByOrderSn( String orderSn) throws Exception;

}
