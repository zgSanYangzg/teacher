package org.tyrest.systrade.channel.pingxx.trade;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.trade.Trade;
import org.tyrest.systrade.trade.TradeResultModel;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;

@Component(value = "pingxxPayment")
public class PingxxPayment implements Trade{

	
	private static final Logger logger = LoggerFactory.getLogger(PingxxPayment.class);



	@Value(value = "${PINGXX_APPID}")
	private  String pingxx_appid;
	/**
	 * 组装参数
	 * @param seriaModel
	 * @param extra
	 * @return
	 * @throws Exception
	 */
	private  Map<String, Object> configure(TransactionsSerialModel seriaModel, Map<String,Object> extra) throws Exception
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
		chargeParams.put(PingxxConstants.CHANNEL, seriaModel.getPayMethod());
		chargeParams.put(PingxxConstants.CLIENT_IP, RequestContext.getRequestIP());
		chargeParams.put(PingxxConstants.SUBJECT, extra.get(PingxxConstants.SUBJECT));
		chargeParams.put(PingxxConstants.BODY, extra.get(PingxxConstants.BODY));
		chargeParams.put(PingxxConstants.METADATA, extra.get(PingxxConstants.METADATA));
		chargeParams.put(PingxxConstants.EXTRA, extra.get(PingxxConstants.EXTRA));
		return chargeParams;
	}
	
	
	@Override
	public TradeResultModel process(TransactionsSerialModel serialModel, Map<String, Object> extra) throws Exception {
		
		
		TradeResultModel resultModel = new TradeResultModel();//交易结果	
		
		//#1.组装交易参数
		Map<String,Object> tradeParams = configure(serialModel,extra);//交易参数
		try {
			//#2.发起交易
			Charge charge = Charge.create(tradeParams);
			
			//将charge对象保存到本地
			
			resultModel.setResult(charge);
			resultModel.setCalledSuccess(true);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException e) {
			
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
