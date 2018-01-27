package org.tyrest.sysaccount.account;

public enum DefaultAccountType  implements AccountType {

	

	/**
	 * 虚拟账户
	 */
	VIRTUAL("虚拟账户"),
	
	/**
	 * 冻结账户；用作资金暂存，转移等。
	 */
	FROZEN("冻结账户"),
	
	/**
	 * 积分账户
	 */
	SCORE("积分账户");
	
	private String accountName;
	
	private DefaultAccountType(String accountName )
	{
		this.accountName = accountName;
	}
	
	public String getAccountType()
	{
		return this.name();
	}

	public String getAccountName()
	{
		return accountName;
	}

}
