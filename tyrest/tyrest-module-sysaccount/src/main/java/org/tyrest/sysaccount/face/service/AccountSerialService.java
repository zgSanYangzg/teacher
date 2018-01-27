package org.tyrest.sysaccount.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.model.AccountInfoModel;
import org.tyrest.sysaccount.face.model.AccountSerialModel;
import org.tyrest.sysaccount.face.orm.entity.AccountInfo;
import org.tyrest.sysaccount.face.orm.entity.AccountSerial;
import org.tyrest.sysaccount.trade.AccountTradeType;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface AccountSerialService extends BaseService<AccountSerialModel,AccountSerial>{


    /**
     * 分页查询记账明细
     * @param userId
     * @param accountType
     * @param tradeType
     * @param bookkeeping
     * @param page
     * @param orderBy
     * @param order
     * @return
     * @throws Exception
     */
    public Page queryForPage(Long userId, AccountType accountType, AccountTradeType tradeType, AccountTradeType bookkeeping, Page page, String orderBy,
                             String order) throws Exception;

}
