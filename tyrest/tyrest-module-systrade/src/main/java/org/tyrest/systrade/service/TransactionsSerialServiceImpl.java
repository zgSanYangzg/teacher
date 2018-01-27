package org.tyrest.systrade.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.systrade.face.model.TransactionsSerialModel;
import org.tyrest.systrade.face.orm.dao.TransactionsSerialDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsSerial;
import org.tyrest.systrade.face.service.TransactionsSerialService;

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
@Component(value = "transactionsSerialService")
public class TransactionsSerialServiceImpl extends BaseServiceImpl<TransactionsSerialModel,TransactionsSerial> implements TransactionsSerialService
{

    @Autowired
    TransactionsSerialDAO transactionsSerialDAO;

	public TransactionsSerialModel queryByCode(String serialNo) throws Exception {
        TransactionsSerial entity =transactionsSerialDAO.findBySerialNo(serialNo);
        return Bean.toModel(entity,new TransactionsSerialModel());
    }
}
