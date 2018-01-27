package org.tyrest.human.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.human.face.model.SocialUserModel;
import org.tyrest.human.face.orm.entity.SocialUser;

import java.util.List;

/**
 * Created by ttsw on 2017/1/11.
 */
public interface SocialUserService extends BaseService<SocialUserModel,SocialUser>
{
    Page findByPage(String userName, String userIdcard, String orderBy, String order, Page page) throws Exception;
    SocialUserModel saveEntity(SocialUserModel suModel) throws  Exception;
    SocialUserModel updateEntity(SocialUserModel suModel) throws Exception;
}
