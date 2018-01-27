package org.tyrest.sysaccount.service;

import org.springframework.stereotype.Service;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.sysaccount.account.AccountManager;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.model.AccountInfoModel;
import org.tyrest.sysaccount.face.orm.entity.AccountInfo;
import org.tyrest.sysaccount.face.service.AccountInfoService;

/**
 * Created by Administrator on 2016/12/5.
 */
@Service(value="accountInfoService")
public class AccountInfoServiceImpl extends BaseServiceImpl<AccountInfoModel,AccountInfo> implements AccountInfoService {

    /**
     * 查询制定账户余额
     * @param userId
     * @param accountType
     * @return
     * @throws Exception
     */
    public int queryAccountBalance(Long userId, AccountType accountType) throws Exception {
        AccountManager am = new AccountManager(userId,accountType);
        AccountInfoModel accountModel =  am.getAccountModel();
        return accountModel.getBalance();
    }

}
