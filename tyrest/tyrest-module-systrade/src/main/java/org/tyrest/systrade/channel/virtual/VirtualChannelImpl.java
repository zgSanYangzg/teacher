package org.tyrest.systrade.channel.virtual;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.systrade.channel.BaseChannelProcess;
import org.tyrest.systrade.channel.ChannelProcessor;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;
import org.tyrest.systrade.trade.TradeStatus;
import org.tyrest.systrade.trade.TradeType;

@Component(value = "virtualChannel")
public class VirtualChannelImpl extends BaseChannelProcess implements ChannelProcessor{

	private static String CHANNEL_PIX = "virtual";
	
	@Override
	public TradeResultModel processTradeRequest(TransactionsSerialModel serialModel, TradeType tradeType,
			Map<String, Object> extra) throws Exception
	{


			/*	billNo(true,"账单号"),	   //用户賬單
				userId(true,"用戶Id"),	   //交易金额
				amount(true,"交易金额"),	   //交易金额
				transferType(false,"轉賬類型"),//可能有多種業務需要凍結資金，轉賬類型作爲前置業務標識
				postscript(true,"交易附言"); //附言*/


		extra.put(PingxxConstants.BILLNO, serialModel.getBillNo());
		extra.put(PingxxConstants.USERID, serialModel.getUserId());
		extra.put(PingxxConstants.AMOUNT, serialModel.getTradeAmount());
		extra.put("transferType",serialModel.getTradeType());
		extra.put("postscript",serialModel.getTradeType());
		Trade trade 				 = SpringContextHelper.getBean(CHANNEL_PIX+tradeType.getTradeProcessor());
		TradeResultModel resultModel = trade.process(serialModel, extra);
		return resultModel;
	}

	@Override
	public TransactionsRecordModel processTradeResult(String serialNo, TradeStatus tradeStatus) throws Exception
	{
		return null;
	}

}
