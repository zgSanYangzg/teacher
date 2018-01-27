package org.tyrest.sysaccount.service;

import org.springframework.stereotype.Service;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.sysaccount.face.model.AccountRechargeRecordModel;
import org.tyrest.sysaccount.face.orm.entity.AccountRechargeRecord;
import org.tyrest.sysaccount.face.service.AccountRechargeService;

/**
 * Created by Administrator on 2016/12/5.
 */
@Service(value="accountRechargeService")
public class  AccountRechargeServiceImpl extends BaseServiceImpl<AccountRechargeRecordModel,AccountRechargeRecord> implements AccountRechargeService{


}
