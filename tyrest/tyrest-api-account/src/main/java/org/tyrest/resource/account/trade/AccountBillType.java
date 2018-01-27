package org.tyrest.resource.account.trade;

import org.tyrest.systrade.trade.BillType;

/**
 * Created by magintursh on 2016-12-16.
 */
public enum AccountBillType implements BillType {
    /**
     * 凍結賬戶資金
     */
    FREEZE("冻结","");



    AccountBillType(String billTypeName, String resultProcessor)
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
