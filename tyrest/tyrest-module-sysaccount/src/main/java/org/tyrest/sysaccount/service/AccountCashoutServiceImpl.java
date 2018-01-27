package org.tyrest.sysaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.sysaccount.account.AccountManager;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.constants.AccountConstants;
import org.tyrest.sysaccount.face.model.AccountCashoutRecordModel;
import org.tyrest.sysaccount.face.orm.dao.AccountCashoutRecordDAO;
import org.tyrest.sysaccount.face.orm.entity.AccountCashoutRecord;
import org.tyrest.sysaccount.face.service.AccountCashoutService;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/5.
 */
@Service(value="accountCashoutService")
public class AccountCashoutServiceImpl extends BaseServiceImpl<AccountCashoutRecordModel,AccountCashoutRecord>
        implements AccountCashoutService
{


    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    AccountCashoutRecordDAO accountCashoutRecordDAO;



    //提现申请
    public AccountCashoutRecordModel createCashoutApply(Long userId, AccountType accountType, int outAmount, int poundage, String billNo, String TradeType ) throws Exception {
        //将当前账户的余额全部冻结
        AccountCashoutRecordModel recordModel   = null;
        AccountManager amSource                 = new AccountManager(userId,accountType);
        Map<String,Object> transferParams       = new HashMap<String,Object>();
        transferParams.put("billNo",billNo);
        transferParams.put("userId",userId);
        transferParams.put("amount",amSource.getAccountModel().getBalance());
        transferParams.put("postscript","提现申请");
        transferParams.put("transferType", DefaultAccountTradeType.CASHOUT.name());
        boolean  transferResult                 = amSource.executeTrade(DefaultAccountTradeType.FREEZE,transferParams);
        //添加申请记录
        if(transferResult)
        {
            AccountCashoutRecord record = new AccountCashoutRecord();
            record.setSequenceNBR(sequenceGenerator.getNextValue());
            record.setUserId(userId);
            record.setApplayNo(billNo);
            record.setApplayType(TradeType);
            record.setApplyStatus(AccountConstants.CASHOUT_STATUS_SUSPEND_AUTO);
            if(amSource.getAccountModel().getBalance() >= 3000*100)
            {
                record.setApplyStatus(AccountConstants.CASHOUT_STATUS_SUSPEND);
            }

            record.setOutAmount(outAmount);
            record.setTransferAccount(amSource.getAccountModel().getAccountNo());
            record.setApplayAmount(amSource.getAccountModel().getBalance());
            record.setPoundage(poundage);
            record.setRecDate(new Date());
            record.setRecUserId(RequestContext.getExeUserId());
            record.setRecStatus(CoreConstants.COMMON_ACTIVE);

            accountCashoutRecordDAO.insert(record);
            recordModel = Bean.toModel(record,new AccountCashoutRecordModel());
        }
        return recordModel;
    }


    /**
     * 分页查询提现申请记录
     * @param userName
     * @param applayType
     * @param applyStatus
     * @param page
     * @param orderby
     * @param order
     * @return
     * @throws Exception
     */
    public Page queryForPage(String userName, String applayType, String applyStatus, Page page, String orderby, String order) throws Exception {
        page.setList(Bean.toModels(this.accountCashoutRecordDAO.queryForPage( userName,  applayType,  applyStatus,  page,  orderby,  order),
                                   AccountCashoutRecordModel.class));
        return page;
    }




    public  Map<String,Object> balanceOfApply(Long userId) throws Exception {
        return accountCashoutRecordDAO.balanceOfApply(userId);
    }


    public AccountCashoutRecordModel queryByCode(String applyNo) throws Exception {
        AccountCashoutRecordModel returnModel = null;
        AccountCashoutRecord record = this.accountCashoutRecordDAO.queryByCode(applyNo);
        if(!ValidationUtil.isEmpty(record))
        {
            returnModel = Bean.toModel(record,new AccountCashoutRecordModel());
        }
        return returnModel;
    }



    //到期结算
    //审核通过体现申请
    //审核拒绝提现申请

}
