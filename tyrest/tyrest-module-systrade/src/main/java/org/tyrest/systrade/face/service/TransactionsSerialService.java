package org.tyrest.systrade.face.service;


import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
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
public interface TransactionsSerialService extends BaseService<TransactionsSerialModel,TransactionsSerial>
{
    TransactionsSerialModel queryByCode(String serialNo) throws Exception;
}
