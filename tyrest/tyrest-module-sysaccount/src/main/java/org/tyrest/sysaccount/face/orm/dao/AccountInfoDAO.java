package org.tyrest.sysaccount.face.orm.dao;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysaccount.account.AccountBaseOperation;
import org.tyrest.sysaccount.account.AccountStatus;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.orm.entity.AccountInfo;

public interface AccountInfoDAO extends  GenericDAO<AccountInfo>
{
	public  AccountInfo queryByCode(Long userId,AccountType accountType) throws Exception;
	public  AccountInfo queryByCode(String accountNo) throws Exception;
	
	/**
	 * 更新最终余额
	 * @param accountNo
	 * @param finalBalance
	 * @param changeAmount
	 * @param oldUpdateVersion
	 * @param bookkeeping 账户操作类型
	 * @return
	 * @throws Exception
	 */
	public int updateFinalBalance(String accountNo,int finalBalance,int changeAmount,Long oldUpdateVersion,AccountBaseOperation bookkeeping) throws Exception;
	
	
	/**
	 * 变更账户状态
	 * @param accountNo	账户编号
	 * @param newStatus	新状态
	 * @param oldStatus	原状态
	 * @param oldUpdateVersion 数据版本
	 * @return
	 * @throws Exception
	 */
	public int updateAccountStatus(String accountNo,AccountStatus newStatus,AccountStatus oldStatus,Long oldUpdateVersion) throws Exception;

	
	
	/**
	 * 
	 * @param accountInfo 新的账户对象
	 * @return
	 * @throws Exception
	 */
	public AccountInfo initAccountInfo(Long userId,AccountType accountType,String accountNo) throws Exception;
	
}
