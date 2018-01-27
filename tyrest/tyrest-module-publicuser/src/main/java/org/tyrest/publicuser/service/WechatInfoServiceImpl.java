package org.tyrest.publicuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.publicuser.face.model.WechatInfoModel;
import org.tyrest.publicuser.face.orm.dao.WechatInfoDAO;
import org.tyrest.publicuser.face.orm.entity.WechatInfo;
import org.tyrest.publicuser.face.service.WechatInfoService;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.orm.entity.Principal;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: WechatInfoServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WechatInfoServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="wechatInfoService")
public class WechatInfoServiceImpl extends BaseServiceImpl<WechatInfoModel, WechatInfo> implements WechatInfoService
{

	@Autowired
	WechatInfoDAO wechatInfoDAO;

	@Autowired
	PrincipalDAO principalDAO;

	@Override
	public WechatInfoModel createWechatInfo(WechatInfoModel wechatInfoModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WechatInfoModel updateWechatInfo(WechatInfoModel wechatInfoModel) throws Exception {
		WechatInfo wechatInfo =  wechatInfoDAO.findByUserId(wechatInfoModel.getUserId());
		Principal principal   =  principalDAO.findByUserId(wechatInfoModel.getUserId());
		if(!ValidationUtil.isEmpty(wechatInfo) && !ValidationUtil.isEmpty(principal))
		{
			Bean.copyExistPropertis(wechatInfoModel,wechatInfo);
			wechatInfoDAO.update(wechatInfo);

			principal.setAvatar(wechatInfo.getAvatarUrl());
			principal.setGender(wechatInfo.getSex());
			principal.setNickName(wechatInfo.getNickName());
			principalDAO.update(principal);

		}
		return Bean.toModel(wechatInfo,wechatInfoModel);
	}

	@Override
	public WechatInfoModel getByOpenId(String openId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WechatInfoModel getByUserId(Long userId) throws Exception {

		WechatInfoModel wechatInfoModel = null;
		WechatInfo wechatInfo = this.wechatInfoDAO.findByUserId(userId);
		if(!ValidationUtil.isEmpty(wechatInfo))
		{
			wechatInfoModel = Bean.toModel(wechatInfo,new WechatInfoModel());
		}
		return wechatInfoModel;
	}

	@Override
	public void bindWechatInfo(String openId, Long userId, String loginId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
