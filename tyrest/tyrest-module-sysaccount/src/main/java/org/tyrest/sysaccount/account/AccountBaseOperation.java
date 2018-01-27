package org.tyrest.sysaccount.account;

import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.sysaccount.trade.AccountTradeType;

public enum AccountBaseOperation implements AccountTradeType
{

	/**
	 * 入账
	 */
	INCOME("入账","accountIncomeRule"),
	
	/**
	 * 出账
	 */
	SPEND("出账","accountSpendRule"),
	
	
	/**
	 * 初始化账户
	 */
	INIT("初始化",""),
	
	/**
	 * 锁定账户
	 */
	LOCK("锁定",""),
	
	/**
	 * 解锁账户
	 */
	UNLOCK("解锁",""),
	
	
	/**
	 * 失效
	 */
	INVALID("失效","");
	
	private String operationName;
	private String operationRule;
	
	
	private AccountBaseOperation( String operationName,String operationRule)
	{
		this.operationName = operationName;
		this.operationRule = operationRule;
	}
	
	
	
	public String getOperationName()
	{
		return operationName;
	}
	public String getOperationRule()
	{
		return operationRule;
	}

	public String getAccountTradeType()
	{
		return this.name();
	}



	@Override
	public String getAccountTradeHandler()
	{
		throw new BadRequestException("不支持的操作");
	}
	
	
}
