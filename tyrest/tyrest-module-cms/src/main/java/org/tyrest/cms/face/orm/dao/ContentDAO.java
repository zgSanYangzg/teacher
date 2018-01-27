package org.tyrest.cms.face.orm.dao;

import org.tyrest.cms.face.enums.ContentType;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.cms.face.orm.entity.Content;

import java.beans.ExceptionListener;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  Lexing
 *  File: ContentDAO.java
 * 
 *  Lexing, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ContentDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-12-15 00:42:52		freeapis		Initial.
 *
 * </pre>
 */
public interface ContentDAO extends GenericDAO<Content>
{
    /**
     * 分页查询帖子／广告
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
	List<Content> findPostByPage(String contentType,String contentTitle,String contentStatus,String categoryCode,
                                 String isTop,String paid,String locationCode, String orderBy, String order, Page page) throws Exception;

    /**
     * 分页查询问答
     * @param contentTitle
     * @param contentStatus
     * @param categoryCode
     * @return
     * @throws Exception
     */
    List<Content> findQuestionByPage(String contentType,String contentTitle,String contentStatus,String categoryCode,String orderBy,String order, Page page) throws Exception;

    /**
     * 查询热门问题
     * @return
     * @throws Exception
     */
    List<Content> findHotQuestions(Page page) throws Exception;

    /**
     * 查询最新问题
     * @return
     * @throws Exception
     */
    List<Content> findLatestQuestions(Page page) throws Exception;

    /**
     * 查询奖励积分最多的问题
     * @return
     * @throws Exception
     */
    List<Content> findMostScoreQuestions(Page page) throws Exception;

    /**
     * 查询奖励金额最多的问题
     * @return
     * @throws Exception
     */
    List<Content> findMostMoneyQuestions(Page page) throws Exception;

    /**
     * 根据userId查询帖子
     * @param userId
     * @param page
     * @return
     */
    List<Content> findPostsByUserId(Long userId,String contentStatus, Page page) throws Exception;

    /**
     * 根据userId查询和用户相关的问答，包括用户提问的和用户回答的
     * @param userId
     * @param page
     * @return
     * @throws Exception
     */
    List<Content> findQuestionsByUserId(long userId, Page page) throws Exception;
}