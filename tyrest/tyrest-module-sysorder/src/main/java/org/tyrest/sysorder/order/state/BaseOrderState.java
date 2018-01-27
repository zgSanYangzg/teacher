package org.tyrest.sysorder.order.state;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysorder.face.model.OrderInfoModel;
import org.tyrest.sysorder.face.orm.dao.OrderDetailDAO;
import org.tyrest.sysorder.face.orm.dao.OrderHistoryDAO;
import org.tyrest.sysorder.face.orm.dao.OrderInfoDAO;
import org.tyrest.sysorder.face.orm.dao.OrderProductRelationDAO;
import org.tyrest.sysorder.face.orm.entity.OrderHistory;
import org.tyrest.sysorder.order.BaseOrder;
import org.tyrest.systrade.face.orm.dao.TransactionsBillDAO;

/**
 * 
 * <pre>
 * 
 *  File: BaseOrderState.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  BaseOrderState.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
public abstract class BaseOrderState  {

	@Autowired
	protected OrderProductRelationDAO orderProductRelationDAO;
	
	@Autowired
	protected SequenceGenerator sequenceGenerator;
	
	@Autowired
	protected OrderInfoDAO orderInfoDAO;
	
	@Autowired
	protected OrderDetailDAO orderDetailDAO;
	
	@Autowired
	protected TransactionsBillDAO transactionsBillDAO;
	
	@Autowired
	protected OrderHistoryDAO orderInfoHistoryDAO;
	
	/**
	 * 订单实体
	 */
	private BaseOrder order;

	/**
	 * 获得当前状态枚举
	 */
	public OrderStatus getStatus() {
		return this.getOrder().getOrderStatus();
	}
	
	public void setStatus(OrderStatus status)
	{
		this.order.setOrderStatus(status);
	}


	
	
	

	
	
	/**
	 * 获得订单实体
	 */
	public BaseOrder getOrder() {
		return order;
	}

	/**
	 * 设置订单实体
	 * @param order  
	 * 		订单实体对象
	 */
	public  void setOrder(BaseOrder order) {
		this.order = order;
	}
	
	/**
	 * 归档订单
	 * @param orderInfo
	 * @throws Exception
	 */
	protected void createAchived(OrderInfoModel orderInfoModel) throws Exception{
		//生成归档订单
		OrderHistory orderHistory = Bean.copyExistPropertis(orderInfoModel, new OrderHistory());
		orderHistory.setSequenceNBR(this.sequenceGenerator.getNextValue());
		orderHistory.setRecDate(new Date());
		orderHistory.setRecStatus(CoreConstants.COMMON_ACTIVE);
		orderHistory.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
		orderInfoHistoryDAO.insert(orderHistory);
		//删除订单
		orderInfoDAO.delete(orderInfoModel.getSequenceNBR());
	}
	
}
