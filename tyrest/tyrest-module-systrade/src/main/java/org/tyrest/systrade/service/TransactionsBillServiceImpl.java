package org.tyrest.systrade.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.Sequence;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.orm.dao.TransactionsBillDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.face.service.TransactionsBillService;
import org.tyrest.systrade.trade.BillType;
import org.tyrest.systrade.trade.TradeType;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsBillServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsBillServiceImpl.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "transactionsBillService")
public class TransactionsBillServiceImpl extends BaseServiceImpl<TransactionsBillModel,TransactionsBill> implements TransactionsBillService
{


    @Autowired
    TransactionsBillDAO transactionsBillDAO;

    @Autowired
    SequenceGenerator sequenceGenerator;

    //创建账单
    public TransactionsBillModel createBill(String agencyCode, int tradeAmount, BillType billType, Long userId) throws Exception {

        TransactionsBill bill =   transactionsBillDAO.createBill( agencyCode, Sequence.generatorBillNo(),  sequenceGenerator.getNextValue(),
         tradeAmount,  billType,  userId);
        return Bean.toModel(bill,new TransactionsBillModel());
    }




}
