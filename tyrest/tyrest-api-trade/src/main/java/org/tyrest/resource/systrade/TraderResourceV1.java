package org.tyrest.resource.systrade;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.systrade.TradeProcess;
import org.tyrest.systrade.channel.ChannelType;
import org.tyrest.systrade.channel.DefaultChannelType;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.channel.pingxx.PingxxUtils;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.face.service.TransactionsRecordService;
import org.tyrest.systrade.face.service.TransactionsSerialService;
import org.tyrest.systrade.trade.*;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.EventData;


/**
 * 
 * <pre>
 *  Tyrest
 *  File: TraderResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TraderResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@RestController
@RequestMapping(value = "/1/trade")
@TyrestResource(module = "trade",value = "TraderResourceV1", description = "交易")
public class TraderResourceV1
{


	@Autowired
	TradeProcess tradeProcess ;

	@Autowired
	TransactionsSerialService transactionsSerialService;

	@Autowired
	TransactionsRecordService TransactionsRecordService;

	@SuppressWarnings("rawtypes")
	@TyrstOperation(name = "asyncCalblacks",ApiLevel = APILevel.ALL, description = "PING++异步支付回调接口.",needAuth = false)
	@RequestMapping(value = "/async/calblacks/pingxx", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseModel asyncCalblacks(@RequestBody Event event) throws Exception {
		RequestContext.setExeUserId("PINGXX");
		TransactionsRecordModel returnModel 	 = null;
		if (ValidationUtil.isEmpty(event) || ValidationUtil.isEmpty(event.getData())){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		if (!PingxxConstants.EVENT_CHARGE_SUCCEEDED.equals(event.getType())){
			throw new BadRequestException(MessageConstants.DATA_NOT_FOUND);
		}

		Pingpp.apiKey = PingxxConstants.PINGXX_APPKEY;
		event = Event.retrieve(event.getId());
		//#1.處理結果對象
		return ResponseHelper.buildResponseModel(processEvent(event));
	}



	@SuppressWarnings("rawtypes")
	@TyrstOperation(name = "manuallyCalblacks",ApiLevel = APILevel.ALL, description = "pingxx交易手动回调处理.")
	@RequestMapping(value = "/manually/{tradeType}/{id}/calblacks/pingxx", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseModel manuallyCalblacks(@PathVariable String tradeType, @PathVariable String id) throws Exception {
		TransactionsRecordModel returnModel 	 = null;


		//#1.處理結果對象
		Event event 	     = new Event();
		EventData  eventData = new EventData();
		DefaultTradeType tradeTypeE = DefaultTradeType.valueOf(tradeType);
		switch (tradeTypeE)
		{
			case PAYMENT:
				eventData.setObject(Charge.retrieve(id));
				event.setData(eventData);
				event.setType(PingxxConstants.EVENT_CHARGE_SUCCEEDED);
				break;
			case TRANSFER:
				eventData.setObject(Transfer.retrieve(id));
				event.setData(eventData);
				event.setType(PingxxConstants.EVENT_TRANSFER_SUCCEEDED);
				break;
		}
		return ResponseHelper.buildResponseModel(processEvent(event));
	}





	private ResponseModel processEvent(Event event) throws Exception {

		TransactionsRecordModel returnModel 	 = null;
		Map<String,Object> resultMap 			 = PingxxUtils.pingxxEventProcess(event);
		TransactionsSerialModel serialModel 	 =transactionsSerialService.queryByCode((String)resultMap.get(PingxxConstants.SERIALNO));
		TransactionsRecordModel recordModel      = TransactionsRecordService.queryBySerialNo((String)resultMap.get(PingxxConstants.SERIALNO));
		if(!ValidationUtil.isEmpty(recordModel))//交易記錄已經存在
		{
			return ResponseHelper.buildResponseModel(CoreConstants.COMMON_SUCCESS);
		}
		if(!ValidationUtil.isEmpty(serialModel)
				&& !ValidationUtil.isEmpty(resultMap.get(PingxxConstants.BILL_TYPE_PROCESSOR)))
		{

			//#2.賬單相關業務處理
			TradeResultProcessor resultProcessor = SpringContextHelper.getBean((String)resultMap.get(PingxxConstants.BILL_TYPE_PROCESSOR));
			returnModel = resultProcessor.processResult(resultMap);
		}else{
			throw new BusinessException("支付失敗，不予處理.");
		}
		return ResponseHelper.buildResponseModel(returnModel);
	}
}

/*
*$Log: av-env.bat,v $
*/