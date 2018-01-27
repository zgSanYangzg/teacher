package org.tyrest.systemctl.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.systemctl.face.model.CommentModel;
import org.tyrest.systemctl.face.orm.dao.CommentDAO;
import org.tyrest.systemctl.face.orm.entity.Comment;
import org.tyrest.systemctl.face.service.CommentService;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: CommentServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CommentServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="commentService")
public class CommentServiceImpl extends BaseServiceImpl<CommentModel, Comment> implements CommentService
{
	@Autowired
	private CommentDAO commentDAO;


	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@Override
	public CommentModel createComment(CommentModel commentModel) throws Exception {
		Comment comment = Bean.toPo(commentModel, new Comment());
		comment.setSequenceNBR(sequenceGenerator.getNextValue());
		comment.setRecDate(new Date());
		comment.setRecUserId(RequestContext.getExeUserId());
		comment.setRecStatus(CoreConstants.COMMON_ACTIVE);
		comment.setIsAudit(CoreConstants.COMMON_N);
		comment.setAuditStatus(CoreConstants.COMMON_Y);
		commentDAO.insert(comment);
		return Bean.toModel(comment, new CommentModel());
	}

	@Override
	public CommentModel updateAuditStatus(Long id, String auditStatus) throws Exception {
		Comment currentComment = commentDAO.findById(id);
		if(ValidationUtil.isEmpty(currentComment)){
			throw new BusinessException(MessageConstants.DATA_NOT_FOUND);
		}
		if(CoreConstants.COMMON_Y.equals(auditStatus) || CoreConstants.COMMON_N.equals(auditStatus)){
			currentComment.setAuditStatus(auditStatus);
			currentComment.setRecDate(new Date());
			currentComment.setRecUserId(RequestContext.getExeUserId());
		}
		commentDAO.update(currentComment);
		return Bean.toModel(currentComment, new CommentModel());
	}

	@Override
	public CommentModel getById(Long id) throws Exception {
		return Bean.toModel(commentDAO.findById(id), new CommentModel());
	}

	@Override
	public List<CommentModel> getCommentByPage(String targetType, Long targetId, String isAudit, String auditStatus,
			Page page, String orderBy, String order) throws Exception {
		return  Bean.toModels(commentDAO.findCommnetByPage(targetType, targetId, isAudit, auditStatus, page, orderBy, order),CommentModel.class);
	}

}
