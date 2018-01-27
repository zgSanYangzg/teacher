package org.tyrest.resource.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.sysaccount.account.AccountBaseOperation;
import org.tyrest.sysaccount.account.AccountType;
import org.tyrest.sysaccount.account.DefaultAccountType;
import org.tyrest.sysaccount.face.service.AccountInfoService;
import org.tyrest.sysaccount.face.service.AccountSerialService;
import org.tyrest.sysaccount.trade.AccountTradeType;
import org.tyrest.sysaccount.trade.DefaultAccountTradeType;

@RestController
@RequestMapping(value = "/1/account")
@TyrestResource(module = "account",value = "AccountResourcesV1", description = "虚拟账户")

public class AccountResourcesV1 extends BaseResources
{
    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    AccountSerialService accountSerialService;

    @TyrstOperation(name = "accountBalance",ApiLevel = APILevel.PUBLIC, description = "获取账户余额")
    @RequestMapping(value = "/accountType/{accountType}/balance", method = RequestMethod.GET)
    public ResponseModel<Integer> accountBalance(@PathVariable(value = "accountType") String accountTypeStr) throws Exception
    {


        String userId = RequestContext.getExeUserId();

        AccountType accountType = DefaultAccountType.valueOf(accountTypeStr);
        if(ValidationUtil.isEmpty(accountType))
        {
            throw new DataValidateException("账户类型有误.");
        }
        return ResponseHelper.buildResponseModel(accountInfoService.queryAccountBalance(Long.parseLong(userId),accountType));
    }

    @TyrstOperation(name = "accountBalance",ApiLevel = APILevel.PUBLIC, description = "指定账户记账明细")
    @RequestMapping(value = "/accountType/{accountType}/bookkeeping", method = RequestMethod.GET)
        public ResponseModel<Page> bookingDetail(@PathVariable( value = "accountType") String accountTypeStr,
                                                 @RequestParam(value = "bookkeeping", required = false) String bookkeepingStr,
                                                 @RequestParam(value = "tradeType", required = false) String tradeTypeStr,
                                                 @RequestParam(value = ParamConstants.SIDX, required = false) String orderby,
                                                 @RequestParam(value = ParamConstants.SORT, required = false) String order,
                                                 @RequestParam(value = ParamConstants.OFFSET) int start,
                                                 @RequestParam(value = ParamConstants.LENGTH) int length) throws Exception
    {
        String userId = RequestContext.getExeUserId();
        AccountType accountType = null;
        if(!ValidationUtil.isEmpty(accountTypeStr))
            accountType = DefaultAccountType.valueOf(accountTypeStr);
        AccountBaseOperation bookkeeping = null;
        if(!ValidationUtil.isEmpty(bookkeepingStr))
            bookkeeping = AccountBaseOperation.valueOf(bookkeepingStr);
        AccountTradeType tradeType = null;
        if(!ValidationUtil.isEmpty(tradeTypeStr))
            tradeType  = DefaultAccountTradeType.valueOf(tradeTypeStr);
        if(ValidationUtil.isEmpty(accountType))
        {
            throw new DataValidateException("账户类型有误.");
        }
        return ResponseHelper.buildResponseModel(accountSerialService.queryForPage(Long.parseLong(userId), accountType, tradeType, bookkeeping,
                                                new Page(length,start),  orderby,order));
    }




}
/*
 * $Log: av-env.bat,v $
 */