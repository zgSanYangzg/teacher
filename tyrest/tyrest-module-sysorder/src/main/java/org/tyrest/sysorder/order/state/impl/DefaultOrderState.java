package org.tyrest.sysorder.order.state.impl;


import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.sysorder.constants.ErrorMessageConstants;
import org.tyrest.sysorder.order.BaseOrder;
import org.tyrest.sysorder.order.state.BaseOrderState;
import org.tyrest.sysorder.order.state.StateHandler;

/**
 * 
 * <pre>
 * 
 *  File: DefaultOrderState.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  DefaultOrderState.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
@Component(value = "defaultOrderState")
public class DefaultOrderState extends BaseOrderState implements  StateHandler{
	
	
	@Override
	public BaseOrder process() throws Exception{		
		throw new BadRequestException(ErrorMessageConstants.ORDER_STATUS_ERROR);
	}

}

/*
*$Log: av-env.bat,v $
*/