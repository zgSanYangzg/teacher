package org.tyrest.systrade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.Sequence;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.systrade.channel.ChannelProcessor;
import org.tyrest.systrade.channel.ChannelType;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.face.orm.dao.TransactionsBillDAO;
import org.tyrest.systrade.face.orm.dao.TransactionsRecordDAO;
import org.tyrest.systrade.face.orm.dao.TransactionsSerialDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;
import org.tyrest.systrade.trade.DefaultTradeStatus;
import org.tyrest.systrade.trade.TradeResultModel;
import org.tyrest.systrade.trade.TradeType;


@Component("tradeProcess")
public class TradeProcess {

	
	@Autowired
	TransactionsSerialDAO transactionsSerialDAO;


	@Autowired
	TransactionsBillDAO  transactionsBillDAO;

	@Autowired
	TransactionsRecordDAO transactionsRecordDAO;

	@Autowired
	SequenceGenerator sequenceGenerator;

	/**
	 * 发起交易
	 * @param tradeType
	 * @param channelType
	 * @throws Exception 
	 * @param billModel  待结账的账单
	 * @param tradeType	交易类型
	 * @param channelType 交易渠道
	 */
	public TradeResultModel sendTradeRequest(TransactionsBillModel billModel,TradeType tradeType,ChannelType channelType,Map<String,Object> extraParams) throws Exception
	{

		TradeResultModel resultModel 			= null;
		TransactionsBill billEntity 			= transactionsBillDAO.findByBillNo(billModel.getBillNo());
		Map<String,String>  matadata 			= (Map<String,String>)extraParams.get(PingxxConstants.METADATA);
		if(ValidationUtil.isEmpty(matadata))
		{
			matadata = new HashMap<>();
		}
		if(!ValidationUtil.isEmpty(billEntity))
		{
			//#1.生成流水单
			TransactionsSerial serial 			= transactionsSerialDAO.toSaveSerial( sequenceGenerator.getNextValue(), billEntity, channelType);
			TransactionsSerialModel serialModel = Bean.toModel(serial,new TransactionsSerialModel());

			//#2.附加參數
			matadata.put(PingxxConstants.BILLNO, serialModel.getBillNo());
			matadata.put(PingxxConstants.SERIALNO, serialModel.getSerialNo());
			matadata.put(PingxxConstants.USERID,String.valueOf(serialModel.getUserId()));
			matadata.put(PingxxConstants.AGENCYCODE, serialModel.getAgencyCode());
			extraParams.put(PingxxConstants.METADATA,matadata);

			//发起交易
			ChannelProcessor channelProcessor 	= SpringContextHelper.getBean(channelType.getChannelProcess());
			resultModel 	  				  	= channelProcessor.processTradeRequest(serialModel, tradeType, extraParams);
		}else{
			throw new BusinessException("賬單信息不存在.");
		}
		//返回交易結果
		return resultModel;
	}


	/**
	 * 交易结果处理
	 * @param serialNo	流水號
	 * @param channelSerialNo	交易渠道交易單編號
	 * @return
	 * @throws Exception
	 */
	public TransactionsRecordModel resultProcess(String  serialNo,String channelSerialNo) throws Exception
	{

		//#1.將流水置為成功
		TransactionsSerial serialEntity = transactionsSerialDAO.findBySerialNo(serialNo);
		serialEntity.setTradeStatus(DefaultTradeStatus.SUCCESS.getStatus());
		serialEntity.setFinishTime(new Date());
		serialEntity.setChannelSerialNo(channelSerialNo);
		serialEntity.setAsyncFinishTime(new Date());
		serialEntity.setRecUserId(RequestContext.getExeUserId());

		//#2.將賬單設置為已結賬
		TransactionsBill billEntity 	= transactionsBillDAO.findByBillNo(serialEntity.getBillNo());
		billEntity.setBillStatus(DefaultTradeStatus.SUCCESS.getStatus());
		billEntity.setCheckoutTime(new Date());
		billEntity.setRecDate(new Date());
		billEntity.setRecUserId(RequestContext.getExeUserId());

		//#生成交易記錄
		TransactionsRecord record 	    = transactionsRecordDAO.saveTransactionsRecord(sequenceGenerator.getNextValue(),serialEntity);
		return Bean.toModel(record,new TransactionsRecordModel());
	}
	



	
	
}

