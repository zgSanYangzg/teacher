package org.tyrest.systrade.channel.wx.trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;

import java.util.Map;

@Component(value = "jsapiPayment")
public class JSAPIPayment implements Trade{

	
	private static final Logger logger = LoggerFactory.getLogger(JSAPIPayment.class);
	/**
	 * 组装参数
	 * @param seriaModel
	 * @param extra
	 * @return
	 * @throws Exception
	 */
	private  Map<String, Object> configure(TransactionsSerialModel seriaModel, Map<String,Object> extra) throws Exception
	{		

		return null;
	}
	
	
	@Override
	public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception {
		
		
		TradeResultModel resultModel = new TradeResultModel();//交易结果	

		
		return resultModel;
	}
	
	
	

	
	
}
