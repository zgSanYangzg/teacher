package org.tyrest.human.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.mongodb.MongoDAO;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.human.face.model.SocialUserModel;
import org.tyrest.human.face.orm.dao.SocialUserDAO;
import org.tyrest.human.face.orm.entity.SocialUser;
import org.tyrest.human.face.service.SocialUserService;

import java.util.List;

/**
 * Created by ttsw on 2017/1/11.
 */
@Component(value = "socialUserService")
public class SocialUserServiceImpl extends BaseServiceImpl<SocialUserModel,SocialUser> implements SocialUserService
{
    @Autowired
    SocialUserDAO socialUserDAO;
    @Autowired
    SequenceGenerator sequenceGenerator;
    @Autowired
    MongoDAO<SocialUserModel> mongoDAO;

    @Override
    public Page findByPage(String userName, String userIdcard, String orderBy, String order, Page page) throws Exception
    {
        List<SocialUser> lst=socialUserDAO.findByPage(userName,userIdcard,orderBy,order,page);
        page.setList(Bean.toModels(lst,SocialUserModel.class));
        return page;
    }
    public SocialUserModel saveEntity(SocialUserModel suModel) throws  Exception
    {
        SocialUser ent=this.prepareEntity(suModel);
        socialUserDAO.createWithCache(ent);

        suModel = Bean.toModel(ent,suModel);

        mongoDAO.insert(suModel);

        return suModel;

    }

    public SocialUserModel updateEntity(SocialUserModel suModel) throws Exception
    {
        SocialUser su = socialUserDAO.findById(suModel.getSequenceNBR());
        Bean.copyExistPropertis(suModel,su);
        socialUserDAO.updateWithCache(su);//.update(su);
        return Bean.toModel(su,suModel);
    }
}
