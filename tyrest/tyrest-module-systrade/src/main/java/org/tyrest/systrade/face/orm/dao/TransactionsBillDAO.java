package org.tyrest.systrade.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.trade.BillType;
import org.tyrest.systrade.trade.TradeType;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsBillServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsBillServiceImpl.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface TransactionsBillDAO extends GenericDAO<TransactionsBill>
{
	
	/**
	 * 根据账单编号查找账单
	 * @param billNo
	 * @return
	 * @throws Exception
	 */
	TransactionsBill findByBillNo(String billNo) throws Exception;


	TransactionsBill createBill(String agencyCode, String billNo, Long SequenceNbr,
								int tradeAmount, BillType billType, Long userId) throws Exception;
	
}
