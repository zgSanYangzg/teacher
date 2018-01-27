package org.tyrest.systrade.channel.virtual.trade;

import org.springframework.stereotype.Component;
import org.tyrest.sysaccount.account.AccountManager;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;

import java.util.Map;

@Component(value = "virtualTransfer")
public class VirtualTransfer implements Trade {


    /**
     * 组装参数
     * @param seriaModel
     * @param extra
     * @return
     * @throws Exception
     */
    private Map<String, Object> configure(TransactionsSerialModel seriaModel, Map<String,Object> extra) throws Exception
    {
        return extra;
    }


    @Override
    public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception
    {
        TradeResultModel resultModel 	= new TradeResultModel();//交易结果

        //#1.组装交易参数
        Map<String,Object> tradeParams 	= configure(serialModel,extra);//交易参数
        AccountType accountType  		=  (AccountType)extra.get(AccountConstants.ACCOUNT_TYPE);//账户类型
        AccountManager accountManager 	= new AccountManager(serialModel.getUserId(),accountType);
        boolean result 					= accountManager.executeTrade(DefaultAccountTradeType.TRANSFER_INTERNAL, tradeParams);
        resultModel.setResult(result);
        resultModel.setResultCode("OK");
        resultModel.setCalledSuccess(result);
        return resultModel;
    }
}
