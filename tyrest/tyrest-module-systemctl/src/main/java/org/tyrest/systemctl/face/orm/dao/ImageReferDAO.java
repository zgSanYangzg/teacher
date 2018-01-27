package org.tyrest.systemctl.face.orm.dao;

import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.ImageRefer;

import java.util.List;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: ImageReferDAO.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ImageReferDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-09 11:30:15		freeapis		Initial.
 *
 * </pre>
 */
public interface ImageReferDAO extends GenericDAO<ImageRefer>
{
	void createImageRefer(BaseEntity entity, ImageRefer... imageRefers) throws Exception;
	
	void deleteImageRefer(BaseEntity entity) throws Exception;
	
	List<String> findImageRefers(BaseEntity entity) throws Exception;

	public List<ImageRefer> findImageRefersWithOrder(BaseEntity entity, String orderBy, String order) throws Exception;
	
}