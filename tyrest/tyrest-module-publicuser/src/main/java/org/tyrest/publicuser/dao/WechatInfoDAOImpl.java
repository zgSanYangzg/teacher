package org.tyrest.publicuser.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.publicuser.face.orm.dao.WechatInfoDAO;
import org.tyrest.publicuser.face.orm.entity.WechatInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: WechatInfoDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WechatInfoDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="wechatInfoDAO")
public class WechatInfoDAOImpl extends GenericDAOImpl<WechatInfo> implements WechatInfoDAO
{

	@Override
	public void insertWithCache(WechatInfo wechatInfo) throws Exception {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWithCache(WechatInfo wechatInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WechatInfo findByOpenId(String openId) throws Exception {

		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<>();

		params.put("openId",openId);
		sql.append(" and OPEN_ID = :openId ");
		return this.findFirst(sql.toString(),params);
	}

	@Override
	public WechatInfo findByUserId(Long userId) throws Exception {
		WechatInfo wechatInfo  = Redis.getSingle(WechatInfo.class,String.valueOf(userId));
		if(ValidationUtil.isEmpty(wechatInfo))
		{
			StringBuilder sql = new StringBuilder();
			Map<String,Object> params = new HashMap<>();

			params.put("userId",userId);
			sql.append(" and USER_ID = :userId ");
			wechatInfo = this.findFirst(sql.toString(),params);
			if(!ValidationUtil.isEmpty(wechatInfo))
			{
				Redis.setSingle(wechatInfo,String.valueOf(userId));
			}
		}
		return wechatInfo;
	}

}
