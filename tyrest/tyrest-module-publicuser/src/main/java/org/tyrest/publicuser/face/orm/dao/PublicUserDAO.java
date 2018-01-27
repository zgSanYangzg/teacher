package org.tyrest.publicuser.face.orm.dao;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.publicuser.face.orm.entity.PublicUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PublicUserDAO.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PublicUserDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-25 14:50:25		freeapis		Initial.
 *
 * </pre>
 */
public interface PublicUserDAO extends GenericDAO<PublicUser>
{

    /**
     * 查询公网用户信息
     * @param userId
     * @return
     */
    PublicUser findByUserId(Long userId) throws Exception;

    /**
     * 分页查询公网用户信息
     * @param userName
     * @param startRegisterDate
     * @param endRegisterDate
     * @param source
     * @param page
     * @param orderby
     * @param order
     * @return
     */
    List<Map<String,Object>> findUserByPage(String userName, Date startRegisterDate, Date endRegisterDate, String source, Page page, String orderby, String order) throws Exception;
}
