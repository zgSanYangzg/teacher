package org.tyrest.systrade.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systrade.channel.ChannelType;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TransactionsSerialServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TransactionsSerialServiceImpl.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface TransactionsSerialDAO extends GenericDAO<TransactionsSerial>
{
	
	/**
	 * 查询流水信息
	 * @param serialNo
	 * @return
	 * @throws Exception
	 */
	TransactionsSerial findBySerialNo(String serialNo) throws Exception;


	/**
	 * 生成交易流水記錄
	 * @param sequenceNBR
	 * @param bill
	 * @param channelType
	 * @return
	 * @throws Exception
	 */
	TransactionsSerial toSaveSerial(Long sequenceNBR, TransactionsBill bill, ChannelType channelType) throws Exception ;


}
