package org.tyrest.sysorder.order.state;

import org.tyrest.sysorder.order.BaseOrder;

/**
 * 
 * <pre>
 * 
 *  File: BranchHandler.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  BranchHandler.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
public interface BranchHandler
{
	/**
	 *	流程分支处理方法
	 * @return
	 */
	public BaseOrder branchOperate()throws Exception;
	
	/**
	 * 获得订单实体
	 * @return
	 */
	BaseOrder getOrder();
	
	
	/**
	 * 设置订单实体
	 */
	void setOrder(BaseOrder order);
	
	
	/**
	 * 订单状态
	 */
	void setStatus(OrderStatus orderStatus);
	
	
	/**
	 * 当前状态
	 * @return
	 */
	OrderStatus getStatus();
}

/*
*$Log: av-env.bat,v $
*/