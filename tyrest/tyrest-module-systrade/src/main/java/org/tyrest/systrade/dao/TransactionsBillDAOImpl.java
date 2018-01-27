package org.tyrest.systrade.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.orm.dao.TransactionsBillDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.trade.BillType;
import org.tyrest.systrade.trade.TradeType;

@Repository(value="transactionsBillDAO")
public class TransactionsBillDAOImpl extends GenericDAOImpl<TransactionsBill> implements TransactionsBillDAO {

	
	@Override
	public TransactionsBill findByBillNo(String billNo) throws Exception {
		StringBuilder  sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		sql.append(" AND BILL_NO = :BILL_NO");
		params.put("BILL_NO", billNo);
		return this.findFirst(sql.toString(), params);
	}


	public  TransactionsBill createBill(String agencyCode, String billNo, Long SequenceNbr,
										int tradeAmount, BillType billType, Long userId) throws Exception
	{
		TransactionsBill transactionsBill = new TransactionsBill();
		transactionsBill.setAgencyCode(agencyCode);
		transactionsBill.setBillNo(billNo);
		transactionsBill.setBillStatus(CoreConstants.COMMON_NO);
		transactionsBill.setCreateTime(new Date());
		transactionsBill.setRecDate(new Date());
		transactionsBill.setRecStatus(CoreConstants.COMMON_ACTIVE);
		transactionsBill.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?"0":RequestContext.getExeUserId());
		transactionsBill.setSequenceNBR(SequenceNbr);
		transactionsBill.setAmount(tradeAmount);
		transactionsBill.setBillType(billType.getBillType());
		transactionsBill.setUserId(userId);
		transactionsBill.setDescription(billType.getBillTypeName());
		this.insert(transactionsBill);
		return transactionsBill;
	}




}
