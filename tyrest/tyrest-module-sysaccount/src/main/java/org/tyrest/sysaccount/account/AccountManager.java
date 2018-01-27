package org.tyrest.sysaccount.account;

import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.face.model.AccountInfoModel;
import org.tyrest.sysaccount.trade.AccountTradeHandler;
import org.tyrest.sysaccount.trade.AccountTradeType;

import java.util.Map;

public final class AccountManager {
	
	private Account account;
	
	/**
	 * 根据userId和accountType创建一个AccountManager操作对象
	 * @param userId
	 * @param accountType
	 * @throws Exception
	 */
	public AccountManager(Long userId,AccountType accountType) throws Exception {
		this.account  = Account.getAccountInstance(userId,accountType);
	}
	
	/**
	 * 根据accountNo创建一个AccountManager操作对象
	 * @param accountNo
	 * @throws Exception
	 */
	public AccountManager(String accountNo) throws Exception {
		this.account  = Account.getAccountInstance(accountNo);
	}
	

	/**
	 * 
	 * 执行交易
	 * @param accountTradeType	交易类型
	 * @param params	交易参数
	 * @throws Exception
	 */
	public boolean executeTrade(AccountTradeType accountTradeType,Map<String,Object> params) throws Exception
	{
		//#1.验证操作规则
		//#2.初始化账户操作对象
		//#3.执行交易
		//#4.交易结果处理
		AccountTradeHandler tradeHandler = SpringContextHelper.getBean(accountTradeType.getAccountTradeHandler());
		if(ValidationUtil.isEmpty(tradeHandler)){
			throw new BusinessException(AccountConstants.NOTFOUNT_ACCOUNT_HANDLER);
		}
		return tradeHandler.execute(params,account);
	}
	
	
	
	
	
	
	/**
	 * 初始化账户
	 * @param userId	用户id
	 * @return
	 * @throws Exception
	 */
	public static boolean initAccountInfo(Long userId) throws Exception
	{
		for(AccountType accountType: DefaultAccountType.values())
		{
			Account.initAccountInfo(userId,accountType);
		}
		return true;
	}

	public static boolean initAccountInfo(Long userId,AccountType accountType) throws Exception
	{
		Account.initAccountInfo(userId,accountType);
		return true;
	}
	

	
	
	/**
	 * 锁定账户
	 * @param userId		
	 * @param accountType
	 * @return
	 * @throws Exception 
	 */
	public boolean lock(Long userId, AccountType accountType) throws Exception
	{
		return account.lock();
	}


	public AccountInfoModel getAccountModel() throws Exception {
			return Bean.copyExistPropertis(this.account.getAccountInfo(),new AccountInfoModel());
	}

	public Account getAccountInstance()
	{
		return this.account;
	}

}
