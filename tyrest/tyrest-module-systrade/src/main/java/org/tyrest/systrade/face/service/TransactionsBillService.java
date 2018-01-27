package org.tyrest.systrade.face.service;


import org.tyrest.core.mysql.BaseService;
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
public interface TransactionsBillService extends BaseService<TransactionsBillModel,TransactionsBill>
{
    TransactionsBillModel createBill(String agencyCode, int tradeAmount, BillType billType, Long userId) throws Exception;
}
