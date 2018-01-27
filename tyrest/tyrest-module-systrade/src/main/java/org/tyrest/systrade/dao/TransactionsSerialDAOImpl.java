package org.tyrest.systrade.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.Sequence;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systrade.channel.ChannelType;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.face.orm.dao.TransactionsSerialDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;
import org.tyrest.systrade.trade.DefaultTradeStatus;
import org.tyrest.systrade.trade.TradeType;

@Repository(value="transactionsSerialDAO")
public class TransactionsSerialDAOImpl extends GenericDAOImpl<TransactionsSerial> implements TransactionsSerialDAO
{

	@Override
	public TransactionsSerial findBySerialNo(String serialNo) throws Exception {
		StringBuilder  sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sql.append(" AND SERIAL_NO = :SERIAL_NO");
		params.put("SERIAL_NO", serialNo);
		return this.findFirst(sql.toString(), params);
	}


	/**
	 *
	 * @param sequenceNBR
	 * @param bill
	 * @param channelType
	 * @return
	 * @throws Exception
	 */
	public  TransactionsSerial toSaveSerial(Long sequenceNBR, TransactionsBill bill, ChannelType channelType) throws Exception {
		TransactionsSerial serial = new TransactionsSerial();
		serial.setSequenceNBR(sequenceNBR);
		serial.setSerialNo(Sequence.generatorSerialNo());
		serial.setUserId(bill.getUserId());
		serial.setAgencyCode(bill.getAgencyCode());
		serial.setBillNo(bill.getBillNo());
		serial.setTradeAmount(bill.getAmount());
		serial.setClientIp(RequestContext.getRequestIP());
		serial.setPayMethod(channelType.getChannel());
		serial.setSyncFinishTime(new Date());
		serial.setSendTime(new Date());
		serial.setTradeStatus(DefaultTradeStatus.REQUESTED.name());
		serial.setTradeType(bill.getBillType());
		serial.setRecDate(new Date());
		serial.setRecStatus(CoreConstants.COMMON_ACTIVE);
		serial.setRecUserId(RequestContext.getExeUserId());
		this.insert(serial);
		return serial;
	}
}
