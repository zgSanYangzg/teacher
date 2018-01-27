package org.tyrest.sysaccount.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.account.Account;
import org.tyrest.sysaccount.account.DefaultAccountType;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.face.orm.dao.AccountCashoutRecordDAO;
import org.tyrest.sysaccount.face.orm.dao.AccountTransferRecordDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountCashoutRecord;
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
 *  File: CashoutHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  凍結指定賬戶金額
 * 
 *  Notes:
 *  $Id: CashoutHandler.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "cashoutHandler")
public class CashoutHandler extends BaseTradeParams   implements AccountTradeHandler {



	@Autowired
	AccountTransferRecordDAO accountTransferRecordDAO;

	@Autowired
	AccountCashoutRecordDAO  accountCashoutRecordDAO;



	/**交易参数*/
	private enum CashoutParams implements TradeParams {
 
		billNo(true,"账单号"),	   //用户賬單
		userId(true,"用戶Id"),
		applyStatus(false,"提現申請狀態"),
		amount(true,"交易金额"),	   //交易金额
		postscript(true,"交易附言"); //附言
		
		private boolean notnull;
		private String paramName;
		CashoutParams(boolean notnull,String paramName)
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
		 if(checkPrams(params, CashoutParams.values()))
		 {
			 // #1.驗證提現記錄和其凍結記錄
			 Long userId 						  = Long.parseLong((String)params.get(CashoutParams.userId.name()));
			 String billNo						  = (String)params.get(CashoutParams.billNo.name());
			 int	amount						  = (Integer)params.get(CashoutParams.amount.name());
			 String applyStatus					  = (String)params.get(CashoutParams.applyStatus.name());
			 AccountTransferRecord transferRecord = accountTransferRecordDAO.queryByCode(userId,account.getAccountInfo().getAccountNo(),billNo,DefaultAccountTradeType.CASHOUT.name());
			 AccountCashoutRecord cashoutRecord   = accountCashoutRecordDAO.queryByCode(billNo);

			 if(!ValidationUtil.isEmpty(transferRecord)
					 && !ValidationUtil.isEmpty(cashoutRecord)
					 &&	amount == cashoutRecord.getApplayAmount()
					 && amount == transferRecord.getTransferAmount())
			 {
				// #2.更新提現記錄--默認是手動確認提現
				 cashoutRecord.setApplyStatus(ValidationUtil.isEmpty(applyStatus)?AccountConstants.CASHOUT_STATUS_TRANSFERRED:applyStatus);
				 accountCashoutRecordDAO.update(cashoutRecord);
				 // #3.將凍結賬戶的對應的提現凍結金額做出賬處理\
				 Account freezeAccount			  = Account.getAccountInstance(userId,DefaultAccountType.FROZEN);
				 flage    						  = freezeAccount.spend(amount,DefaultAccountTradeType.CASHOUT,billNo);
			 }else{
				 throw new BusinessException("提現交易參數有誤.");
			 }
		 }
		return flage;	
	}
	
	

}