package org.tyrest.sysaccount.dao;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.face.orm.dao.AccountCashoutRecordDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountCashoutRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AccountCashoutRecordDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountCashoutRecordDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="accountCashoutRecordDAO")
public class AccountCashoutRecordDAOImpl extends GenericDAOImpl<AccountCashoutRecord> implements AccountCashoutRecordDAO
{

    private static final String QUERY_SQL = "select * from account_cashout_record p where 1=1 ";




    public AccountCashoutRecord queryByCode(String applyNo) throws Exception {
        StringBuilder sql = new StringBuilder(QUERY_SQL);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("applyNo",applyNo);
        sql.append(" and APPLAY_NO = :applyNo");
        return this.findFirst(sql.toString(),params);
    }



    public List<AccountCashoutRecord> queryForPage(String userName, String applayType, String applyStatus, Page page, String orderby, String order) throws Exception {
        StringBuilder sql = new StringBuilder(QUERY_SQL);
        Map<String,Object> params = new HashMap<String,Object>();

        if(!ValidationUtil.isEmpty(userName))
        {
            params.put("userName",userName);
            sql.append(" and p.user_name = :userName ");
        }

        if(!ValidationUtil.isEmpty(applayType))
        {
            params.put("applayType",applayType);
            sql.append(" and p.APPLAY_TYPE = :applayType ");
        }

        if(!ValidationUtil.isEmpty(applyStatus))
        {
            params.put("applyStatus",applyStatus);
            sql.append(" and p.APPLY_STATUS = :applyStatus ");
        }

        if(ValidationUtil.isEmpty(orderby))
        {
            orderby = "REC_DATE";
        }
        return this.paginate(sql.toString(),params,page,orderby,order);
    }








    /**
     * 体现
     * @param userId
     * @return
     * @throws Exception
     */
    public Map<String,Object> balanceOfApply(Long userId) throws Exception {
        Map<String,Object> resultMap = new HashMap();
        String sql = "SELECT" +
                " IFNULL(sum(c.APPLAY_AMOUNT),0) applayAmount," +
                " IFNULL(sum(c.OUT_AMOUNT),0) outAmount," +
                " IFNULL(sum(c.POUNDAGE),0) poundage" +
                " FROM" +
                " account_cashout_record c" +
                " WHERE" +
                " c.APPLY_STATUS in( "+"'"+AccountConstants.CASHOUT_STATUS_SUSPEND+"','"+AccountConstants.CASHOUT_STATUS_PENDING+"'"+")" +
                " AND c.USER_ID = :userId";
        Map<String,Object> params = new HashMap();
        //params.put("pending", "'"+AccountConstants.CASHOUT_STATUS_SUSPEND+"','"+AccountConstants.CASHOUT_STATUS_PENDING+"'");
        params.put("userId",userId);

        List<Map<String,Object>> resultList =  this.findMaps(sql, params);
        if(!ValidationUtil.isEmpty(resultList) && resultList.size() == 1)
        {
            resultMap = resultList.get(0);
        }
        return resultMap;
    }
}
