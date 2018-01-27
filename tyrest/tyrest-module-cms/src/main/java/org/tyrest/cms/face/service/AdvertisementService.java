package org.tyrest.cms.face.service;

import org.tyrest.cms.face.model.AdvertisementModel;
import org.tyrest.cms.face.orm.entity.Advertisement;
import org.tyrest.core.mysql.BaseService;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  Lexing
 *  File: AdvertisementService.java
 * 
 *  Lexing, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AdvertisementService.java 31101200-9 2014-10-14 16:43:51Z Lexing\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年6月16日		wuqiang		Initial.
 *
 * </pre>
 */
public interface AdvertisementService extends BaseService<AdvertisementModel,Advertisement>
{
	/**
	 * TODO.创建广告
	 * @param advertisementModel
	 * @throws Exception
	 */
	AdvertisementModel createAdvertisement(AdvertisementModel advertisementModel) throws Exception;
	/**
	 * TODO.删除广告
	 * @param id
	 * @throws Exception
	 */
	void deleteAdvertisement(Long id,String fileName) throws Exception;
	/**
	 * TODO.更新广告
	 * @param advertisementModel
	 * @throws Exception
	 */
	AdvertisementModel updateAdvertisement(AdvertisementModel advertisementModel) throws Exception;
	/**
	 * TODO.根据ID获取广告详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	AdvertisementModel getById(Long id) throws Exception;
	/**
	 * TODO.根据类型查询广告列表
	 * @param adType
	 * @return
	 * @throws Exception
	 */
	List<AdvertisementModel> getAdList(Integer adType) throws Exception;
	/**
	 * TODO.验证广告/专题标题是否可用
	 * @param type 轮播广告的标题还是专题的标题
	 * @param title 
	 * @param id
	 * @return
	 */
	Boolean isTitleAvailable(Integer type, String title, Long id) throws Exception;
}
