package org.tyrest.sysaccount.face.orm.dao;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountCashoutRecord;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AccountCashoutRecordDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountCashoutRecordDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface AccountCashoutRecordDAO extends GenericDAO<AccountCashoutRecord>
{
    List<AccountCashoutRecord> queryForPage(String userName, String applayType, String applyStatus, Page page, String orderby, String order) throws Exception;

    AccountCashoutRecord queryByCode(String applyNo) throws Exception;

    Map<String,Object> balanceOfApply(Long userId) throws Exception;

}
