package org.tyrest.sysaccount.service;

import org.springframework.stereotype.Service;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.sysaccount.face.model.AccountTransferRecordModel;
import org.tyrest.sysaccount.face.orm.entity.AccountTransferRecord;
import org.tyrest.sysaccount.face.service.AccountTransferService;

/**
 * Created by Administrator on 2016/12/5.
 */
@Service(value="accountTransferService")
public class AccountTransferServiceImpl extends BaseServiceImpl<AccountTransferRecordModel,AccountTransferRecord> implements AccountTransferService {


}
