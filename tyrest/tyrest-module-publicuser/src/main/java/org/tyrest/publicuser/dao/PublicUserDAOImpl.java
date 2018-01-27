package org.tyrest.publicuser.dao;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.publicuser.face.orm.dao.PublicUserDAO;
import org.tyrest.publicuser.face.orm.entity.PublicUser;
import org.tyrest.security.face.orm.entity.Principal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PublicUserDAOImpl.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PublicUserDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-25 14:50:25		freeapis		Initial.
 *
 * </pre>
 */
@Repository(value="publicUserDAO")
public class PublicUserDAOImpl extends GenericDAOImpl<PublicUser> implements PublicUserDAO
{
    @Override
    public void update(PublicUser publicUser) throws Exception{
        super.update(publicUser);
        Redis.removeSingle(this.getEntityClass(),publicUser.getUserId().toString());
    }

    @Override
    public PublicUser findByUserId(Long userId) throws Exception {
        PublicUser publicUser = Redis.getSingle(this.getEntityClass(),userId.toString());
        if(ValidationUtil.isEmpty(publicUser)){
            String sql = " AND USER_ID=:USER_ID ";
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("USER_ID",userId);
            publicUser = this.findFirst(sql,params);
            if(!ValidationUtil.isEmpty(publicUser)){
                Redis.setSingle(publicUser,userId.toString());
            }
        }
        return publicUser;
    }

    @Override
    public List<Map<String, Object>> findUserByPage(String userName, Date startRegisterDate, Date endRegisterDate, String source, Page page, String orderby, String order) throws Exception {
        String findSql = "SELECT " +
                " a.USER_ID AS userId," +
                " a.AVATAR AS avatar," +
                " a.USER_NAME AS userName," +
                " a.REAL_NAME AS realName," +
                " a.REGISTER_DATE AS registerDate," +
                " a.REC_DATE AS recDate," +
                " b.CITY_NAME AS cityName," +
                " b.BIZ_LOCK_STATUS AS bizLockStatus," +
                " b.SOURCE AS source FROM "
                +this.tableName(Principal.class)+ " a INNER JOIN "+this.tableName()+" b" +
                " ON a.USER_ID = b.USER_ID " +
                " WHERE 1=1 ";
        StringBuilder sql = new StringBuilder(findSql);
        Map<String, Object> params = new HashMap<String, Object>();
        if(!ValidationUtil.isEmpty(userName)){
            sql.append(" AND a.USER_NAME = :USER_NAME ");
            params.put("USER_NAME", userName);
        }
        if(!ValidationUtil.isEmpty(startRegisterDate)){
            sql.append(" AND a.REGISTER_DATE >= :startRegisterDate ");
            params.put("startRegisterDate", startRegisterDate);
        }
        if(!ValidationUtil.isEmpty(endRegisterDate)){
            sql.append(" AND a.REGISTER_DATE <= :endRegisterDate ");
            params.put("endRegisterDate", endRegisterDate);
        }
        if(!ValidationUtil.isEmpty(source)){
            sql.append(" AND b.SOURCE = :SOURCE ");
            params.put("SOURCE", source);
        }
        if(ValidationUtil.isEmpty(orderby)){
            orderby = "recDate";
        }
        sql.append(" order by "+orderby );
        if(!ValidationUtil.isEmpty(order)){
            sql.append(" "+order );
        }
        return this.findMapsByPage(sql.toString(), params, page);
    }
}
