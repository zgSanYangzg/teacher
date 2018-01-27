package org.tyrest.systrade.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systrade.face.orm.dao.TransactionsRecordDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository(value="transactionsRecordDAO")
public class TransactionsRecordDAOImpl extends GenericDAOImpl<TransactionsRecord> implements TransactionsRecordDAO {

	@Override
	public TransactionsRecord findByBillCode(String billNo) throws Exception {
		StringBuilder  sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sql.append(" AND BILL_NO = :BILL_NO");
		params.put("BILL_NO", billNo);
		return this.findFirst(sql.toString(), params);
	}


	@Override
	public TransactionsRecord findBySerialNo(String serialNo) throws Exception {
		StringBuilder  sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sql.append(" AND SERIAL_NO = :serialNo");
		params.put("serialNo", serialNo);
		return this.findFirst(sql.toString(), params);
	}


	/**
	 * 創建交易記錄
	 * @param sequenceNBR
	 * @param serial
	 * @return
	 * @throws Exception
	 */
	public  TransactionsRecord saveTransactionsRecord(Long sequenceNBR,TransactionsSerial serial ) throws Exception
	{
		TransactionsRecord record = this.findByBillCode(serial.getBillNo());
		if(ValidationUtil.isEmpty(record))
		{
			record = new TransactionsRecord();
			record.setAgencyCode(serial.getAgencyCode());
			record.setAgencyName(serial.getAgencyCode());
			record.setBillNo(serial.getBillNo());
			record.setFinishedTime(new Date());
			record.setPayMethod(serial.getPayMethod());
			record.setSerialNo(serial.getSerialNo());
			record.setTradeAmount(serial.getTradeAmount());
			record.setTradeType(serial.getTradeType());
			record.setUserId(serial.getUserId());
			record.setRecDate(new Date());
			record.setRecUserId(RequestContext.getExeUserId());
			record.setRecStatus(CoreConstants.COMMON_ACTIVE);
			record.setSequenceNBR(sequenceNBR);
			this.insert(record);
		}
		return record;
	}


}
