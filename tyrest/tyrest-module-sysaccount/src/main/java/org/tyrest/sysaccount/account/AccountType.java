package org.tyrest.sysaccount.account;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AccountType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  虚拟账户类型
 *  当前系统定义每个用户的每种账户类型只存在一个账户
 * 
 *  Notes:
 *  $Id: AccountType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface AccountType {

	
	/**
	 * 
	 * 账户类型标识code
	 *
	 * @return
	 */
	public String getAccountType();
	
	
	/**
	 * 
	 * 账户类型名称
	 *
	 * @return
	 */
	public String getAccountName();
	
}
