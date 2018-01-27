package org.tyrest.systemctl.face.orm.dao;

import java.util.List;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.Comment;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: CommentDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CommentDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface CommentDAO extends GenericDAO<Comment> {
	/**
	 * TODO.分页查询评论列表
	 * 
	 * @param targetType
	 * @param targetId
	 * @param isAudit
	 * @param page
	 * @throws Exception
	 */
	List<Comment> findCommnetByPage(String targetType, Long targetId, String isAudit, String auditStatus,
			Page page,String orderBy,String order) throws Exception;

}
