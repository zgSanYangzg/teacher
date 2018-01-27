package org.tyrest.asi.service.core.processor.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.tyrest.asi.face.orm.dao.GroupColumnDAO;
import org.tyrest.asi.service.core.processor.ASIHelper;
import org.tyrest.asi.service.core.processor.TypeProcessor;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mongodb.MongoDAO;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: FormProcessor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: FormProcessor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component("formProcessor")
public class FormProcessor extends TypeProcessor {

	@Autowired
	private GroupColumnDAO groupColumnDAO;

	@Autowired
	private MongoDAO<Map<String, String>> mongoDAO;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Override
	public void update4Type(String agencyCode, String entityType, String entityId,
			Map<String, List<Map<String, String>>> tableValues, Map<String, Map<String, String>> formValues)
					throws Exception {
		Map<String, String> currentForm = null;
		for (String groupCode : formValues.keySet()) {
			// #1.验证数据
			currentForm = formValues.get(groupCode);
			//取出ID
			String currentId = currentForm.get(ASIHelper.ID);
			currentId = !ValidationUtil.isEmpty(currentId) ? currentId : String.valueOf(sequenceGenerator.getNextValue());
			
			currentForm = validateValues(currentForm,groupColumnDAO.findGroupColumnsMap(agencyCode, groupCode));
			
			if (ValidationUtil.isEmpty(currentForm)) return;
			// #2.更新数据
			Iterator<String> iterator = currentForm.keySet().iterator();
			Update update = new Update();
			while (iterator.hasNext()) {
				String key = iterator.next();
				update.set(key, currentForm.get(key));
			}
			
			update.set(ASIHelper.ID, currentId);
			update.set(ASIHelper.AGENCY_CODE, agencyCode);
			update.set(ASIHelper.GROUP_CODE, groupCode);
			update.set(ASIHelper.ENTITY_TYPE, entityType);
			update.set(ASIHelper.ENTITY_ID, entityId);

			mongoDAO.getMongoOp().upsert(
					new Query(Criteria.where(ASIHelper.AGENCY_CODE).is(agencyCode)
							.and(ASIHelper.GROUP_CODE).is(groupCode)
							.and(ASIHelper.ENTITY_TYPE).is(entityType)
							.and(ASIHelper.ENTITY_ID).is(entityId)),
					update, Map.class, super.buildTableName(entityType, groupCode));

		}

	}

	@SuppressWarnings({ "rawtypes" })
	public Map getFormValue(String agencyCode, String groupCode, String entityType, String entityId) throws Exception {
		return mongoDAO.getMongoOp().findOne(
				new Query(Criteria.where(ASIHelper.AGENCY_CODE).is(agencyCode)
						.and(ASIHelper.GROUP_CODE).is(groupCode)
						.and(ASIHelper.ENTITY_TYPE).is(entityType)
						.and(ASIHelper.ENTITY_ID).is(entityId)),
				Map.class, this.buildTableName(entityType, groupCode));
	}

	@SuppressWarnings("rawtypes")
	public List<Map> getTableValue(String agencyCode, String groupCode, String entityType,
			String entityId) throws Exception {
		return null;
	}

}
