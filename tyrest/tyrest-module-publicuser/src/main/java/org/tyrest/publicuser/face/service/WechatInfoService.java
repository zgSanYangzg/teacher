package org.tyrest.publicuser.face.service;

import org.tyrest.core.mysql.BaseService;
import org.tyrest.publicuser.face.model.WechatInfoModel;
import org.tyrest.publicuser.face.orm.entity.WechatInfo;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: WechatInfoService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WechatInfoService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface WechatInfoService extends BaseService<WechatInfoModel, WechatInfo> {
	
	public WechatInfoModel createWechatInfo(WechatInfoModel wechatInfoModel) throws Exception;

	public WechatInfoModel updateWechatInfo(WechatInfoModel wechatInfoModel) throws Exception;

	public WechatInfoModel getByOpenId(String openId) throws Exception;

	public WechatInfoModel getByUserId(Long userId) throws Exception;

	/**
	 * TODO.绑定用户手机号到微信信息
	 * 
	 * @param currentUser
	 * @throws Exception
	 */
	public void bindWechatInfo(String openId, Long userId, String loginId) throws Exception;
}
