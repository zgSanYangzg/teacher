package org.tyrest.sysaccount.trade.impl;

import org.springframework.stereotype.Component;
import org.tyrest.sysaccount.account.Account;
import org.tyrest.sysaccount.trade.AccountTradeHandler;
import org.tyrest.sysaccount.trade.BaseTradeParams;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;
import org.tyrest.sysaccount.trade.TradeParams;

import java.util.Map;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: RechargeHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RechargeHandler.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "rechargeHandler")
public class RechargeHandler extends BaseTradeParams   implements AccountTradeHandler {

	/**交易参数*/
	private enum RechargeParams implements TradeParams {
 
		billNo(true,"账单号"),	   //用户
		amount(true,"交易金额");   //交易金额
		
		private boolean notnull;
		private String paramName;
		RechargeParams(boolean notnull,String paramName)
		{
			this.notnull = notnull;
			this.paramName = paramName; 
		}  
		public boolean isNotnull()
		{
			return notnull;
		}
		public String getParesStr()
		{
			return paramName;
		}
		public String getParamCode(){return this.name();}
		
	}

	
	
	@Override
	public boolean execute(Map<String, Object> params,Account account) throws Exception {
		boolean flage = false;
		//解析参数
		 if(checkPrams(params, RechargeParams.values()))
		 {
			Integer amount = (Integer)params.get(RechargeParams.amount.name());
			
			//执行交易
			flage = account.income(amount,DefaultAccountTradeType.RECHARGE,params.get(RechargeParams.billNo.name()).toString());
			
			//验证结果			 
		 }	 
		return flage;	
	}
	
	

}