package org.tyrest.systemctl.face.service;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.BaseService;
import org.tyrest.systemctl.face.model.FeedbackModel;
import org.tyrest.systemctl.face.orm.entity.Feedback;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: FeedbackService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: FeedbackService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface FeedbackService extends BaseService<FeedbackModel, Feedback> {

	
	
	public FeedbackModel findById(Long id) throws  Exception;
	
	
	/**
	 * 分页查询反馈信息
	 * 
	 * @param phoneNum
	 * @param recStatus
	 */
	Page getByPage(String nickName, String contact, String recStatus, Page page, String orderBy, String order)
			throws Exception;

}

/*
 * $Log: lexingbuild.bat,v $
 */