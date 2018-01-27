package org.tyrest.human.face.orm.dao;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.human.face.orm.entity.SocialUser;

import java.util.List;

/**
 * Created by ttsw on 2017/1/11.
 */
public interface SocialUserDAO extends GenericDAO<SocialUser> {

    List<SocialUser> findByPage(String userName, String userIdcard, String orderBy, String order, Page page) throws Exception;
    String  deleteWithCache(String   userNo) throws Exception;
    SocialUser getWithCache(String userNo) throws Exception;
    SocialUser updateWithCache(SocialUser entity) throws Exception;
    SocialUser createWithCache(SocialUser entity) throws Exception;

}
