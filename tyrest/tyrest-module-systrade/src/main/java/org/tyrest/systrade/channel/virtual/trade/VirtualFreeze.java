package org.tyrest.systrade.channel.virtual.trade;

import org.springframework.stereotype.Component;
import org.tyrest.sysaccount.account.AccountManager;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;

import java.util.HashMap;
import java.util.Map;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: VirtualFreeze.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  虛擬賬戶凍結
 * 
 *  Notes:
 *  $Id: VirtualFreeze.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "virtualFreeze")
public class VirtualFreeze implements Trade{

	/**
	 * 组装参数
	 * @param seriaModel
	 * @param extra
	 * @return
	 * @throws Exception
	 */
	private  Map<String, Object> configure(TransactionsSerialModel seriaModel, Map<String,Object> extra) throws Exception
	{
		return extra;
	}
	
	
	@Override
	public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception
	{
		TradeResultModel resultModel 	= new TradeResultModel();//交易结果






		//#1.组装交易参数
		Map<String,Object> tradeParams 	= configure(serialModel,extra);//交易参数
		AccountType accountType  		=  (AccountType)extra.get(AccountConstants.ACCOUNT_TYPE);//账户类型
		AccountManager accountManager 	= new AccountManager(serialModel.getUserId(),accountType);
		boolean result 					= accountManager.executeTrade(DefaultAccountTradeType.FREEZE, tradeParams);
		resultModel.setResult(result);
		resultModel.setResultCode("OK");
		resultModel.setCalledSuccess(result);
		return resultModel;
	}






}
