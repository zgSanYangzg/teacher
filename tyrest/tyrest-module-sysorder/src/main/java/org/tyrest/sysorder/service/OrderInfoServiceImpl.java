package org.tyrest.sysorder.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.agency.face.model.AgencyModel;
import org.tyrest.agency.face.orm.dao.AgencyDAO;
import org.tyrest.agency.face.orm.entity.Agency;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.standard.StandardProduct;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.snapshot.face.orm.dao.SnapshotDAO;
import org.tyrest.sysorder.face.model.OrderInfoModel;
import org.tyrest.sysorder.face.orm.entity.OrderInfo;
import org.tyrest.sysorder.face.service.OrderInfoService;
import org.tyrest.sysorder.order.BaseOrder;
import org.tyrest.sysorder.order.state.OrderStatus;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderInfoServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderInfoServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value = "orderInfoService")
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfoModel, OrderInfo>
		implements OrderInfoService
{
	
	@Autowired
	AgencyDAO agencyDAO;
	
	
	@Autowired
	SnapshotDAO snapshotDAO;
	
	/**
	 * 
	 * 封装订单处理对象
	 *
	 * @param orderModel
	 * @param publicUserId
	 * @return
	 * @throws Exception
	 */

	protected  BaseOrder fillingBaseOrder(OrderInfoModel orderModel, Long publicUserId, String productCode,
			OrderStatus orderStatus) throws Exception
	{

		BaseOrder baseOrder = new BaseOrder();
		// #1.订单信息
		baseOrder.setOrderInfoModel(orderModel);
		baseOrder.setOrderSn(orderModel.getOrderSn());
		baseOrder.setOrderStatus(orderStatus);

		// #2.商品信息
		baseOrder.setProducts(getDefaultProducts(orderModel, productCode));

		// #3.公网用户信息
		
		// #4.商家信息
		Agency agency = this.agencyDAO.findByAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
		AgencyModel agencyModel = (AgencyModel) Bean.copyExistPropertis(agency, new AgencyModel());
		baseOrder.setAgency(agencyModel);
		return baseOrder;
	}
	
	

	/**
	 * 
	 * 商品信息填充
	 *
	 * @return
	 * @throws Exception
	 */
	private List<StandardProduct> getDefaultProducts(OrderInfoModel orderModel, String productCode) throws Exception
	{
		List<StandardProduct> products = new ArrayList<StandardProduct>();// 商品列表

		
		return products;
	}

	
	

	@Override
	public OrderInfoModel queryByCode(String agencyCode, String orderSn) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public OrderInfoModel queryByBillNO(String agencyCode, String billNo) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}



}

/*
 * $Log: av-env.bat,v $
 */