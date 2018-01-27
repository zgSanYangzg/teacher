package org.tyrest.sysaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.model.AccountSerialModel;
import org.tyrest.sysaccount.face.orm.dao.AccountSerialDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountSerial;
import org.tyrest.sysaccount.face.service.AccountSerialService;
import org.tyrest.sysaccount.trade.AccountTradeType;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */
@Service(value="accountSerialService")
public class AccountSerialServiceImpl extends BaseServiceImpl<AccountSerialModel,AccountSerial>  implements AccountSerialService{


    @Autowired
    AccountSerialDAO accountSerialDAO;
    //制定账户记账明细
    public Page queryForPage(Long userId, AccountType accountType, AccountTradeType tradeType, AccountTradeType bookkeeping,Page page, String orderBy,
                             String order) throws Exception {
        page.setList(Bean.toModels(accountSerialDAO.queryForPage( userId,  accountType,  tradeType,  bookkeeping,  page,  orderBy,
                order),AccountSerialModel.class));
        return page;
    }
}
