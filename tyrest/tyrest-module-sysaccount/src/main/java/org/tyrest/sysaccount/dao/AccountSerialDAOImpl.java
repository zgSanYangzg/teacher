package org.tyrest.sysaccount.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BadRequestException;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysaccount.account.AccountBaseOperation;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.face.orm.dao.AccountSerialDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountSerial;
import org.tyrest.sysaccount.trade.AccountTradeType;


@Repository(value="accountSerialDao")
public class AccountSerialDAOImpl extends GenericDAOImpl<AccountSerial> implements AccountSerialDAO
{

	@Autowired
	SequenceGenerator SequenceGenerator;

	private static final String QUERY_SQL = "SELECT * FROM account_serial p where 1=1 ";
	
	public AccountSerial  queryLastAccountSerial(String accountNo) throws Exception
	{
		AccountSerial accountSerial = null;
		
		StringBuilder sql = new StringBuilder(QUERY_SQL);
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(!ValidationUtil.isEmpty(accountNo))
		{
			params.put("accountNo", accountNo);
			sql.append(" and ACCOUNT_NO = :accountNo   order by SEQUENCE_NBR desc");
			accountSerial = this.findFirst(sql.toString(), params);
		}
		return accountSerial;
	}
	
	public AccountSerial createAccountSerial(Long userId,String accountNo,String accountType,Long updateVersion,String billNo,int amount,AccountTradeType accountTradeType,AccountBaseOperation bookkeeping) throws Exception
	{
		//#1.查询当前流水记录
		AccountSerial lastAccountSerial = this.queryLastAccountSerial(accountNo);
		
		int initialBalance 				= 0;		//起始金额
		if(!ValidationUtil.isEmpty(lastAccountSerial))
		{
			initialBalance 				= lastAccountSerial.getFinalBalance();
		}		
		int finalBalance 				= this.calaFinalBalance(bookkeeping, amount, initialBalance);//最终余额
		
		//#2.创建流水记录
		AccountSerial newAccountSerial 	= new AccountSerial();
		newAccountSerial.setSequenceNBR(SequenceGenerator.getNextValue());
		newAccountSerial.setAccountNo(accountNo);
		newAccountSerial.setBillNo(billNo);
		newAccountSerial.setChangeAmount(amount);
		newAccountSerial.setFinalBalance(finalBalance);
		newAccountSerial.setInitialPrefundedBalance(initialBalance);
		newAccountSerial.setOperateTime(new Date());
		newAccountSerial.setOperationType(accountTradeType.getAccountTradeType());
		newAccountSerial.setUserId(userId);
		newAccountSerial.setUpdateVersion(updateVersion);
		newAccountSerial.setRecDate(new Date());
		newAccountSerial.setRecStatus(CoreConstants.COMMON_ACTIVE);
		newAccountSerial.setRecUserId(RequestContext.getExeUserId());
		newAccountSerial.setAccountType(accountType);
		newAccountSerial.setBookkeeping(bookkeeping.name());
		this.insert(newAccountSerial);	
		return newAccountSerial;
	}
	
	
	/**
	 * 计算最终余额
	 * @param bookkeeping
	 * @param amount
	 * @param initialBalance
	 * @return
	 */
	private int calaFinalBalance(AccountBaseOperation bookkeeping,int amount,int initialBalance)
	{
		int finalBalance = 0;
		switch(bookkeeping)
		{
			case INCOME:
				finalBalance = initialBalance + amount;
				break;
			case SPEND :
				finalBalance = initialBalance - amount;
				if(finalBalance < 0){
					throw new BadRequestException("账户余额不足.");
				}
				break;
			default :
				throw new BadRequestException("账户操作类型有误.");
		}
		
		return finalBalance;
		
	}



	public List<AccountSerial> queryForPage(Long userId, AccountType accountType, AccountTradeType tradeType, AccountTradeType bookkeeping, Page page, String orderBy,
											String order) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		params.put("accountType",accountType.getAccountType());

		sql.append(" and USER_ID = :userId");
		sql.append(" and ACCOUNT_TYPE = :accountType");

		if(!ValidationUtil.isEmpty(tradeType))
		{
			params.put("tradeType",tradeType.getAccountTradeType());
			sql.append(" and OPERATION_TYPE = :tradeType");
		}

		if(!ValidationUtil.isEmpty(bookkeeping))
		{
			params.put("bookkeeping",bookkeeping.getAccountTradeType());
			sql.append(" and bookkeeping = :bookkeeping");
		}

		return this.paginate(sql.toString(), params,  page,  orderBy,  order);
	}

}
