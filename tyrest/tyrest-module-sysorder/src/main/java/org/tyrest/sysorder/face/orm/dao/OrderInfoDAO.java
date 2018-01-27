package org.tyrest.sysorder.face.orm.dao;

import java.util.List;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysorder.face.orm.entity.OrderInfo;


/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderInfoDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderInfoDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface OrderInfoDAO extends GenericDAO<OrderInfo> {

	/**
	 * 根据订单编号查询订单
	 * @param agencyCode
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public OrderInfo findByOrderSn(String agencyCode, String orderSn) throws Exception;
	
	/**
	 * 根据账单编号查询订单
	 * @param agencyCode
	 * @param billNo
	 * @return
	 * @throws Exception
	 */
	public OrderInfo findByBillNo(String agencyCode, String billNo) throws Exception;
	
	
	
	
	
	/**
	 * 查询订单列表
	 * @param billNo	
	 * @param orderTypes
	 * @param orderStatus
	 * @param payMethod
	 * @param payStatus
	 * @param userId
	 * @param agencyCode
	 * @param source
	 * @return
	 */
	public List<OrderInfo> findForList(String billNo,String [] orderTypes,String []orderStatus,String payMethod,String payStatus,Long userId,String agencyCode,String source)throws Exception;
	
	
}
