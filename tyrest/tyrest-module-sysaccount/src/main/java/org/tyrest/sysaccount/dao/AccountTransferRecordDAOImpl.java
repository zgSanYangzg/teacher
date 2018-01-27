package org.tyrest.sysaccount.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysaccount.face.orm.dao.AccountTransferRecordDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountTransferRecord;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AccountTransferRecordDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountTransferRecordDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="accountTransferRecordDAO")
public class AccountTransferRecordDAOImpl extends GenericDAOImpl<AccountTransferRecord> implements AccountTransferRecordDAO
{


    /**
     * 保存转账记录
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
    public AccountTransferRecord saveTransferRecord(Long sequenceNbr,Long userId,String billNo, String sourceAccountNo, String sourceAccountType,
                                                    String targetAccountNo,String targetAccountType,int amount,String postScript,String transferType) throws Exception {
        AccountTransferRecord record = new AccountTransferRecord();
        record.setBillNo(billNo);
        record.setSourceAccountNo(sourceAccountNo);
        record.setSourceAccountType(sourceAccountType);
        record.setTargetAccountNo(targetAccountNo);
        record.setTargetAccountType(targetAccountType);
        record.setTransferAmount(amount);
        record.setTransferPostscript(postScript);
        record.setTransferStatus(CoreConstants.COMMON_SUCCESS);
        record.setTransferTime(new Date());
        record.setTransferType(transferType);
        record.setUserId(userId);
        record.setRecDate(new Date());
        record.setRecUserId(RequestContext.getExeUserId());
        record.setRecStatus(CoreConstants.COMMON_ACTIVE);
        record.setSequenceNBR(sequenceNbr);
        this.insert(record);
        return record;

    }



    public AccountTransferRecord queryByCode(Long userId,String sourceAccountNo,String billNo,String transferType) throws Exception {
        Map<String,Object> params = new HashMap();
        StringBuilder sql = new StringBuilder();
        params.put("userId",userId);
        sql.append(" and USER_ID = :userId ");

        params.put("sourceAccountNo",sourceAccountNo);
        sql.append(" and SOURCE_ACCOUNT_NO = :sourceAccountNo ");

        params.put("billNo",billNo);
        sql.append(" and BILL_NO = :billNo ");

        params.put("transferType",transferType);
        sql.append(" and TRANSFER_TYPE = :transferType ");

        return this.findFirst(sql.toString(),params);
    }





}
