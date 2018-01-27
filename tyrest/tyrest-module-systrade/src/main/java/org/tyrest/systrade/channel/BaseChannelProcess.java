package org.tyrest.systrade.channel;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.face.orm.dao.TransactionsBillDAO;
import org.tyrest.systrade.face.orm.dao.TransactionsRecordDAO;
import org.tyrest.systrade.face.orm.dao.TransactionsSerialDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;
import org.tyrest.systrade.trade.TradeStatus;

@Component(value = "baseChannelProcess")
public abstract class BaseChannelProcess {
	
	@Autowired
	private TransactionsSerialDAO transactionsSerialDAO;
	
	@Autowired
	private TransactionsBillDAO transactionsBillDAO;
	
	@Autowired
	private TransactionsRecordDAO transactionsRecordDAO;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	/**
	 * 支付成功修改流水信息
	 * @param serialNo
	 * @param channelSerialNo
	 * @param tradStatus
	 * @param resultMessage
	 * @throws Exception 
	 */
	protected TransactionsSerialModel updateSerial2Success(String serialNo,String channelSerialNo,TradeStatus tradStatus,String resultMessage) throws Exception {
		TransactionsSerial transactionsSerial = transactionsSerialDAO.findBySerialNo(serialNo);
		transactionsSerial.setSerialNo(serialNo);
		transactionsSerial.setTradeStatus(tradStatus.getStatus());
		transactionsSerial.setFinishTime(new Date());
		transactionsSerial.setChannelSerialNo(channelSerialNo);
		transactionsSerial.setResultMessage(resultMessage);
		transactionsSerial.setRecDate(new Date());
		transactionsSerial.setRecStatus(CoreConstants.COMMON_ACTIVE);
		transactionsSerial.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
		transactionsSerialDAO.update(transactionsSerial);
		TransactionsSerialModel transactionsSerialModel = Bean.copyExistPropertis(transactionsSerial, new TransactionsSerialModel());
		return transactionsSerialModel;
	}
	
	/**
	 * 
	 * 支付完成后修改账单信息 
	 * @param billNo
	 * @param tradeStatus
	 * @throws Exception
	 */
	protected TransactionsBillModel updateBill2Success(String billNo,TradeStatus tradeStatus) throws Exception
	{
		TransactionsBill bill = this.transactionsBillDAO.findByBillNo(billNo);
		bill.setCheckoutTime(new Date());
		bill.setBillStatus(CoreConstants.COMMON_YES);
		bill.setRecDate(new Date());
		bill.setRecStatus(CoreConstants.COMMON_ACTIVE);
		bill.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
		transactionsBillDAO.update(bill);
		TransactionsBillModel transactionsBillModel = Bean.copyExistPropertis(bill, new TransactionsBillModel());
		return transactionsBillModel;
	}
	

	
}
