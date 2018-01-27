package org.tyrest.systemctl.face.service;

import java.util.List;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.systemctl.face.model.CommentModel;
import org.tyrest.systemctl.face.orm.entity.Comment;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: CommentService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CommentService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface CommentService extends BaseService<CommentModel, Comment> {
	/**
	 * TODO.创建评论
	 * @param commentModel
	 * @throws Exception
	 */
	CommentModel createComment(CommentModel commentModel) throws Exception;

	/**
	 * TODO.审核评论
	 * @param id
	 * @param auditStatus
	 * @throws Exception
	 */
	CommentModel updateAuditStatus(Long id, String auditStatus) throws Exception;

	/**
	 * TODO.根据id获取评论详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CommentModel getById(Long id) throws Exception;

	/**
	 * TODO.分页查询评论列表
	 * @param targetType
	 * @param targetId
	 * @param isbn
	 * @param isAudit
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<CommentModel> getCommentByPage(String targetType, Long targetId, String isAudit, String auditStatus,
			Page page,String orderBy,String order) throws Exception;

}
