package org.tyrest.systrade.trade;

public enum DefaultTradeStatus  implements TradeStatus{
	
	/**
	 * 交易请求已提交
	 */
	REQUESTED,
	
	
	/**
	 * 交易处理成功
	 */
	SUCCESS,
	
	
	/**
	 * 交易失败
	 */
	FAILURE;

	@Override
	public String getStatus() {
		return this.name();
	}

}
