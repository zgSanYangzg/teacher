package org.tyrest.sysorder.order.process;

import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysorder.order.BaseOrder;
import org.tyrest.sysorder.order.rule.OperationLimitHandler;
import org.tyrest.sysorder.order.state.BranchHandler;
import org.tyrest.sysorder.order.state.BranchOperationType;
import org.tyrest.sysorder.order.state.StateHandler;

public class OrderManager
{

	private StateHandler state;
	
	public OrderManager(BaseOrder order){
		state = getStateInstance(order);
	}

	/**
	 * 订单主线流转流程 (从提交到成功完成的主线流程，其他支线流程单独处理，如果当前类的方法不足则可以继承当前类实现自己的处理方法。)
	 * @return 状态变更之后的order
	 */
	public BaseOrder transfer() throws Exception{
		BaseOrder order = null;
		if(checkLimit(this.getState().getStatus().getRuleHandler())){
			order = this.getState().process();
		}
		return order;
	}
	
	
	
	public BaseOrder branchOpreate(BranchOperationType branchOperationType) throws Exception
	{
		BaseOrder returnOrder = null;
		BranchHandler branch = SpringContextHelper.getBean(branchOperationType.getBranchHandler());
		if(!ValidationUtil.isEmpty(branch))
		{
			branch.setOrder(this.getState().getOrder());
			branch.setStatus(this.getState().getStatus());
			if(this.checkLimit(branchOperationType.getOprationRuleHandler())){
				returnOrder = branch.branchOperate();
			}
		}else{
			throw new BusinessException("表单状态有误.");
		}
		
		return returnOrder;
	}

	
	

	/**
	 * 获得当前的状态处理器
	 * @return
	 */
	public StateHandler getState(){
		return this.state;
	}
	
	/**
	 * 订单流转主线前的验证
	 * @return
	 * @throws Exception
	 */
	private Boolean checkLimit(String beanName) throws Exception {
		if(ValidationUtil.isEmpty(beanName)){
			return true;
		}
		OperationLimitHandler rule =  SpringContextHelper.getBean(beanName);
		if (!ValidationUtil.isEmpty(rule) && rule.checkOperation(this.getState().getOrder()))
		{
			return true;
		}else{
			throw new BusinessException("表单操作限制验证不通过.");
		}
	}
	
	
	
	/**
	 * 获得订单状态处理器实例
	 * @param status
	 *            订单状态
	 * @param order
	 *            订单实体
	 * @return
	 */
	private   StateHandler getStateInstance(BaseOrder order) {
		
		StateHandler state = null;
		if(!ValidationUtil.isEmpty(order) 
				&& !ValidationUtil.isEmpty(order.getOrderStatus()))
		{
			state = SpringContextHelper.getBean(order.getOrderStatus().getStateHandler());
			state.setOrder(order);
		}
		return state;
	}


	
	
}
