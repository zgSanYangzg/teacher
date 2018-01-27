package org.tyrest.sysaccount.trade;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface TradeParams {


    /**是否必填*/
    public boolean isNotnull();

    /**参数名*/
    public String getParesStr();

    /**参数Code*/
    public String getParamCode();


}
