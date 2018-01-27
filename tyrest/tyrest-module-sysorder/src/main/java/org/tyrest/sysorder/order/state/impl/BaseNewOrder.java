package org.tyrest.sysorder.order.state.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.standard.StandardProduct;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysorder.face.model.OrderInfoModel;
import org.tyrest.sysorder.face.orm.entity.OrderDetail;
import org.tyrest.sysorder.face.orm.entity.OrderInfo;
import org.tyrest.sysorder.face.orm.entity.OrderProductRelation;
import org.tyrest.sysorder.order.BaseOrder;
import org.tyrest.sysorder.order.state.BaseOrderState;
import org.tyrest.sysorder.order.state.OrderStatus;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.trade.BillType;
import org.tyrest.systrade.trade.TradeType;


/**
 * 
 * <pre>
 * 
 *  File: BaseNewOrder.java
 * 
 *  Description:
 * 新提交的订单。还未进行入库,执行操作之后将对订单进行验证（商品，价格，客户信息，商家信息），然后将订单保存到数据库
 * 
 *  Notes:
 *  BaseNewOrder.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */

public abstract class BaseNewOrder extends BaseOrderState
{
	/**
	 * 创建订单信息
	 * @param orderStatus
	 * @return
	 * @throws Exception
	 */
	protected BaseOrder createOrderInfo(OrderStatus orderStatus) throws Exception
	{
		//保存订单基础信息
		BaseOrder baseOrder = this.getOrder();
		OrderInfoModel orderInfoModel = baseOrder.getOrderInfoModel();			
		orderInfoModel.setOrderStatus(orderStatus.toString());
		orderInfoModel.setRecDate(new Date());
		orderInfoModel.setRecStatus(CoreConstants.COMMON_ACTIVE);
		orderInfoModel.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
		OrderInfo orderInfo = Bean.copyExistPropertis(orderInfoModel, new OrderInfo());	
		this.orderInfoDAO.insert(orderInfo);

		//保存订单详细信息		
		OrderDetail orderDetail = (OrderDetail)Bean.copyExistPropertis(orderInfoModel, new OrderDetail());
		orderDetail.setAgencyName(baseOrder.getAgency().getAgencyName());
		orderDetail.setAgencyPhone(baseOrder.getAgency().getAgencyPhone());
		orderDetail.setCustomerType(baseOrder.getCustomer().getCustomerType());
		orderDetail.setUserName(baseOrder.getCustomer().getUserName());	
		orderDetail.setIsComment(CoreConstants.COMMON_N);
		orderDetail.setRecDate(new Date());
		orderDetail.setRecStatus(CoreConstants.COMMON_ACTIVE);
		orderDetail.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
		orderDetailDAO.insert(orderDetail);
		return baseOrder;
	}

	/**
	 * 创建商品订单关系
	 * @return
	 * @throws Exception
	 */
	protected List<OrderProductRelation> createOrderProductRelation() throws Exception
	{
		List<StandardProduct> products = this.getOrder().getProducts();
		List<OrderProductRelation> newEntities = null;
		if(!ValidationUtil.isEmpty(products))
		{
			newEntities = new ArrayList<OrderProductRelation>();
			for(StandardProduct product : products){
				OrderProductRelation entity = new OrderProductRelation();
				entity.setCount(product.getCount());
				entity.setProductType(product.getProductType());
				entity.setIsComment(CoreConstants.COMMON_N);
				entity.setOrderSn(this.getOrder().getOrderSn());
				entity.setProductName(product.getProductName());
				entity.setProductPrice(product.getPrice());
				entity.setSequenceNBR(sequenceGenerator.getNextValue());
				entity.setRecDate(new Date());
				entity.setRecStatus(CoreConstants.COMMON_ACTIVE);
				entity.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
				this.orderProductRelationDAO.insert(entity);
				newEntities.add(entity);
			}
		}
		return newEntities;
	}
	
	
	/**
	 *	创建账单
	 *
	 * @return
	 * @throws Exception 
	 */
	protected TransactionsBillModel createBill(BillType billType) throws Exception
	{

		TransactionsBill transactionsBill = this.transactionsBillDAO.createBill( this.getOrder().getAgency().getAgencyCode(),  this.getOrder().getOrderInfoModel().getBillNo(),
				this.sequenceGenerator.getNextValue(),this.getOrder().getOrderInfoModel().getAmount(),  billType,  this.getOrder().getOrderInfoModel().getUserId());
		return Bean.copyExistPropertis(transactionsBill, new TransactionsBillModel());
	}
	
}
