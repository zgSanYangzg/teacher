package org.tyrest.sysaccount.face.service;

import org.tyrest.core.mysql.BaseService;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.model.AccountInfoModel;
import org.tyrest.sysaccount.face.orm.entity.AccountInfo;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface AccountInfoService  extends BaseService<AccountInfoModel,AccountInfo>{


    //虚拟账户信息查
    public int queryAccountBalance(Long userId, AccountType accountType) throws Exception;
}
