package org.tyrest.sysaccount.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysaccount.face.orm.dao.AccountRechargeRecordDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountRechargeRecord;


@Repository(value="accountRechargeRecordDao")
public class AccountRechargeRecordDAOImpl extends GenericDAOImpl< AccountRechargeRecord> implements AccountRechargeRecordDAO
{

	
	
}
