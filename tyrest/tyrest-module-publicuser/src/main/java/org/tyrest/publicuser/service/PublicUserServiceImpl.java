package org.tyrest.publicuser.service;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.publicuser.face.model.PublicUserModel;
import org.tyrest.publicuser.face.orm.dao.PublicUserDAO;
import org.tyrest.publicuser.face.orm.entity.PublicUser;
import org.tyrest.security.face.enums.AuthType;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.model.PrincipalModel;
import org.tyrest.security.face.orm.dao.LoginInfoDAO;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.orm.entity.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.publicuser.face.service.PublicUserService;
import org.tyrest.security.face.service.SecurityService;
import org.tyrest.security.face.service.SmsVerifyService;
import org.tyrest.systemctl.face.model.LocationInfoModel;
import org.tyrest.systemctl.face.service.LocationInfoService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  freeapis
 *  File: PublicUserServiceImpl.java
 *
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: PublicUserServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-25 14:50:25		freeapis		Initial.
 *
 * </pre>
 */
@Service(value = "publicUserService")
public class PublicUserServiceImpl extends BaseServiceImpl<PublicUserModel, PublicUser> implements PublicUserService {

    @Autowired
    SecurityService securityService;

    @Autowired
    PublicUserDAO publicUserDAO;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    LoginInfoDAO loginInfoDAO;

    @Autowired
    PrincipalDAO principalDAO;

    @Autowired
    SmsVerifyService smsVerifyService;

    @Autowired
    LocationInfoService locationInfoService;


    @Override
    public PublicUserModel getUserById(Long userId) throws Exception {
        //查询公网用户信息
        PublicUserModel userModel = null;
        PublicUser publicUser =  publicUserDAO.findByUserId(userId);
        if(ValidationUtil.isEmpty(publicUser)){
            return userModel;
        }
        userModel = Bean.toModel(publicUser,new PublicUserModel());
        PrincipalModel principalModel = securityService.getPrincipal(userId);
        Bean.copyExistPropertis(principalModel,userModel);
        return userModel;
    }

    @Override
    public Page getUserByPage(String userName, Date startRegisterDate, Date endRegisterDate, String source, Page page, String orderby, String order) throws Exception {
        List<Map<String,Object>> listEntity = publicUserDAO.findUserByPage(userName,startRegisterDate,endRegisterDate,source,page,orderby,order);
        List<PublicUserModel> publicUserList = Bean.listMap2ListBean(listEntity,PublicUserModel.class);
        page.setList(publicUserList);
        return page;
    }

    @Override
    public PublicUserModel updateBizLockStatus(Long userId) throws Exception {
        PublicUser publicUser =  publicUserDAO.findByUserId(userId);
        if(ValidationUtil.isEmpty(publicUser)){
           throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        publicUser.setBizLockStatus(CoreConstants.COMMON_N.equals(publicUser.getBizLockStatus())?CoreConstants.COMMON_Y:CoreConstants.COMMON_N);
        publicUser.setBizLockDate(new Date());
        publicUser.setBizLockUserId(RequestContext.getExeUserId());
        publicUserDAO.update(publicUser);
        return Bean.toModel(publicUser,new PublicUserModel());
    }

    @Override
    public PublicUserModel updatePassword(Long userId) throws Exception {
        PublicUser publicUser =  publicUserDAO.findByUserId(userId);
        if(ValidationUtil.isEmpty(publicUser)){
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        securityService.resetPassword(userId);
        return Bean.toModel(publicUser,new PublicUserModel());
    }

    @Override
    public PublicUserModel updatePublicUser(PublicUserModel publicUserModel) throws Exception {
        PublicUser publicUser =  publicUserDAO.findByUserId(publicUserModel.getUserId());
        if(ValidationUtil.isEmpty(publicUser)){
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        publicUserModel.setRecDate(new Date());
        publicUserModel.setRecUserId(RequestContext.getExeUserId());
        //所在城市信息
        if(!ValidationUtil.isEmpty(publicUserModel.getCity())){
            LocationInfoModel locationInfoModel = locationInfoService.getByCode(publicUserModel.getCity());
            if(ValidationUtil.isEmpty(locationInfoModel)){
                throw new DataValidateException("填写城市不存在！");
            }
            publicUserModel.setCityName(locationInfoModel.getLocationName());
            locationInfoModel = locationInfoService.getByCode(locationInfoModel.getParentCode());
            publicUserModel.setProvince(locationInfoModel.getLocationCode());
            publicUserModel.setProvinceName(locationInfoModel.getLocationName());
        }
        //修改公网用户信息
        Bean.copyExistPropertis(publicUserModel,publicUser);
        publicUserDAO.update(publicUser);
        //修改主体信息
        PrincipalModel currentPrincipal = Bean.copyExistPropertis(publicUserModel,new PrincipalModel());
        currentPrincipal = securityService.updatePrincipal(currentPrincipal);
        Bean.copyExistPropertis(publicUser,publicUserModel);
        return Bean.copyExistPropertis(currentPrincipal,publicUserModel);
    }

    @Override
    public Map<String, Object> signIn(IdType idType, AuthType authType, UserType userType, AuthRequestModel authModel) throws Exception {
        UserSession userSession = securityService.createSession(idType, authType, userType, authModel);
        PublicUser publicUser = publicUserDAO.findByUserId(userSession.getUserId());
        //创建公网用户信息
        if (ValidationUtil.isEmpty(publicUser)) {
            publicUser = new PublicUser();
            publicUser.setUserId(userSession.getUserId());
            publicUser.setSource(RequestContext.getProduct());
            publicUser.setIsPasswordAuthed(CoreConstants.COMMON_N);
            setCreatePublicProperty(publicUser);
            publicUserDAO.insert(publicUser);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CoreConstants.TOKEN, userSession.getSsoSessionId());
        result.put(CoreConstants.EXPIRE, userSession.getSsoSessionExpiration());
        result.put(CoreConstants.USERID, userSession.getUserId());
        return result;
    }

    @Override
    public PublicUserModel signUp(AuthRequestModel authModel) throws Exception {
        authModel.setAgency(CoreConstants.CODE_SUPER_ADMIN);
        PrincipalModel principalModel = securityService.createSecurityInfoForRegister(IdType.mobile,UserType.PUBLIC_USER,authModel);
        //创建公网用户信息
        PublicUser publicUser = new PublicUser();
        publicUser.setUserId(principalModel.getUserId());
        publicUser.setSource(RequestContext.getProduct());
        publicUser.setIsPasswordAuthed(CoreConstants.COMMON_Y);
        setCreatePublicProperty(publicUser);
        publicUserDAO.insert(publicUser);
        return Bean.toModel(publicUser,this.getModelClass().newInstance());
    }

    @Override
    public void updatePasswordBySelf(AuthRequestModel authModel) throws Exception {
        PublicUser publicUser = publicUserDAO.findByUserId(Long.valueOf(RequestContext.getExeUserId()));
        if(ValidationUtil.isEmpty(publicUser)){
            throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);
        }
        //修改主体账号的密码信息
        authModel.setMobile(authModel.getLoginId());
        securityService.modifyPassword(authModel);
        //修改PublicUser信息
        publicUser.setIsPasswordAuthed(CoreConstants.COMMON_Y);
        publicUserDAO.update(publicUser);
    }

    /**
     * 设置公共的属性
     * @param publicUser
     * @throws Exception
     */
    private void setCreatePublicProperty(PublicUser publicUser) throws Exception {
        publicUser.setSequenceNBR(sequenceGenerator.getNextValue());
        publicUser.setRecDate(new Date());
        publicUser.setRecUserId(ValidationUtil.isEmpty(RequestContext.getExeUserId())?CoreConstants.SYSTEM.toString():RequestContext.getExeUserId());
        publicUser.setRecStatus(CoreConstants.COMMON_ACTIVE);
        publicUser.setIsEmailAuthed(CoreConstants.COMMON_N);
        publicUser.setIsIdCardAuthed(CoreConstants.COMMON_N);
        publicUser.setBizLockStatus(CoreConstants.COMMON_N);
    }


}