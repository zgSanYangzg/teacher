package org.tyrest.sysaccount.trade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.account.Account;
import org.tyrest.sysaccount.account.AccountManager;
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
 *  File: PaymentHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  虛擬賬戶轉賬
 * 
 *  Notes:
 *  $Id: PaymentHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "transferHandler")
public class TransferHandler extends BaseTradeParams   implements AccountTradeHandler {


	@Autowired
	AccountTransferRecordDAO accountTransferRecordDAO;

	@Autowired
	SequenceGenerator sequenceGenerator;
	/**交易参数*/
	private enum PaymentParams implements TradeParams {

		billNo(true,"账单号"),
		transferType(true,"轉賬類型"),//轉賬的前置業務標識
		amount(true,"交易金额"),
		postscript(true,"交易附言"),
		targetAccountNo(true,"目标账户编号");
		
		private boolean notnull;
		private String paramName;
		PaymentParams(boolean notnull,String paramName)
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
		 if(checkPrams(params, PaymentParams.values()))
		 {
			Integer amount 		   = (Integer)params.get(PaymentParams.amount.name());
			String targetAccountNo = (String)params.get(PaymentParams.targetAccountNo.name());
			String billNo		   = (String)params.get(PaymentParams.billNo.name());
			String postscript	   = (String)params.get(PaymentParams.postscript.name());
			String transferType    = (String)params.get(PaymentParams.transferType.name());
			Account targetAccount  = Account.getAccountInstance(targetAccountNo);


			 //从來源账户出账
			boolean  spendResult   = account.spend(amount,DefaultAccountTradeType.TRANSFER_INTERNAL,billNo);
			 //为目标账户入账
			boolean incomeResult   = targetAccount.income(amount,DefaultAccountTradeType.TRANSFER_INTERNAL,billNo);
			               flage   = spendResult && incomeResult;
			if(!spendResult || !incomeResult)
			{
				throw new BusinessException("转账出现异常.");
			}

			//保存转账记录
			 AccountTransferRecord newRecord
					               = accountTransferRecordDAO.saveTransferRecord( sequenceGenerator.getNextValue(),  account.getAccountInfo().getUserId(),
																		 billNo,  account.getAccountInfo().getAccountNo(),  account.getAccountInfo().getAccountType(),
																		  targetAccountNo,  targetAccount.getAccountInfo().getAccountType(), amount, postscript,  transferType);
			 flage 				   = flage && !ValidationUtil.isEmpty(newRecord);
		 }	 
		return flage;	
	}
	
	

}