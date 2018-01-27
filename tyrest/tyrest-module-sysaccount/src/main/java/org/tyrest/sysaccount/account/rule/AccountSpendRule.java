package org.tyrest.sysaccount.account.rule;

import org.springframework.stereotype.Component;
import org.tyrest.sysaccount.account.AccountOperateRule;
import org.tyrest.sysaccount.account.Account;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AccountSpendRule.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  账户出账限制条件
 * 
 *  Notes:
 *  $Id: AccountSpendRule.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "accountSpendRule")
public class AccountSpendRule  implements AccountOperateRule
{

	@Override
	public boolean checkOperation(Account account )
	{
		
		return false;
	}
	
}
