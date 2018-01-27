package org.tyrest.systrade.trade;

import java.util.Map;

import org.tyrest.systrade.face.model.TransactionsSerialModel;

public interface Trade {


	/**
	 * 處理交易
	 * @param serialModel
	 * @param extra
	 * @return
	 * @throws Exception
	 */
	TradeResultModel process(TransactionsSerialModel serialModel,Map<String, Object> extra)throws Exception ;


}
