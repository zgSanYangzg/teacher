package org.tyrest.systrade.channel.wx;

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

/**
 * Created by Administrator on 2016/12/15.
 */
@Component(value = "wxChannel")
public class WxChannelImpl extends BaseChannelProcess implements ChannelProcessor {


    /**
     *  交易处理
     * @param serialModel 交易流水
     * @param tradeType 交易类型
     * @param extra 附加参数
     * @return
     * @throws Exception
     */
    public TradeResultModel processTradeRequest(TransactionsSerialModel serialModel, TradeType tradeType, Map<String,Object> extra) throws Exception
    {

        Trade trade             = (Trade) SpringContextHelper.getBean(tradeType.getTradeProcessor());
        TradeResultModel result = trade.process(serialModel,extra);
        return result;
    }







    /**
     * 	处理返回结果
     *	异步返回结果、前端直接返回结果
     * @param tradeStatus 交易状态
     * @return
     * @throws Exception
     */
   public  TransactionsRecordModel processTradeResult (String serialNo , TradeStatus tradeStatus)throws Exception
    {
        return null;
    }

}
