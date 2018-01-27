package org.tyrest.sysaccount.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.model.AccountCashoutRecordModel;
import org.tyrest.sysaccount.face.orm.entity.AccountCashoutRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface AccountCashoutService extends BaseService<AccountCashoutRecordModel,AccountCashoutRecord>{


    public AccountCashoutRecordModel createCashoutApply(Long userId, AccountType accountType, int outAmount, int poundage, String billNo, String TradeType ) throws Exception;


    public Page queryForPage(String userName, String applayType, String applyStatus, Page page, String orderby, String order) throws Exception;

    public Map<String,Object> balanceOfApply(Long userId) throws Exception;

    public AccountCashoutRecordModel queryByCode(String applyNo) throws Exception;

}
