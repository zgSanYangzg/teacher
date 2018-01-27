package org.tyrest.cms.trade;

import org.tyrest.systrade.trade.BillType;

/**
 * Created by magintursh on 2016-12-16.
 */
public enum PostingBillType implements BillType {


    POSTING_PAYMENT("帖子缴费","contentService"),

    QUESTION_REWARD("问答悬赏",""),


    CREATE_POSTING("帖子奖励",""),

    AUDIT_POST("审帖佣金","");



    PostingBillType(String billTypeName, String resultProcessor)
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
