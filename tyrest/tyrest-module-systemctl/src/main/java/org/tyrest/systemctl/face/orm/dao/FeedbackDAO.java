package org.tyrest.systemctl.face.orm.dao;

import java.util.List;

import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.Feedback;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: FeedbackDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: FeedbackDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface FeedbackDAO extends GenericDAO<Feedback> {

	public List<Feedback> findByPage(String nickName, String contact, String recStatus, Page page, String orderBy,
			String order) throws Exception;

}

/*
 * $Log: av-env.bat,v $
 */