package org.tyrest.cms.service;

import com.pingplusplus.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.cms.constants.CmsConstants;
import org.tyrest.cms.face.enums.ContentStatus;
import org.tyrest.cms.face.enums.ContentType;
import org.tyrest.cms.face.enums.QuestionType;
import org.tyrest.cms.face.enums.RefType;
import org.tyrest.cms.face.model.ContentModel;
import org.tyrest.cms.face.orm.dao.ContentDAO;
import org.tyrest.cms.face.orm.entity.Content;
import org.tyrest.cms.face.service.ContentService;

import org.tyrest.cms.trade.PostingBillType;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.*;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.core.restevent.RestEventHandler;
import org.tyrest.publicuser.face.orm.dao.WechatInfoDAO;
import org.tyrest.publicuser.face.orm.entity.WechatInfo;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.orm.entity.Principal;
import org.tyrest.sysaccount.account.AccountManager;
import org.tyrest.sysaccount.account.DefaultAccountType;
import org.tyrest.systemctl.face.orm.dao.DictionaryEntryDAO;
import org.tyrest.systrade.TradeProcess;
import org.tyrest.systrade.channel.DefaultChannelType;
import org.tyrest.systrade.channel.pingxx.PingxxConstants;
import org.tyrest.systrade.face.model.TransactionsBillModel;
import org.tyrest.systrade.face.model.TransactionsRecordModel;
import org.tyrest.systrade.face.orm.dao.TransactionsBillDAO;
import org.tyrest.systrade.face.orm.entity.TransactionsBill;
import org.tyrest.systrade.trade.DefaultTradeType;
import org.tyrest.systrade.trade.TradeResultModel;
import org.tyrest.systrade.trade.TradeResultProcessor;

import java.util.*;


/**
 * <pre>
 *
 *  Lexing
 *  File: ContentServiceImpl.java
 *
 *  Lexing, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: ContentServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-12-15 00:42:52		freeapis		Initial.
 *
 * </pre>
 */
@Service(value = "contentService")
public class ContentServiceImpl extends BaseServiceImpl<ContentModel, Content>
        implements ContentService, TradeResultProcessor {

    @Autowired
    private ContentDAO contentDAO;

    @Autowired
    private PrincipalDAO principalDAO;




    @Autowired
    private DictionaryEntryDAO dictionaryEntryDAO;


    @Autowired
    TransactionsBillDAO transactionsBillDAO;

    @Autowired
    TradeProcess tradeProcess;


    @Autowired
    WechatInfoDAO wechatInfoDAO;



    @Override
    public ContentModel createContent(ContentType contentType, ContentModel contentModel) throws Exception {

        return null;
    }

    /**
     * 付费广告贴
     *
     * @param contentDef
     * @param contentDef
     * @param days       置顶天数
     * @throws Exception
     */
    public Object createWithPayment(ContentModel contentDef,
                                    ContentType contentType,//帖子类型、此方法只支持便民贴 和 广告贴的缴费操作
                                    int days,             //置顶天数
                                    boolean isDeduction,  //是否用积分抵扣
                                    int postTopFee,       //置顶原来总价
                                    int deductionResult, //积分抵扣之后总价
                                    int scoreToDeduction //用来抵扣的积分数量
    ) throws Exception {





        Long userId = Long.parseLong(RequestContext.getExeUserId());
        int amount = 0;
        if (ContentType.ad.equals(contentType)) {       //广告贴需要添加广告费用
            //前端未有广告费用的直接体现，此处按类型加上广告费用可能会与前端有所差异，待确认.....
            // ......若将置顶费用和广告费用分开收费则方便控制佣金结算过程
            String adFeeStr = dictionaryEntryDAO.findValueByKey(CoreConstants.CODE_SUPER_ADMIN, "FINANCE", "AD_FEE");
            int adFee = (int) (Double.parseDouble(adFeeStr) * 100);//广告单价（分）
            amount = adFee;
        }

        //如果置顶使用积分抵扣
        if (isDeduction && days > 0 && scoreToDeduction >= 0) {
            Map<String, Integer> resultMap = this.paymentForTopCal(userId, days, scoreToDeduction);
            if (postTopFee == resultMap.get("postTopFee").intValue()
                    && deductionResult == resultMap.get("deductionResult").intValue()) {
                amount = amount + deductionResult;
            } else {
                throw new BusinessException("抵扣信息有误,请重试.");
            }
        }

        contentDef.setPayAmount(amount);
        ContentModel returnContent = this.createContent(contentType,contentDef);


        return this.postpayment(amount, userId, contentType, returnContent.getSequenceNBR(), days > 0 ? days : 0);
    }



    @Override
    public Page getReportContentByPage(Page page) throws Exception {

        return null;
    }


    private Object postpayment(int amount, Long userId, ContentType contentType, Long contentSeq, int days) throws Exception {
        Object returnObj = null;
        WechatInfo wechatInfo = wechatInfoDAO.findByUserId(userId);
        // #2.生成續費賬單
        TransactionsBill bill = transactionsBillDAO.createBill(CoreConstants.CODE_SUPER_ADMIN, Sequence.generatorBillNo(),
                sequenceGenerator.getNextValue(), amount, PostingBillType.POSTING_PAYMENT, userId);

        // #3.配置附加參數
        Map<String, Object> extraParams = new HashMap<>();//封裝
        Map<String, String> matadata = new HashMap<>();//系統附加參數
        Map<String, Object> channelExtra = new HashMap<>();//交易渠道附加參數

        extraParams.put(PingxxConstants.SUBJECT, PostingBillType.POSTING_PAYMENT.getBillTypeName());
        extraParams.put(PingxxConstants.BODY, PostingBillType.POSTING_PAYMENT.getBillTypeName());
        matadata.put(PingxxConstants.BILL_TYPE_PROCESSOR, PostingBillType.POSTING_PAYMENT.getResultProccesor());
        matadata.put(PingxxConstants.BILL_TYPE, PostingBillType.POSTING_PAYMENT.getBillType());
        matadata.put(CmsConstants.CONTENT_TYPE, contentType.name());
        matadata.put(CmsConstants.CONTENT_SEQ, String.valueOf(contentSeq));
        matadata.put(CmsConstants.CONTENT_TOP_DAYS, String.valueOf(days));
        channelExtra.put("open_id", wechatInfo.getOpenId());
        extraParams.put(PingxxConstants.EXTRA, channelExtra);
        extraParams.put(PingxxConstants.METADATA, matadata);
        // #4.發起交易
        TransactionsBillModel billModel = Bean.toModel(bill, new TransactionsBillModel());
        TradeResultModel resultModel = tradeProcess.sendTradeRequest(billModel, DefaultTradeType.PAYMENT, DefaultChannelType.PINGXX_WX_PUB, extraParams);

        if (!ValidationUtil.isEmpty(resultModel.getResult()) && resultModel.isCalledSuccess()) {
            // #5.封裝微信小程序所需的交易參數
            Charge charge = (Charge) resultModel.getResult();
            returnObj = charge.getCredential().get(DefaultChannelType.PINGXX_WX_PUB.getChannel());
        } else {
            throw new BusinessException("交易結果信息缺失,或者交易結果失敗.");
        }
        //返回交易凭证
        return returnObj;
    }


    @Override
    public void updateContentStatus(ContentType contentType, Long id, ContentStatus contentStatus) throws Exception {

    }

    @Override
    public void deleteContent(ContentType contentType, Long id) throws Exception {

    }

    @Override
    public ContentModel getContentById(ContentType contentType, Long id) throws Exception {

        return null;
    }

    @Override
    public Page getPostByPage(String contentType, String contentTitle, String contentStatus, String categoryCode,
                              String isTop, String paid, String locationCode, String orderBy, String order, Page page) throws Exception {
        List<Content> contents = contentDAO.findPostByPage(contentType, contentTitle,
                contentStatus, categoryCode, isTop, paid, locationCode, orderBy, order, page);
        page.setList(contents);
        return page;
    }

    @Override
    public Page getPostByPageForPortal(String contentType,String categoryCode,String locationCode, Page page) throws Exception{
        ContentType currentContentType = ContentType.getByName(contentType);
        if(ValidationUtil.isEmpty(currentContentType)) return page;
        //portal端的缓存按照contentType + categoryCode + locationCode缓存
        //缓存排序按照置顶 + 创建时间排
        long start = page.getPageStartRow();
        long end = start + page.getPageRecorders() - 1;
        Set cachedPosts = Redis.zget(start,end,true,currentContentType.name(),categoryCode,locationCode);
        List<Content> result = new ArrayList<>();
        if(ValidationUtil.isEmpty(cachedPosts)){
            Page cachedPage = new Page(500,0);
            List<Content> dbPosts = contentDAO.findPostByPage(contentType, null,
                    ContentStatus.published.name(), categoryCode, null, null, locationCode, "createTime", "desc", cachedPage);
            for(Content post : dbPosts){
                Redis.zadd(post,
                        //置顶的帖子始终放在最前面
                        (CoreConstants.COMMON_Y.equals(post.getIsTop()) ? 10 : 1) * post.getCreateTime().getTime(),
                        currentContentType.name(),categoryCode,locationCode);
            }
            cachedPosts = Redis.zget(start,end,true,currentContentType.name(),categoryCode,locationCode);
        }
        if(!ValidationUtil.isEmpty(cachedPosts)){
            for(Object e : cachedPosts){
                result.add((Content)e);
            }
            page.setTotalRows(Redis.zcard(currentContentType.name(),categoryCode,locationCode).intValue());
            page.setList(result);
        }
        return page;
    }

    @Override
    public Page getQuestionByPage(String contentType,String contentTitle, String contentStatus, String categoryCode,
                                  String orderBy, String order, Page page) throws Exception {
        List<Content> contents = contentDAO.findQuestionByPage(contentType,contentTitle, contentStatus, categoryCode, orderBy, order, page);
        page.setList(contents);
        return page;
    }


    @Override
    public Page getQuestions(QuestionType questionType, Page page) throws Exception {
        long start = page.getPageStartRow();
        long end = start + page.getPageRecorders() - 1;
        Set cachedQuestions = Redis.zget(start,end,true,ContentType.question.name(),questionType.name());
        List<Content> result = new ArrayList<>();
        if(ValidationUtil.isEmpty(cachedQuestions)){
            //最多缓存前500条数据，500以后的数据无论从时间特性，还是从参考价值特性考虑，都没有太大的意义。
            Page cachedPage = new Page(500,0);
            List<Content> dbQuestions = null;
            switch (questionType) {
                case hottest:
                    dbQuestions = contentDAO.findHotQuestions(cachedPage);
                    for(Content question : dbQuestions){
                        Redis.zadd(question,question.getCommentCount(),ContentType.question.name(),questionType.name());
                    }
                    break;
                case latest:
                    dbQuestions = contentDAO.findLatestQuestions(cachedPage);
                    for(Content question : dbQuestions){
                        Redis.zadd(question,question.getCreateTime().getTime(),ContentType.question.name(),questionType.name());
                    }
                    break;
                case mostScore:
                    dbQuestions = contentDAO.findMostScoreQuestions(cachedPage);
                    for(Content question : dbQuestions){
                        Redis.zadd(question,question.getRewardAmount(),ContentType.question.name(),questionType.name());
                    }
                    break;
                case mostMoney:
                    dbQuestions = contentDAO.findMostMoneyQuestions(cachedPage);
                    for(Content question : dbQuestions){
                        Redis.zadd(question,question.getRewardAmount(),ContentType.question.name(),questionType.name());
                    }
                    break;
                default:
                    throw new BusinessException("groupType不合法,hottest|latest|mostScore|mostMoney");
            }
            cachedQuestions = Redis.zget(start,end,true,ContentType.question.name(),questionType.name());
        }
        if(!ValidationUtil.isEmpty(cachedQuestions)){
            for(Object e : cachedQuestions){
                result.add((Content)e);
            }
            page.setTotalRows(Redis.zcard(ContentType.question.name(),questionType.name()).intValue());
            page.setList(result);
        }
        return page;
    }

    @Override
    public Page getPostByUserIdWithPage(Long userId,String contentStatus, Page page) throws Exception {
        List<Content> contents = contentDAO.findPostsByUserId(userId,contentStatus, page);
        page.setList(contents);
        return page;
    }

    @Override
    public Page getMyQuestions(long userId, Page page) throws Exception {
        List<Content> contents = contentDAO.findQuestionsByUserId(userId, page);
        page.setList(contents);
        return page;
    }


    public Map<String, Integer> paymentForTopCal(Long userId, int days, int scoreToDeduction) throws Exception {

        if (days <= 0) days = 1;
        Map<String, Integer> returnMap = new HashMap();
        String scoreDeductionRatioStr   //积分抵扣比例限制（%）
                = dictionaryEntryDAO.findValueByKey(CoreConstants.CODE_SUPER_ADMIN, "FINANCE", "SCORE_DEDUCTION_RATIO");
        int scoreDeductionRatio         //积分抵扣比例限制（%）
                = Integer.parseInt(scoreDeductionRatioStr);
        String postTopFeeStr           //帖子置顶费用（元）
                = dictionaryEntryDAO.findValueByKey(CoreConstants.CODE_SUPER_ADMIN, "FINANCE", "POST_TOP_FEE");
        int postTopFee                //帖子置顶费用（分）
                = (int) (Double.parseDouble(postTopFeeStr) * 100 * days);//置顶费用

        String scoreExchangeCny        //每积分兑换人民币额度（元）
                = dictionaryEntryDAO.findValueByKey(CoreConstants.CODE_SUPER_ADMIN, "FINANCE", "SCORE_EXCHANGE_CNY");
        int scoreExchangeCnyFee          //每积分兑换人民币额度（分）
                = (int) (Double.parseDouble(scoreExchangeCny) * 100);//一积分等于xx人民币（分）
        AccountManager amScore = new AccountManager(userId, DefaultAccountType.SCORE);

        int scoreDeductionFeeAble = scoreDeductionRatio * postTopFee / 100;//(分)//可被积分抵扣的金额
        int scoreToDeductionAble = scoreDeductionFeeAble / scoreExchangeCnyFee;   //用于抵扣资金的积分数(个)

        if (scoreToDeductionAble > amScore.getAccountModel().getBalance()) {
            //用于抵扣资金的积分数大于积分账户余额时候 ，则可用于抵扣的积分为积分账户余额
            scoreToDeductionAble = amScore.getAccountModel().getBalance();
        }
        scoreDeductionFeeAble = scoreToDeductionAble * scoreExchangeCnyFee;
        if (scoreToDeduction > scoreToDeductionAble || scoreToDeduction < 0) {
            throw new BadRequestException("使用积分数量有误.");
        }
        int scoreDeductionFee = scoreToDeduction * scoreExchangeCnyFee;
        returnMap.put("postTopFee", postTopFee);//帖子置顶价格（分）
        returnMap.put("scoreBanlance", amScore.getAccountModel().getBalance());//积分余额(个)
        returnMap.put("scoreToDeduction", scoreToDeduction);//用于抵扣资金的积分数
        returnMap.put("scoreDeductionFee", scoreDeductionFee);//可用积分抵扣的金额（分）
        returnMap.put("scoreDeductionRatio", scoreDeductionRatio);//抵扣比例（%）
        returnMap.put("deductionResult", postTopFee - scoreDeductionFee);//抵扣之后的金额（分）
        returnMap.put("scoreExchangeCny", scoreExchangeCnyFee);//抵扣比例（分）

        return returnMap;
    }


    public TransactionsRecordModel processResult(Map<String, Object> infoMap) throws Exception {


       /* returnMap.put(PingxxConstants.SERIALNO,charge.getMetadata().get(PingxxConstants.SERIALNO));
        returnMap.put(PingxxConstants.CHANNEL_SERIAL_NO,charge.getTransactionNo());
        returnMap.put(PingxxConstants.METADATA,charge.getMetadata());
        returnMap.put(PingxxConstants.BILL_TYPE,charge.getMetadata().get(PingxxConstants.BILL_TYPE));*/

        Map<String, String> matadata = (Map<String, String>) infoMap.get(PingxxConstants.METADATA);
        String serialNo = (String) infoMap.get(PingxxConstants.SERIALNO);
        String channelSerialNo = (String) infoMap.get(PingxxConstants.CHANNEL_SERIAL_NO);

        Long contentSeq = Long.parseLong(matadata.get(CmsConstants.CONTENT_SEQ));
        int Days = Integer.parseInt(matadata.get(CmsConstants.CONTENT_TOP_DAYS));
        ContentType contentType = ContentType.valueOf(matadata.get(CmsConstants.CONTENT_TYPE));
        Long userId = Long.parseLong(matadata.get(PingxxConstants.USERID));


        return tradeProcess.resultProcess(serialNo, channelSerialNo);
    }








}