package org.tyrest.sysaccount.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.orm.entity.AccountTransferRecord;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AccountTransferRecordDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountTransferRecordDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface AccountTransferRecordDAO extends GenericDAO<AccountTransferRecord> {


    /**
     * 保存转账记录
     *
     * @param sequenceNbr
     * @param userId
     * @param billNo
     * @param sourceAccountNo
     * @param sourceAccountType
     * @param targetAccountNo
     * @param targetAccountType
     * @param postScript
     * @param transferType
     * @return
     * @throws Exception
     */
    AccountTransferRecord saveTransferRecord(Long sequenceNbr, Long userId, String billNo, String sourceAccountNo, String sourceAccountType,
                                                    String targetAccountNo, String targetAccountType, int amount,String postScript, String transferType) throws Exception;

    AccountTransferRecord queryByCode(Long userId,String accountNo,String billNo,String transferType) throws Exception;

}