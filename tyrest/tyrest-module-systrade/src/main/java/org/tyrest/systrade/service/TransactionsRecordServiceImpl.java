package org.tyrest.systrade.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.systrade.TradeProcess;
import org.tyrest.systrade.channel.ChannelType;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.orm.dao.TransactionsRecordDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.face.service.TransactionsRecordService;
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
@Component(value = "transactionsRecordService")
public class TransactionsRecordServiceImpl extends BaseServiceImpl<TransactionsRecordModel,TransactionsRecord>
        implements TransactionsRecordService
{

    @Autowired
    TradeProcess tradeProcess;
    @Autowired
    TransactionsRecordDAO TransactionsRecordDAO;
	public TransactionsRecordModel queryBySerialNo(String serialNo) throws Exception {
        TransactionsRecordModel returnModel = null;
        TransactionsRecord record  =  TransactionsRecordDAO.findBySerialNo(serialNo);
        if(!ValidationUtil.isEmpty(record))
        {
            returnModel = Bean.toModel(record,new TransactionsRecordModel());
        }
        return returnModel;
    }


    public TradeResultModel updateForTrade(TransactionsBillModel billModel, TradeType tradeType, ChannelType channelType, Map<String,Object> extraParams) throws Exception {
            return  tradeProcess.sendTradeRequest(billModel, tradeType, channelType,  extraParams);
    }
}
