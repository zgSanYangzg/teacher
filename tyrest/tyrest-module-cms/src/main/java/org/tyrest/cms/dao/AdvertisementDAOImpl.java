package org.tyrest.cms.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.cms.face.orm.dao.AdvertisementDAO;
import org.tyrest.cms.face.orm.entity.Advertisement;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  Lexing
 *  File: AdvertisementDAOImpl.java
 * 
 *  Lexing, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AdvertisementDAOImpl.java 31101200-9 2014-10-14 16:43:51Z Lexing\leishuaifeng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月27日		leishuaifeng		Initial.
 *
 * </pre>
 */

@Repository(value="advertisementDAO")
public class AdvertisementDAOImpl extends GenericDAOImpl<Advertisement> implements AdvertisementDAO
{
	
	@Override
	public List<Advertisement> findAdvertisementList(Integer adType) throws Exception {
		StringBuilder sql = new StringBuilder()
				.append(" AND AD_TYPE = :AD_TYPE ");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AD_TYPE",adType);
		return this.find(sql.toString(), params,"recDate","desc");
	}

	@Override
	public Boolean isTitleAvailable(Integer type,String title, Long id) throws Exception {
		StringBuilder countSql = new StringBuilder().append(" AND AD_TYPE = :AD_TYPE  AND NEW_WINDOW_TITLE = :TITLE ");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("AD_TYPE",type);
		params.put("TITLE",title);
		if (!ValidationUtil.isEmpty(id)) {
			countSql.append(" AND SEQUENCE_NBR != :SEQUENCE_NBR ");
			params.put("SEQUENCE_NBR", id);
		}
		return this.findCount(countSql.toString(), params).compareTo(0L) > 0 ? false : true;
	}
}
