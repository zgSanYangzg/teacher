package org.tyrest.sysorder.order;

import java.util.List;
import java.util.Map;

import org.tyrest.core.foundation.standard.BaseAgency;
import org.tyrest.core.foundation.standard.Customer;
import org.tyrest.core.foundation.standard.StandardProduct;
import org.tyrest.sysorder.face.model.OrderInfoModel;
import org.tyrest.sysorder.order.state.OrderStatus;
/**
 * 
 * <pre>
 * 
 *  File: BaseOrder.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  BaseOrder.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
public  class BaseOrder{
	
	/**
	 * 订单编号（全局唯一）
	 */
	protected String orderSn;
	
	/**
	 * 订单状态
	 */
	protected OrderStatus orderStatus;
	
	/**
	 * 客户信息
	 */
	protected Customer customer;
	
	/**
	 * 商品信息
	 */
	protected List<StandardProduct> products;
	
	/**
	 * 商家信息
	 */
	protected BaseAgency agency;
	
	/**
	 * 订单实体
	 */
	protected OrderInfoModel orderInfoModel;

	/**
	 * 订单交易信息
	 */
	protected Map<String,Object> tradeParams;
	
	public String getOrderSn() {
		return orderSn;
	}


	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<StandardProduct> getProducts() {
		return products;
	}


	public void setProducts(List<StandardProduct> products) {
		this.products = products;
	}


	public BaseAgency getAgency() {
		return agency;
	}


	public void setAgency(BaseAgency agency) {
		this.agency = agency;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	public OrderInfoModel getOrderInfoModel(){
		return orderInfoModel;
	}


	public void setOrderInfoModel(OrderInfoModel orderInfoModel)
	{
		this.orderInfoModel = orderInfoModel;
	}


	public Map<String, Object> getTradeParams(){
		return tradeParams;
	}


	public void setTradeParams(Map<String, Object> tradeParams){
		this.tradeParams = tradeParams;
	}
}
