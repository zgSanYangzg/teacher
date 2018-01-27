package org.tyrest.systemctl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.systemctl.face.model.FeedbackModel;
import org.tyrest.systemctl.face.orm.dao.FeedbackDAO;
import org.tyrest.systemctl.face.orm.entity.Feedback;
import org.tyrest.systemctl.face.service.FeedbackService;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: FeedbackServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: FeedbackServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="feedbackService")
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackModel, Feedback> implements FeedbackService
{
	@Autowired
	private FeedbackDAO feedbackDAO;

	@Override
	public Page getByPage(String nickName, String contact, String recStatus, Page page, String orderBy, String order)
			throws Exception {
		page.setList(Bean.toModels(feedbackDAO.findByPage(nickName, contact, recStatus, page, orderBy, order),FeedbackModel.class));
		return page;
	}
	
	
	public FeedbackModel findById(Long id) throws  Exception
	{
		return Bean.toModel(this.feedbackDAO.findById(id), new FeedbackModel());
	}

}
