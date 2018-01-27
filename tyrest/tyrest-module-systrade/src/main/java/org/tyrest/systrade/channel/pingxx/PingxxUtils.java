package org.tyrest.systrade.channel.pingxx;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Transfer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by magintursh on 2016-12-17.
 */
public class PingxxUtils {


    /**
     *
     * @param event  Pingxx 交易結果事件對象
     * @return  不同類型的事件返回結果有所不同
     */
    public static Map<String,Object> pingxxEventProcess(Event event)
    {
        Map<String,Object> returnMap
                          = new HashMap<String,Object>();
        String eventType  = event.getType();

        switch (eventType)
        {
            case PingxxConstants.EVENT_CHARGE_SUCCEEDED :
                returnMap = parseCharge(event);
                break;
            case PingxxConstants.EVENT_TRANSFER_SUCCEEDED :
                returnMap = parseTransfer(event);
            default:
                returnMap = new HashMap<>();
        }

        return  returnMap;
    }


    private static Map<String,Object> parseCharge(Event event)
    {
        Map<String,Object> returnMap = new HashMap<>();
        Charge charge  = (Charge)event.getData().getObject();
        returnMap.put(PingxxConstants.SERIALNO,charge.getMetadata().get(PingxxConstants.SERIALNO));
        returnMap.put(PingxxConstants.CHANNEL_SERIAL_NO,charge.getTransactionNo());
        returnMap.put(PingxxConstants.METADATA,charge.getMetadata());
        returnMap.put(PingxxConstants.BILL_TYPE,charge.getMetadata().get(PingxxConstants.BILL_TYPE));
        returnMap.put(PingxxConstants.BILL_TYPE_PROCESSOR,charge.getMetadata().get(PingxxConstants.BILL_TYPE_PROCESSOR));
        return returnMap;
    }

    private static Map<String,Object> parseTransfer(Event event)
    {
        Map<String,Object> returnMap = new HashMap<>();
        Transfer transfer  = (Transfer)event.getData().getObject();
        returnMap.put(PingxxConstants.SERIALNO,transfer.getMetadata().get(PingxxConstants.SERIALNO));
        returnMap.put(PingxxConstants.CHANNEL_SERIAL_NO,transfer.getTransaction_no());
        returnMap.put(PingxxConstants.METADATA,transfer.getMetadata());
        returnMap.put(PingxxConstants.BILL_TYPE,transfer.getMetadata().get(PingxxConstants.BILL_TYPE));
        return returnMap;
    }


}
