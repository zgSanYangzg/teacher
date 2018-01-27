package org.tyrest.systrade.face.service;


import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systrade.channel.ChannelType;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.trade.TradeResultModel;
import org.tyrest.systrade.trade.TradeType;

import java.util.Map;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsRecordServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsRecordServiceImpl.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface TransactionsRecordService extends BaseService<TransactionsRecordModel,TransactionsRecord>
{
    TransactionsRecordModel queryBySerialNo(String serialNo) throws Exception;

    public TradeResultModel updateForTrade(TransactionsBillModel billModel, TradeType tradeType, ChannelType channelType, Map<String,Object> extraParams) throws Exception;
}
