package org.tyrest.publicuser.trade;

import org.tyrest.systrade.trade.BillType;

/**
 * Created by magintursh on 2016-12-16.
 */
public enum AppuserBillType implements BillType {


    PROXY_RENEWAL("代理續費","wbsjAppuserService"),

    CASHOUT("用户提现","wbsjAppuserService"),


    RECHARGE("账户充值",""),

    SIGN("签到赠送",""),


    /**
     * 凍結賬戶資金
     */
    FREEZE("冻结","");



    AppuserBillType(String billTypeName,String resultProcessor)
    {
        this.billTypeName    = billTypeName;
        this.resultProcessor = resultProcessor;
    }
    private String billTypeName;
    private String resultProcessor;
    public String getBillType()
    {
        return this.name();
    }
    public String getResultProccesor()
    {
        return this.resultProcessor;
    }

    public String getBillTypeName() {
        return billTypeName;
    }
}
