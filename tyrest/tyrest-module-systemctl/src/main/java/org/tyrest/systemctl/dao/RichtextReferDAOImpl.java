package org.tyrest.systemctl.dao;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.mysql.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.orm.dao.RichtextReferDAO;
import org.tyrest.systemctl.face.orm.entity.RichtextRefer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: RichtextReferDAOImpl.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RichtextReferDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-09 11:30:16		freeapis		Initial.
 *
 * </pre>
 */
@Repository(value="richtextReferDAO")
public class RichtextReferDAOImpl extends GenericDAOImpl<RichtextRefer> implements RichtextReferDAO
{

	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	@Override
	public void createRichtextRefer(BaseEntity entity,String richtextType,Long richtextRefer) throws Exception {
		RichtextRefer currentRefer = new RichtextRefer(sequenceGenerator.getNextValue(),
				entity.getClass().getName(), entity.getSequenceNBR(), richtextType,richtextRefer);
		currentRefer.setRecDate(new Date());
		currentRefer.setRecStatus(CoreConstants.COMMON_ACTIVE);
		currentRefer.setRecUserId(CoreConstants.SYSTEM.toString());
		this.insert(currentRefer);
	}

	@Override
	public RichtextRefer findRichtextRefer(BaseEntity entity,String richtextType) throws Exception {
		String entityType = entity.getClass().getName();
		StringBuilder sql = new StringBuilder(" AND ENTITY_TYPE = :ENTITY_TYPE AND ENTITY_ID = :ENTITY_ID AND RICHTEXT_TYPE = :RICHTEXT_TYPE");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ENTITY_TYPE",entityType);
		params.put("ENTITY_ID",entity.getSequenceNBR());
		params.put("RICHTEXT_TYPE",richtextType);
		return this.findFirst(sql.toString(), params);
	}
}