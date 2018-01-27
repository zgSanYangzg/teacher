package org.tyrest.systrade.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsRecord;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsRecordServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsRecordServiceImpl.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface TransactionsRecordDAO extends GenericDAO<TransactionsRecord>
{
	/**
	 * 根据账单编号查找交易账单上信息
	 * @param billNo
	 * @return
	 * @throws Exception
	 */
	TransactionsRecord findByBillCode(String billNo) throws Exception;


	TransactionsRecord findBySerialNo(String serialNo) throws Exception;



	/**
	 * 創建交易記錄
	 * @param sequenceNBR
	 * @param serial
	 * @return
	 * @throws Exception
	 */
	 TransactionsRecord saveTransactionsRecord(Long sequenceNBR,TransactionsSerial serial ) throws Exception;
	
}
