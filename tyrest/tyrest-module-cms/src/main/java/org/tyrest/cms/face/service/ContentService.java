package org.tyrest.cms.face.service;

import org.tyrest.cms.face.enums.ContentStatus;
import org.tyrest.cms.face.enums.ContentType;
import org.tyrest.cms.face.enums.QuestionType;
import org.tyrest.cms.face.model.ContentModel;
import org.tyrest.cms.face.orm.entity.Content;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;

import java.util.Map;

/**
 * <pre>
 *
 *  Lexing
 *  File: ContentService.java
 *
 *  Lexing, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: ContentService.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-12-15 00:42:52		freeapis		Initial.
 *
 * </pre>
 */
public interface ContentService extends BaseService<ContentModel, Content> {
    /**
     * 创建帖子
     *
     * @param contentType
     * @param contentModel
     * @return
     * @throws Exception
     */
    ContentModel createContent(ContentType contentType, ContentModel contentModel) throws Exception;

    /**
     * 修改帖子状态
     *
     * @param contentType
     * @param id
     * @param contentStatus
     * @return
     * @throws Exception
     */
    void updateContentStatus(ContentType contentType, Long id, ContentStatus contentStatus) throws Exception;

    /**
     * 删除帖子
     *
     * @param contentType
     * @param id
     * @throws Exception
     */
    void deleteContent(ContentType contentType, Long id) throws Exception;

    /**
     * 根据ID获取帖子详情
     *
     * @param contentType
     * @param id
     * @return
     * @throws Exception
     */
    ContentModel getContentById(ContentType contentType, Long id) throws Exception;

    /**
     * 分页查询帖子／广告
     *
     * @param contentTitle
     * @param contentStatus
     * @param categoryCode
     * @param isTop
     * @param paid
     * @param locationCode
     * @param orderBy
     * @param order
     * @param page
     * @return
     * @throws Exception
     */
    Page getPostByPage(String contentType, String contentTitle, String contentStatus, String categoryCode,
                       String isTop, String paid, String locationCode, String orderBy, String order, Page page) throws Exception;

    Page getPostByPageForPortal(String contentType,String categoryCode,String locationCode, Page page) throws Exception;

    /**
     * 分页查询问答
     *
     * @param contentTitle
     * @param contentStatus
     * @param categoryCode
     * @return
     * @throws Exception
     */
    Page getQuestionByPage(String contentType, String contentTitle, String contentStatus, String categoryCode, String orderBy, String order, Page page) throws Exception;

    /**
     * 按照分组类型分页获取问答
     *
     * @param groupType
     * @param page
     * @return
     * @throws Exception
     */
    Page getQuestions(QuestionType questionType, Page page) throws Exception;

    /**
     * 查询我发布的帖子
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    Page getPostByUserIdWithPage(Long userId,String contentStatus, Page page) throws Exception;

    /**
     * 查询我的问答，我提问的/我回答的
     *
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    Page getMyQuestions(long userId, Page page) throws Exception;

    Map<String, Integer> paymentForTopCal(Long userId, int days, int scoreToDeduction) throws Exception;


    public Object createWithPayment(ContentModel contentDef,
                                    ContentType contentType,
                                    int days,             //置顶天数
                                    boolean isDeduction,  //是否用积分抵扣
                                    int postTopFee,       //置顶原来总价
                                    int deductionResult, //积分抵扣之后总价
                                    int scoreToDeduction //用来抵扣的积分数量
    ) throws Exception;


    /**
     * 分页获取被举报的帖子
     *
     * @param page
     * @return
     * @throws Exception
     */
    Page getReportContentByPage(Page page) throws Exception;

}