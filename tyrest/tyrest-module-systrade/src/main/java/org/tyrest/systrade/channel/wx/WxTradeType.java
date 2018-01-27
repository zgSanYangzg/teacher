package org.tyrest.systrade.channel.wx;

import org.tyrest.systrade.trade.TradeType;

/**
 * Created by Administrator on 2016/12/15.
 */
public enum  WxTradeType implements TradeType {

    JSAPI("公众号支付","jsapiPayment")
    ;



    private String tradeProcessor;
    private String parseString;



    private WxTradeType(String parseString,String tradeProcessor)
    {
        this.parseString    = parseString;
        this.tradeProcessor = tradeProcessor;
    }


    public String getTradeProcessor() {
        return tradeProcessor;
    }

    public String getType() {
        return this.name();
    }

    public String parseString()
    {
        return this.parseString;
    }
}
