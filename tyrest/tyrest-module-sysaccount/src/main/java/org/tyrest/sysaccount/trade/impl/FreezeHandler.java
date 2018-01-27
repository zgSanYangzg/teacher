package org.tyrest.sysaccount.trade.impl;

import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.account.Account;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.account.DefaultAccountType;
import org.tyrest.sysaccount.face.orm.dao.AccountInfoDAO;
import org.tyrest.sysaccount.trade.AccountTradeHandler;
import org.tyrest.sysaccount.trade.BaseTradeParams;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;
import org.tyrest.sysaccount.trade.TradeParams;

import java.util.Map;
import java.util.HashMap;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: FreezeHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  凍結指定賬戶金額
 * 
 *  Notes:
 *  $Id: FreezeHandler.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "freezeHandler")
public class FreezeHandler extends BaseTradeParams   implements AccountTradeHandler {



	/**交易参数*/
	private enum FreezeParams implements TradeParams {
 
		billNo(true,"账单号"),	   //用户賬單
		userId(true,"用戶Id"),	   //交易金额
		amount(true,"交易金额"),	   //交易金额
		transferType(false,"轉賬類型"),//可能有多種業務需要凍結資金，轉賬類型作爲前置業務標識
		postscript(true,"交易附言"); //附言
		
		private boolean notnull;
		private String paramName;
		FreezeParams(boolean notnull,String paramName)
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
		 if(checkPrams(params, FreezeParams.values()))
		 {
			 //獲取當前凍結賬戶信息
			 Long userId 				= (Long)params.get(FreezeParams.userId.name());
			 Account targetAccount  	= Account.getAccountInstance( userId, DefaultAccountType.FROZEN);
			 String transferType		= (String)params.get(FreezeParams.transferType.name());

			 params.put("transferType", ValidationUtil.isEmpty(transferType)?DefaultAccountTradeType.FREEZE.name():transferType);
			 params.put("targetAccountNo",targetAccount.getAccountInfo().getAccountNo());


			 //將衹當金額從來源賬戶轉入凍結賬戶
			 AccountTradeHandler transferHandler
					 					= SpringContextHelper.getBean("transferHandler");
			 flage 						=  transferHandler.execute(params,account);
		 }
		return flage;	
	}
	
	

}