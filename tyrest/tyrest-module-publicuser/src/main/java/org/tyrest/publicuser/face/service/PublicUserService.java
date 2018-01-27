package org.tyrest.publicuser.face.service;

import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.publicuser.face.model.PublicUserModel;
import org.tyrest.publicuser.face.orm.entity.PublicUser;
import org.tyrest.security.face.enums.AuthType;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;

import java.util.Date;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PublicUserService.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PublicUserService.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-25 14:50:25		freeapis		Initial.
 *
 * </pre>
 */
public interface PublicUserService extends BaseService<PublicUserModel,PublicUser>
{

    /**
     * 根据用户编号获取用户的详细信息
     * @param userId
     * @return
     */
    PublicUserModel getUserById(Long userId) throws Exception;

    /**
     * 分页获取公网用户信息
     * @param userName
     * @param startRegisterDate
     * @param endRegisterDate
     * @param source
     * @param orderby
     * @param order
     * @return
     */
    Page getUserByPage(String userName, Date startRegisterDate, Date endRegisterDate, String source, Page page, String orderby, String order) throws Exception;


    /**
     * 修改业务锁定状态
     * @param userId
     * @return
     */
    PublicUserModel updateBizLockStatus(Long userId) throws Exception;

    /**
     * 修改公网用户密码
     * @param userId
     * @return
     */
    PublicUserModel updatePassword(Long userId) throws Exception;

    /**
     * 公网用户获取自身信息
     * @param publicUserModel
     * @return
     */
    PublicUserModel updatePublicUser(PublicUserModel publicUserModel) throws Exception;

    /**
     * 公网用户登录
     * @param idType
     * @param authType
     * @param userType
     * @param authModel
     * @return
     */
    Map<String,Object> signIn(IdType idType, AuthType authType, UserType userType, AuthRequestModel authModel) throws Exception;

    /**
     * 公网用户注册
     * @param authModel
     * @return
     * @throws Exception
     */
    PublicUserModel signUp(AuthRequestModel authModel) throws Exception;

    /**
     * 公网用户自己修改密码
     * @param authModel
     */
    void updatePasswordBySelf(AuthRequestModel authModel) throws Exception;
}
