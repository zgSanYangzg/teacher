package org.tyrest.sysorder.order.rule;

import org.springframework.stereotype.Component;
import org.tyrest.sysorder.order.BaseOrder;
/**
 * 
 * <pre>
 * 
 *  File: DefaultOperationLimitRule.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  DefaultOperationLimitRule.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
@Component(value = "defaultOperationLimitRule")
public class DefaultOperationLimitRule implements OperationLimitHandler
{

	@Override
	public boolean checkOperation(BaseOrder order) throws Exception
	{
		return true;
	}

}

/*
*$Log: av-env.bat,v $
*/