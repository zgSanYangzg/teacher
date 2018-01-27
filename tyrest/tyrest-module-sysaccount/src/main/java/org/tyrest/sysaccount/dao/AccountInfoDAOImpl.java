package org.tyrest.sysaccount.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;

import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysaccount.account.AccountBaseOperation;
import org.tyrest.sysaccount.account.AccountStatus;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.face.orm.dao.AccountInfoDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountInfo;


/**
 * 
 * <pre>
 *  Tyrest
 *  File: AccountInfoDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AccountInfoDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="accountInfoDao")
public class AccountInfoDAOImpl  extends GenericDAOImpl<AccountInfo>  implements AccountInfoDAO
{

	
	@Autowired
	SequenceGenerator sequenceGenerator;
	
	
	/**更新最终余额*/
	private static final String UPDATE_FINAL_BALANCE = "UPDATE ACCOUNT_INFO p "
													+ " SET "
													+ "	p.BALANCE = :BALANCE ,"
													+ " p.CUMULATIVE_BALANCE = p.CUMULATIVE_BALANCE + :changeAmount, "
													+ " p.UPDATE_VERSION = :UPDATE_VERSION, "
													+ "	p.REC_DATE = :REC_DATE, "
													+ " p.REC_USER_ID = :REC_USER_ID "
													+ " where p.ACCOUNT_NO = :ACCOUNT_NO "
													+ " and p.ACCOUNT_STATUS = :ACCOUNT_STATUS "
													+ " and p.UPDATE_VERSION = :OLD_UPDATE_VERSION ";
	
	/**更新账户状态*/
	private static final String UPDATE_ACCOUNT_STATUS = "UPDATE ACCOUNT_INFO p"
													+ " SET p.ACCOUNT_STATUS = :ACCOUNT_STATUS"
													+ "	p.REC_DATE = :REC_DATE"
													+ " p.REC_USER_ID = :REC_USER_ID"
													+ " WHERE"
													+ "	p.ACCOUNT_NO = :ACCOUNT_NO"													
													+ " AND p.PAYMENT_PASSWORD = :PAYMENT_PASSWORD"
													+ " AND p.UPDATE_VERSION = :UPDATE_VERSION";
	
	
	
	public  AccountInfo queryByCode(Long userId,AccountType accountType) throws Exception
	{
		AccountInfo accountInfo = null;
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(!ValidationUtil.isEmpty(userId) && !ValidationUtil.isEmpty(accountType))
		{
			params.put("userId", userId);			
			sql.append("AND USER_ID = :userId ");
			params.put("accountType", accountType.getAccountType());
			sql.append(" AND ACCOUNT_TYPE = :accountType");
			accountInfo = this.findFirst(sql.toString(), params);
		}
		return accountInfo;
	}
	
	public  AccountInfo queryByCode(String accountNo) throws Exception
	{
		AccountInfo accountInfo = null;
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(!ValidationUtil.isEmpty(accountNo))
		{
			params.put("accountNo", accountNo);			
			sql.append(" AND ACCOUNT_NO = :accountNo ");
			accountInfo = this.findFirst(sql.toString(), params);
		}
		return accountInfo;
	}
	
	
	
	public int updateFinalBalance(String accountNo,int finalBalance,int changeAmount,Long oldUpdateVersion,AccountBaseOperation bookkeeping) throws Exception
	{
		StringBuilder sql = new StringBuilder(UPDATE_FINAL_BALANCE);		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("BALANCE", finalBalance);
		params.put("changeAmount", 0);
		if(AccountBaseOperation.INCOME.equals(bookkeeping)){
			params.put("changeAmount", changeAmount);
		}
		params.put("UPDATE_VERSION",sequenceGenerator.getNextValue());
		params.put("ACCOUNT_NO",accountNo);
		params.put("ACCOUNT_STATUS",AccountStatus.NORMAL.name());
		params.put("OLD_UPDATE_VERSION",oldUpdateVersion);		
		
		params.put("REC_DATE",new Date());	
		params.put("REC_USER_ID",RequestContext.getExeUserId());
		int flage = this.update(sql.toString(), params);
		if(flage<=0){
			throw new Exception("update_fail");
		}
		return flage;
	}
	
	
	
	public int updateAccountStatus(String accountNo,AccountStatus newStatus,AccountStatus oldStatus,Long oldUpdateVersion) throws Exception
	{
		StringBuilder sql = new StringBuilder(UPDATE_ACCOUNT_STATUS);		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ACCOUNT_STATUS", newStatus.name());
		params.put("ACCOUNT_NO", accountNo);		
		params.put("PAYMENT_PASSWORD", AccountConstants.DEFAULT_PAYMENT_PASSWORD);
		params.put("UPDATE_VERSION", oldUpdateVersion);
		params.put("REC_DATE",new Date());	
		params.put("REC_USER_ID",RequestContext.getExeUserId());
		
		if(!ValidationUtil.isEmpty(oldStatus))
		{
			sql.append(" AND p.ACCOUNT_STATUS = :OLD_ACCOUNT_STATUS ");
			params.put("OLD_ACCOUNT_STATUS", oldStatus.name());
		}
		return this.update(sql.toString(), params);
	}
	
	
	
	
	public AccountInfo initAccountInfo(Long userId ,AccountType accountType,String accountNo) throws Exception
	{		
		//TODO  生成账户编号
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountNo(accountNo);//账户编号生成
		accountInfo.setUserId(userId);
		accountInfo.setAccountType(accountType.getAccountType());
		accountInfo.setAccountStatus(AccountStatus.NORMAL.name());
		accountInfo.setAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
		accountInfo.setBalance(0);
		accountInfo.setCreateTime(new Date());
		accountInfo.setCumulativeBalance(0);
		accountInfo.setPaymentPassword(AccountConstants.DEFAULT_PAYMENT_PASSWORD);
		accountInfo.setRecStatus(CoreConstants.COMMON_ACTIVE);
		accountInfo.setRecUserId(String.valueOf(userId));
		accountInfo.setRecDate(new Date());
		accountInfo.setSequenceNBR(sequenceGenerator.getNextValue());
		accountInfo.setUpdateVersion(sequenceGenerator.getNextValue());		
		this.insert(accountInfo);		
		return accountInfo;
	}
	
	
	
	
}
