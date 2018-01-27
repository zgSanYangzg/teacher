package org.tyrest.human.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.human.face.orm.dao.SocialUserDAO;
import org.tyrest.human.face.orm.entity.SocialUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ttsw on 2017/1/11.
 */
@Repository(value="socialUserDAO")
public class SocialUserDAOImpl extends GenericDAOImpl<SocialUser> implements SocialUserDAO
{
    @Override
    public List<SocialUser> findByPage(String userName, String userIdcard, String orderBy, String order, Page page) throws Exception
    {
        StringBuffer sql = new StringBuffer();
        Map<String,Object> parms = new HashMap<String,Object>();
        if(!ValidationUtil.isEmpty(userName))
        {
            sql.append(" AND USER_NAME LIKE :USER_NAME");
            parms.put("USER_NAME","%"+userName+"%");
        }
        if(!ValidationUtil.isEmpty(userIdcard) )
        {
            sql.append(" AND USER_IDCARD LIKE :USER_IDCARD");
            parms.put("USER_IDCARD",userIdcard+"%");
        }
        return this.paginate(sql.toString(),parms,page,orderBy,order);
    }


    public  SocialUser createWithCache(SocialUser entity) throws Exception {
        this.insert(entity);


        //存放实体对象,系统会默认按对象类型全名加key的前缀
        Redis.setSingle(entity,entity.getUserNo());

        //String socialUserStr = "SOCIALUSER";

        //带过期时间的缓存,过期时间按秒计
        //Redis.setWithExpire(entity,2L,socialUserStr,entity.getUserNo());


        //key已存在则保存失败,不存在则保存成功 返回
        //Redis.setNXWithExpire()
        return entity;
    }


    public  SocialUser updateWithCache(SocialUser entity) throws Exception {
        this.update(entity);
        Redis.setSingle(entity,entity.getUserNo());
        return entity;
    }

    public  SocialUser getWithCache(String userNo) throws Exception {
        SocialUser socialUser = Redis.getSingle(SocialUser.class,userNo);
        if(ValidationUtil.isEmpty(socialUser))
        {
            StringBuffer sql = new StringBuffer();
            Map<String,Object> parms = new HashMap<String,Object>();
            sql.append(" and USER_NO =:userNo");
            parms.put("userNo",userNo);
            socialUser = this.findFirst(sql.toString(),parms);
            if(!ValidationUtil.isEmpty(socialUser))
            {
                Redis.setSingle(socialUser,userNo);
            }
        }
        return socialUser;
    }


    public  String  deleteWithCache(String   userNo) throws Exception {
        SocialUser user = this.getWithCache(userNo);
        if(!ValidationUtil.isEmpty(user))
        {
            this.delete(user.getSequenceNBR());
            Redis.removeSingle(SocialUser.class,user.getUserNo());
        }
        return userNo;
    }




}
