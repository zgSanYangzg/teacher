package org.tyrest.cms.face.orm.dao;

import org.tyrest.cms.face.orm.entity.Advertisement;
import org.tyrest.core.mysql.GenericDAO;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  Lexing
 *  File: AdvertisementDAO.java
 * 
 *  Lexing, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AdvertisementDAO.java 31101200-9 2014-10-14 16:43:51Z Lexing\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月27日		wuqiang		Initial.
 *
 * </pre>
 */
public interface AdvertisementDAO extends GenericDAO<Advertisement>
{

	/**
	 * TODO.根据广告类型获取广告列表
	 * @param adType
	 * @return
	 * @throws Exception
	 */
	List<Advertisement> findAdvertisementList(Integer adType) throws Exception;

	/**
	 * TODO.验证轮播广告标题是否可用
	 * @param title
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Boolean isTitleAvailable(Integer type,String title, Long id) throws Exception;
}
