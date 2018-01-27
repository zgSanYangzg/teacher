package org.tyrest.systrade.channel.pingxx.trade;

import com.pingplusplus.model.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;

import java.util.HashMap;
import java.util.Map;

@Component(value = "pingxxTransfer")
public class PingxxTransfer implements Trade {



    @Value(value = "${PINGXX_APPID}")
    private  String pingxx_appid;

    private static final Logger logger = LoggerFactory.getLogger(PingxxTransfer.class);
    /**
     * 组装参数
     * @param seriaModel
     * @param extra
     * @return
     * @throws Exception
     */
    private Map<String, Object> configure(TransactionsSerialModel seriaModel, Map<String,Object> extra) throws Exception
    {
        if(ValidationUtil.isEmpty(pingxx_appid))
        {
            throw new BusinessException("PINGXX_APPID配置有误.");
        }
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put(PingxxConstants.ORDER_NO, seriaModel.getSerialNo());
        chargeParams.put(PingxxConstants.AMOUNT, seriaModel.getTradeAmount());
        Map<String, String> app = new HashMap<String, String>();
        app.put(PingxxConstants.ID, pingxx_appid);
        chargeParams.put(PingxxConstants.APP, app);
        chargeParams.put(PingxxConstants.CURRENCY, PingxxConstants.CNY);
        chargeParams.put("type",  "b2c");
        chargeParams.put("recipient", extra.get(PingxxConstants.OPENID));
        chargeParams.put(PingxxConstants.CHANNEL, seriaModel.getPayMethod());
        chargeParams.put(PingxxConstants.DESCRIPTION, extra.get(PingxxConstants.SUBJECT));
        chargeParams.put(PingxxConstants.METADATA, extra.get(PingxxConstants.METADATA));
        chargeParams.put(PingxxConstants.EXTRA, new HashMap<>());
        return chargeParams;
    }


    @Override
    public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception {


        TradeResultModel resultModel = new TradeResultModel();//交易结果

        //#1.组装交易参数
        Map<String,Object> tradeParams = configure(serialModel,extra);//交易参数
        try {
            //#2.发起交易
            Transfer transfer = Transfer.create(tradeParams);

            resultModel.setResult(transfer);
            resultModel.setCalledSuccess(true);
        } catch (Exception e) {
            //#3.调用交易渠道的异常需要自行处理
            resultModel.setResultMessage(e.getMessage());
            resultModel.setCalledSuccess(false);
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return resultModel;
        }

        return resultModel;
    }
}
