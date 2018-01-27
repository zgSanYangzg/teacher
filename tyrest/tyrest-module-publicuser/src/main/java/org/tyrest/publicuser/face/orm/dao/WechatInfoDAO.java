package org.tyrest.publicuser.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.publicuser.face.orm.entity.WechatInfo;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: WechatInfoDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WechatInfoDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface WechatInfoDAO extends GenericDAO<WechatInfo> {
	public void insertWithCache(WechatInfo wechatInfo) throws Exception;

	public void updateWithCache(WechatInfo wechatInfo) throws Exception;

	public WechatInfo findByOpenId(String openId) throws Exception;

	public WechatInfo findByUserId(Long userId) throws Exception;
}
