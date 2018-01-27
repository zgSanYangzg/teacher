package org.tyrest.systemctl.dao;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.mysql.BaseEntity;
import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.orm.dao.ImageReferDAO;
import org.tyrest.systemctl.face.orm.entity.ImageRefer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: ImageReferDAOImpl.java
 * 
 *  Freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ImageReferDAOImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  - 2016-10-09 11:30:15		freeapis		Initial.
 *
 * </pre>
 */
@Repository(value="imageReferDAO")
public class ImageReferDAOImpl extends GenericDAOImpl<ImageRefer> implements ImageReferDAO
{
	@Override
	public void createImageRefer(BaseEntity entity, ImageRefer... imageRefers) throws Exception {
		String entityType =  entity.getClass().getName();
		for(ImageRefer imageRefer : imageRefers){
			imageRefer.setEntityType(entityType);
			imageRefer.setEntityId(entity.getSequenceNBR());
			imageRefer.setRecDate(new Date());
			imageRefer.setRecStatus(CoreConstants.COMMON_ACTIVE);
			imageRefer.setRecUserId(CoreConstants.SYSTEM.toString());
			this.insert(imageRefer);
		}
	}

	@Override
	public void deleteImageRefer(BaseEntity entity) throws Exception {
		String entityType = entity.getClass().getName();
		StringBuilder deleteSql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		deleteSql.append(" DELETE FROM ").append(this.tableName()).append(" WHERE ENTITY_TYPE = :ENTITY_TYPE AND ENTITY_ID = :ENTITY_ID");
		params.put("ENTITY_TYPE",entityType);
		params.put("ENTITY_ID",entity.getSequenceNBR());
		this.update(deleteSql.toString(), params);
	}

	@Override
	public List<String> findImageRefers(BaseEntity entity) throws Exception {
		String entityType = entity.getClass().getName();
		StringBuilder sql = new StringBuilder("SELECT IMAGE_REFER FROM ")
				.append(this.tableName()).append(" WHERE ENTITY_TYPE = :ENTITY_TYPE AND ENTITY_ID = :ENTITY_ID");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ENTITY_TYPE",entityType);
		params.put("ENTITY_ID",entity.getSequenceNBR());
		return this.findObjects(sql.toString(), params);
	}

	@Override
	public List<ImageRefer> findImageRefersWithOrder(BaseEntity entity,String orderBy,String order) throws Exception {
		String entityType = entity.getClass().getName();
		StringBuilder sql = new StringBuilder("SELECT * FROM ")
				.append(this.tableName()).append(" WHERE ENTITY_TYPE = :ENTITY_TYPE AND ENTITY_ID = :ENTITY_ID");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ENTITY_TYPE",entityType);
		params.put("ENTITY_ID",entity.getSequenceNBR());
		return this.find(sql.toString(),params,orderBy,order);
	}

}