package org.tyrest.systemctl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.StringUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.orm.dao.FeedbackDAO;
import org.tyrest.systemctl.face.orm.entity.Feedback;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: FeedbackDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: FeedbackDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value = "feedbackDAO")
public class FeedbackDAOImpl extends GenericDAOImpl<Feedback> implements FeedbackDAO {

	@Override
	public List<Feedback> findByPage(String nickName, String contact, String recStatus, Page page, String orderBy,
			String order) throws Exception {
		StringBuilder sqlSufix = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (!ValidationUtil.isEmpty(nickName)) {
			sqlSufix.append(" AND EXISTS (SELECT 1 FROM SECURITY_PUBLICUSER P WHERE P.USER_ID = Z.USER_ID AND P.NICK_NAME LIKE '%")
					 .append(StringUtil.iso2UTF8(nickName)).append("%') ");
		}
		if (!ValidationUtil.isEmpty(contact)) {
			sqlSufix.append(" AND CONTACT = :CONTACT ");
			params.put("CONTACT", contact);
		}
		if (!ValidationUtil.isEmpty(recStatus)) {
			sqlSufix.append(" AND REC_STATUS = :REC_STATUS ");
			params.put("REC_STATUS", recStatus);
		}
		return this.paginate(sqlSufix.toString(), params, page, orderBy, order);
	}

}
