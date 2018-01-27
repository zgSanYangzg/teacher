package org.tyrest.systemctl.face.orm.dao;

import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.systemctl.face.orm.entity.RichtextRefer;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: RichtextReferDAO.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RichtextReferDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-09 11:30:16		freeapis		Initial.
 *
 * </pre>
 */
public interface RichtextReferDAO extends GenericDAO<RichtextRefer>
{
	void createRichtextRefer(BaseEntity entity, String richtextType, Long richtextRefer) throws Exception;

	RichtextRefer findRichtextRefer(BaseEntity entity, String richtextType) throws Exception;
}