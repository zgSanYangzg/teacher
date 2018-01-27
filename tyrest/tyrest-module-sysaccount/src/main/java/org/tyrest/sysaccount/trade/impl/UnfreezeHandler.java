package org.tyrest.sysaccount.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.account.Account;
import org.tyrest.sysaccount.account.DefaultAccountType;
import org.tyrest.sysaccount.face.orm.dao.AccountTransferRecordDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountTransferRecord;
import org.tyrest.sysaccount.trade.AccountTradeHandler;
import org.tyrest.sysaccount.trade.BaseTradeParams;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;
import org.tyrest.sysaccount.trade.TradeParams;

import java.util.Map;

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
 *  解冻资金
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
@Component(value = "unfreezeHandler")
public class UnfreezeHandler extends BaseTradeParams   implements AccountTradeHandler {


	@Autowired
	AccountTransferRecordDAO accountTransferRecordDAO;
	/**交易参数*/
	private enum UnfreezeParams implements TradeParams {
 
		billNo(true,"账单号"),	   //用户賬單
		userId(true,"用户id"),
		transferType(false,"轉賬類型"),//可能有多種業務需要凍結資金，轉賬類型作爲前置業務標識
		postscript(true,"交易附言"); //附言
		
		private boolean notnull;
		private String paramName;
		UnfreezeParams(boolean notnull,String paramName)
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
		 if(checkPrams(params, UnfreezeParams.values()))
		 {
			 String billNo 				= (String)params.get(UnfreezeParams.billNo.name());
			 Long   userId 				= Long.parseLong((String)params.get(UnfreezeParams.userId.name()));
			 String transferType		= (String)params.get(UnfreezeParams.transferType.name());

			 //获取冻结记录
			 AccountTransferRecord transferRecord
					 					=  this.accountTransferRecordDAO.queryByCode(userId,account.getAccountInfo().getAccountNo(),billNo,transferType);

			 params.put("transferType", DefaultAccountTradeType.UNFREEZE.name());
			 params.put("targetAccountNo",transferRecord.getSourceAccountNo());
			 params.put("amount",transferRecord.getTransferAmount());

			 //当前用户的冻结账户
			 Account  freezeAccount 	= Account.getAccountInstance(userId,DefaultAccountType.FROZEN);
			 //将冻结的资金转回原来账户
			 AccountTradeHandler transferHandler
					 					= SpringContextHelper.getBean("transferHandler");
			 flage 						=  transferHandler.execute(params,freezeAccount);
		 }
		return flage;	
	}
	
	

}