package org.tyrest.systrade.channel;

import java.util.Map;

import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.TradeResultModel;
import org.tyrest.systrade.trade.TradeStatus;
import org.tyrest.systrade.trade.TradeType;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ChannelProcessor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  交易渠道处理器
 *  pingxx统一第三方支付渠道（支持支付宝、微信）。
 *  现金、虚拟账户
 * 
 *  Notes:
 *  $Id: ChannelProcessor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface ChannelProcessor {


	/**
	 * 处理交易请求
	 * @param serialModel	交易流水
	 * @param tradeType		交易類型
	 * @param extraParams	附加參數（包含渠道所需附加參數、當前系統附加參數）
	 * @return
	 * @throws Exception
	 */
	TradeResultModel processTradeRequest(TransactionsSerialModel serialModel, TradeType tradeType,Map<String, Object> extraParams) throws Exception;
	
	
	
	
	
	
	
	/**
	 * 	处理返回结果
	 *	异步返回结果、前端直接返回结果
	 *
	 * @param result 异步交易结果
	 * @param tradeStatus 交易状态
	 * @return 
	 * @throws Exception
	 */
	TransactionsRecordModel processTradeResult (String serialNo , TradeStatus tradeStatus)throws Exception;
}

/*
*$Log: av-env.bat,v $
*/