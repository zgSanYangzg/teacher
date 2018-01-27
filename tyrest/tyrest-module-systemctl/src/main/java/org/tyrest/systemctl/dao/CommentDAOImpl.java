package org.tyrest.systemctl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.orm.dao.CommentDAO;
import org.tyrest.systemctl.face.orm.entity.Comment;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: CommentDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CommentDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="commentDAO")
public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO
{

	@Override
	public List<Comment> findCommnetByPage(String targetType, Long targetId, String isAudit, String auditStatus,
			Page page,String orderBy,String order) throws Exception {
		StringBuilder sqlSufix = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(targetType)){
			sqlSufix.append(" AND TARGET_TYPE = :TARGET_TYPE ");
			params.put("TARGET_TYPE",targetType);
		}
		if(!ValidationUtil.isEmpty(targetId)){
			sqlSufix.append(" AND TARGET_ID = :TARGET_ID ");
			params.put("TARGET_ID",targetId);
		}
		if(!ValidationUtil.isEmpty(isAudit)){
			sqlSufix.append(" AND IS_AUDIT = :IS_AUDIT ");
			params.put("IS_AUDIT",isAudit);
		}
		if(!ValidationUtil.isEmpty(auditStatus)){
			sqlSufix.append(" AND AUDIT_STATUS = :AUDIT_STATUS ");
			params.put("AUDIT_STATUS",auditStatus);
		}
		return this.paginate(sqlSufix.toString(), params, page, orderBy, order);
	}

}
