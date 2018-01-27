package org.tyrest.cms.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.cms.face.enums.ContentType;
import org.tyrest.cms.face.enums.RewardType;
import org.tyrest.cms.face.orm.dao.ContentDAO;
import org.tyrest.cms.face.orm.entity.Content;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  Lexing
 *  File: ContentDAOImpl.java
 * 
 *  Lexing, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ContentDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-12-15 00:42:52		freeapis		Initial.
 *
 * </pre>
 */
@Repository(value="contentDAO")
public class ContentDAOImpl extends GenericDAOImpl<Content> implements ContentDAO
{

    @Override
    public List<Content> findPostByPage(String contentType,String contentTitle, String contentStatus, String categoryCode,
                                        String isTop, String paid, String locationCode, String orderBy, String order, Page page) throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String,Object> params = new HashMap<String,Object>();

        if(!ValidationUtil.isEmpty(contentType)){
            sql.append( " AND BUSINESS_TYPE = :BUSINESS_TYPE");
            params.put("BUSINESS_TYPE", contentType);
        }
        if(!ValidationUtil.isEmpty(contentTitle)){
            sql.append(" AND CONTENT_TITLE LIKE '%"+ contentTitle +"%'");
        }
        if(!ValidationUtil.isEmpty(contentStatus)){
            sql.append(" AND CONTENT_STATUS = :CONTENT_STATUS");
            params.put("CONTENT_STATUS",contentStatus);
        }
        if(!ValidationUtil.isEmpty(categoryCode)){
            sql.append(" AND CATEGORY_CODE = :CATEGORY_CODE");
            params.put("CATEGORY_CODE",categoryCode);
        }
        if(!ValidationUtil.isEmpty(isTop)){
            sql.append(" AND IS_TOP = :IS_TOP ");
            params.put("IS_TOP",isTop);
        }
        if(!ValidationUtil.isEmpty(paid)){
            sql.append(" AND PAID = :PAID ");
            params.put("PAID",paid);
        }
        if(!ValidationUtil.isEmpty(locationCode)){
            sql.append(" AND LOCATION_CODE = :LOCATION_CODE ");
            params.put("LOCATION_CODE",locationCode);
        }
        return this.paginate(sql.toString(),params,page,orderBy,order);
    }

    @Override
    public List<Content> findQuestionByPage(String contentType,String contentTitle, String contentStatus,
                                            String categoryCode,String orderBy,String order, Page page) throws Exception {
        StringBuilder sql = new StringBuilder(" AND BUSINESS_TYPE = :BUSINESS_TYPE");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("BUSINESS_TYPE", contentType);
            if(!ValidationUtil.isEmpty(contentTitle)){
            sql.append(" AND CONTENT_TITLE LIKE '%"+ contentTitle +"%'");
        }
        if(!ValidationUtil.isEmpty(contentStatus) && "noanswered".equals(contentStatus)){
            sql.append(" AND BEST_ANSWER_ID is null ");
            //params.put("CONTENT_STATUS",contentStatus);
        }

        if(!ValidationUtil.isEmpty(contentStatus) && "answered".equals(contentStatus)){
            sql.append(" AND BEST_ANSWER_ID is not null ");
            //params.put("CONTENT_STATUS",contentStatus);
        }
        if(!ValidationUtil.isEmpty(categoryCode)){
            sql.append(" AND CATEGORY_CODE = :CATEGORY_CODE");
            params.put("CATEGORY_CODE",categoryCode);
        }
        return this.paginate(sql.toString(),params,page,orderBy,order);
    }

    @Override
    public List<Content> findHotQuestions(Page page) throws Exception {
        String sql = "SELECT " +
                " * " +
                " FROM " +
                " wbsj_content a " +
                " WHERE " +
                " a.BUSINESS_TYPE = :CONTENT_TYPE " +
                " ORDER BY " +
                " a.COMMENT_COUNT DESC";
        Map<String,Object> params = new HashMap<>();
        params.put("CONTENT_TYPE",ContentType.question.name());
        return this.paginate(sql.toString(),params,page,null,null);
    }

    @Override
    public List<Content> findLatestQuestions(Page page) throws Exception {
        String sql = "SELECT " +
                " * " +
                " FROM " +
                " wbsj_content a " +
                " WHERE " +
                " a.BUSINESS_TYPE = :CONTENT_TYPE " +
                " ORDER BY " +
                " a.CREATE_TIME DESC";
        Map<String,Object> params = new HashMap<>();
        params.put("CONTENT_TYPE",ContentType.question.name());
        return this.paginate(sql.toString(),params,page,null,null);
    }

    @Override
    public List<Content> findMostScoreQuestions(Page page) throws Exception {
        String sql = "SELECT" +
                "	*" +
                "FROM" +
                "	wbsj_content a " +
                " WHERE" +
                "	a.BUSINESS_TYPE = :CONTENT_TYPE " +
                " AND a.REWARD_TYPE = :REWARD_TYPE " +
                " ORDER BY" +
                "	a.REWARD_AMOUNT DESC";
        Map<String,Object> params = new HashMap<>();
        params.put("CONTENT_TYPE",ContentType.question.name());
        params.put("REWARD_TYPE", RewardType.score.name());
        return this.paginate(sql.toString(),params,page,null,null);
    }

    @Override
    public List<Content> findMostMoneyQuestions(Page page) throws Exception {
        String sql = "SELECT" +
                "	*" +
                "FROM" +
                "	wbsj_content a" +
                " WHERE" +
                "	a.BUSINESS_TYPE = :CONTENT_TYPE " +
                " AND a.REWARD_TYPE = :REWARD_TYPE " +
                " ORDER BY" +
                "	a.REWARD_AMOUNT DESC";
        Map<String,Object> params = new HashMap<>();
        params.put("CONTENT_TYPE",ContentType.question.name());
        params.put("REWARD_TYPE", RewardType.money.name());
        return this.paginate(sql.toString(),params,page,null,null);
    }

    @Override
    public List<Content> findPostsByUserId(Long userId,String contentStatus, Page page) throws Exception {
        String sql = "SELECT * FROM WBSJ_CONTENT A WHERE A.BUSINESS_TYPE = :CONTENT_TYPE AND A.USER_ID = :USER_ID";
        Map<String,Object> params = new HashMap<>();
        params.put("CONTENT_TYPE",ContentType.commonPost.name());
        params.put("USER_ID",userId);

        if(!ValidationUtil.isEmpty(contentStatus))
        {
            sql +=" and CONTENT_STATUS =:CONTENT_STATUS ";
            params.put("CONTENT_STATUS",contentStatus);
        }


        return this.paginate(sql.toString(),params,page,null,null);
    }

    @Override
    public List<Content> findQuestionsByUserId(long userId, Page page) throws Exception {
        String sql = "SELECT" +
                "	*" +
                "FROM" +
                "	wbsj_content a" +
                "WHERE" +
                "	a.BUSINESS_TYPE = :CONTENT_TYPE" +
                "AND a.USER_ID = :USER_ID";
        Map<String,Object> params = new HashMap<>();
        params.put("CONTENT_TYPE",ContentType.question.name());
        params.put("USER_ID",userId);
        return this.paginate(sql.toString(),params,page,null,null);
    }
}
