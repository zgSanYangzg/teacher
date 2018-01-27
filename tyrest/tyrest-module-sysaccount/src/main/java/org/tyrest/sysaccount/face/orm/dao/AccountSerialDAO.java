package org.tyrest.sysaccount.face.orm.dao;


import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.sysaccount.account.AccountBaseOperation;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.orm.entity.AccountSerial;
import org.tyrest.sysaccount.trade.AccountTradeType;

import java.util.List;

public interface AccountSerialDAO extends  GenericDAO<AccountSerial>
{
	
	
	/**
	 * 查询最后一条流水记录
	 * @param accountNo
	 * @return
	 * @throws Exception
	 */
	public AccountSerial  queryLastAccountSerial(String accountNo) throws Exception;
	
	
	/**
	 * 创建流水记录
	 * @param userId
	 * @param accountNo
	 * @param billNo
	 * @param amount
	 * @param accountTradeType
	 * @param bookkeeping
	 * @return
	 * @throws Exception
	 */
	public AccountSerial createAccountSerial(Long userId,String accountNo,String accountType,Long updateVersion,String billNo,int amount,AccountTradeType accountTradeType,AccountBaseOperation bookkeeping) throws Exception;


	/**
	 * 分页查询记账信息明细
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
	public List<AccountSerial> queryForPage(Long userId, AccountType accountType, AccountTradeType tradeType, AccountTradeType bookkeeping, Page page, String orderBy,
											String order) throws Exception;

}
