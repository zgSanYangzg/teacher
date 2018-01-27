package org.tyrest.sysorder.face.service;

import org.tyrest.core.mysql.BaseService;
import org.tyrest.sysorder.face.model.OrderInfoModel;
import org.tyrest.sysorder.face.orm.entity.OrderInfo;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderInfoService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderInfoService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface OrderInfoService extends BaseService<OrderInfoModel, OrderInfo> {



	/**
	 * 
	 * 查询单个订单信息
	 *
	 * @param agencyCode
	 * @param orderSn
	 * @return
	 * @throws Exception
	 */
	public OrderInfoModel queryByCode(String agencyCode, String orderSn) throws Exception;

	

	

	/**
	 * 根据账单编号查询单个订单信息
	 * 
	 * @param agencyCode
	 * @param billNo
	 * @return
	 * @throws Exception
	 */
	public OrderInfoModel queryByBillNO(String agencyCode, String billNo) throws Exception;


	
	
}

/*
 * $Log: av-env.bat,v $
 */
