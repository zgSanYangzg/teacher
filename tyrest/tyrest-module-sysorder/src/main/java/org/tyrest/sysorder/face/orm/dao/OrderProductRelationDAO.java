package org.tyrest.sysorder.face.orm.dao;

import java.util.List;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysorder.face.orm.entity.OrderProductRelation;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderProductRelationDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderProductRelationDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface OrderProductRelationDAO extends GenericDAO<OrderProductRelation>
{
	/**
	 * 根据订单编号查询订单商品详细信息
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	List<OrderProductRelation> findByOrderSn(String orderSn) throws Exception;

}
