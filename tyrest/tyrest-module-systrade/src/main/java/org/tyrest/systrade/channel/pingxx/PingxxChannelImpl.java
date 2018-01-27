package org.tyrest.systrade.channel.pingxx;

import com.pingplusplus.Pingpp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.systrade.channel.BaseChannelProcess;
import org.tyrest.systrade.channel.ChannelProcessor;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;
import org.tyrest.systrade.trade.TradeStatus;
import org.tyrest.systrade.trade.TradeType;

import java.util.Map;

@Component(value = "pingxxChannel")
public class PingxxChannelImpl extends BaseChannelProcess implements ChannelProcessor{



	

	@Value(value = "${PINGXX_APPKEY}")
	private  String pingxx_appkey;


	
	
	private static final String CHANNEL_PIX = "pingxx";
	
	@Override
	public TradeResultModel processTradeRequest(TransactionsSerialModel serialModel,TradeType tradeType, Map<String, Object> extraParams)
			throws Exception {
		Pingpp.apiKey =pingxx_appkey;
		PingxxConstants.PINGXX_APPKEY = pingxx_appkey;
		Trade trade = SpringContextHelper.getBean(CHANNEL_PIX+tradeType.getTradeProcessor());
		TradeResultModel resultModel =  trade.process(serialModel, extraParams);
		
		return resultModel;
	}


	public TransactionsRecordModel processTradeResult(String serialNo , TradeStatus tradeStatus)
			throws Exception {
		//Trade trade = SpringContextHelper.getBean(CHANNEL_PIX+tradeType.getTradeProcessor());

		//Event  pingResultEvent = (Event)result.get("result");


		return null;
	}

}
