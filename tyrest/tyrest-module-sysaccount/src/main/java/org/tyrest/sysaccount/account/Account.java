package org.tyrest.sysaccount.account;


import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.sysaccount.face.orm.dao.AccountInfoDAO;
import org.tyrest.sysaccount.face.orm.dao.AccountSerialDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountInfo;
import org.tyrest.sysaccount.face.orm.entity.AccountSerial;
import org.tyrest.sysaccount.trade.AccountTradeType;
import org.tyrest.systemctl.face.orm.dao.DictionaryDAO;
import org.tyrest.systemctl.face.orm.dao.DictionaryEntryDAO;
import org.tyrest.systemctl.face.orm.entity.DictionaryEntry;
import org.tyrest.systemctl.face.service.DictionaryService;


/** 
 * 
 * <pre>
 *  Tyrest
 *  File: Account.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  虚拟账户操作类
 *  定义虚拟账户所有的操作
 *  所有方法不可在子类重写 
 * 
 *  Notes:
 *  $Id: Account.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
  public class Account {
	
	

	private static AccountInfoDAO accountInfoDao;

	private static AccountSerialDAO accountSerialDao;

	private static DictionaryEntryDAO dictionaryEntryDAO;
	

	static {
		accountInfoDao 		= (AccountInfoDAO)SpringContextHelper.getBean("accountInfoDao");
		accountSerialDao 	= (AccountSerialDAO)SpringContextHelper.getBean("accountSerialDao");
		dictionaryEntryDAO 	= (DictionaryEntryDAO)SpringContextHelper.getBean("dictionaryEntryDAO");
	}


	private Account()
	{

	}
	
	/**
	 * 账户对象
	 */
	private AccountInfo accountInfo;
	
	
	/**
	 * 初始化账户
	 * @throws Exception 
	 */
	protected static  final  AccountInfo  initAccountInfo(Long userId,AccountType accountType) throws Exception
	{
		return accountInfoDao.initAccountInfo(userId,accountType,createAccountNo());
	}
	
	/**
	 * 入账
	 * @param amount
	 * @return
	 * @throws Exception 
	 */
	public  final   boolean income(int amount,AccountTradeType accountTradeType,String billNo) throws Exception
	{
		return this.bookkeeping(amount, accountTradeType, billNo, AccountBaseOperation.INCOME);
	}
	
	
	
	/**
	 * 出账
	 * @param amount
	 * @return
	 * @throws Exception 
	 */
	public final  boolean spend(int amount,AccountTradeType accountTradeType,String billNo) throws Exception
	{	
		return this.bookkeeping(amount, accountTradeType, billNo, AccountBaseOperation.SPEND);
	}
	
	
	
	
	
	
	/**
	 * 锁定账户
	 * 锁定状态的账户不能进行任何交易，
	 * @return
	 * @throws Exception
	 */
	protected final  boolean lock() throws Exception
	{		
		return updateAccountStatus(AccountStatus.LOCKED, AccountStatus.NORMAL); 
	}
	
	
	
	/**
	 * 解锁账户
	 * 将账户从锁定状态变为正常状态
	 * @return
	 * @throws Exception
	 */
	protected final  boolean unlock() throws Exception
	{
		return updateAccountStatus(AccountStatus.NORMAL, AccountStatus.LOCKED); 
	}
	
	
	/**
	 * 失效，失效之后的账户不能在进行任何操作，也不可以再恢复到正常状态
	 * @return
	 * @throws Exception 
	 */
	protected final  boolean invalid() throws Exception
	{
		return updateAccountStatus(AccountStatus.INVALID, null); 
	}



	
	
	
	
	/**
	 * 记账
	 * @param amount	金额
	 * @param accountTradeType  账户操作类型
	 * @param billNo			账单编号
	 * @param bookkeeping		记账类型
	 * @return
	 * @throws Exception
	 */
	private final boolean bookkeeping(int amount,AccountTradeType accountTradeType,String billNo,AccountBaseOperation bookkeeping ) throws Exception
	{
		boolean returnFlag  = false;
		
		//#1.记录出账流水
		AccountSerial newAccountSerial = accountSerialDao.createAccountSerial( this.accountInfo.getUserId(), this.accountInfo.getAccountNo(),this.accountInfo.getAccountType(),this.accountInfo.getUpdateVersion(), billNo, amount, accountTradeType, bookkeeping);
		
		//#2.变更账户余额
		int updateResult = accountInfoDao.updateFinalBalance(this.accountInfo.getAccountNo(), newAccountSerial.getFinalBalance(), newAccountSerial.getChangeAmount(), this.accountInfo.getUpdateVersion(),bookkeeping);
		
		if(updateResult == 1)
		{
			returnFlag = true;
			//#3.刷新账户信息
			this.refresh(this.accountInfo.getAccountNo());
		}
		return returnFlag;
	}
	
	
	
	/**
	 * 变更账户状态
	 * @param newStatus
	 * @param oldStatus
	 * @return
	 * @throws Exception 
	 */
	private final boolean updateAccountStatus(AccountStatus newStatus,AccountStatus oldStatus) throws Exception
	{
		boolean returnFlag = false;		
		int updateResult = accountInfoDao.updateAccountStatus(accountInfo.getAccountNo(), newStatus, oldStatus, this.accountInfo.getUpdateVersion());
		
		if(updateResult == 1)
		{
			returnFlag = true;
			this.refresh(this.accountInfo.getAccountNo());
		}
		return returnFlag;
	}
	
	

	public final AccountInfo getAccountInfo() {
		return accountInfo;
	}



	private void refresh(String accountNo) throws Exception {

		this.accountInfo  = accountInfoDao.queryByCode(accountNo);
	}
	
	private static   final  Account newInstance(AccountInfo accountInfo)
	{
		Account account = new Account();
		account.accountInfo = accountInfo;
		return account;
	}



	
	protected final Account setAccountInfo(AccountInfo accountInfo) throws Exception
	{
		return newInstance(accountInfo);
	}


	public static final Account getAccountInstance(Long userId,AccountType accountType) throws Exception
	{
		return newInstance(accountInfoDao.queryByCode(userId, accountType));
	}

	public static  final Account getAccountInstance(String accountNo) throws Exception
	{
		return newInstance(accountInfoDao.queryByCode(accountNo));
	}


	private static String createAccountNo() throws Exception {

		String  returnNo = "";
		DictionaryEntry entity = dictionaryEntryDAO.findEntryByKey(CoreConstants.CODE_SUPER_ADMIN,   "VIRTUAL_ACCOUNT",   "CREATE_ACCOUNT_NO");
		if(!ValidationUtil.isEmpty(entity) && !ValidationUtil.isEmpty(entity.getEntryValue()))
		{
			returnNo 		= 	entity.getEntryValue();
			long currentNo  	= Long.parseLong(returnNo);
			long nextNo 		= currentNo + 1;
			entity.setEntryValue(String.valueOf(nextNo));
			dictionaryEntryDAO.updateWithCache(entity);
		}else{
			throw new BusinessException("虚拟账户编号配置有误.");
		}
		return returnNo;
	}

	
}
