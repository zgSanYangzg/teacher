package org.tyrest.systrade.trade;


import org.tyrest.systrade.face.model.TransactionsRecordModel;

import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
public interface TradeResultProcessor {

    TransactionsRecordModel processResult(Map<String,Object> infoMap) throws Exception;
}
