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
import org.tyrest.asi.face.orm.entity.GroupColumn;
import org.tyrest.asi.service.core.processor.ASIHelper;
import org.tyrest.asi.service.core.processor.TypeProcessor;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mongodb.MongoDAO;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: TablePorcessor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TablePorcessor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component("tableProcessor")
public class TablePorcessor extends TypeProcessor {

	@Autowired
	private MongoDAO<Map<String, String>> mongoDAO;

	@Autowired
	private GroupColumnDAO groupColumnDAO;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;

	private static final String OPT_ADD = "ADD";

	private static final String OPT_REMOVE = "DEL";

	private static final String OPT_UPDATE = "UPD";

	@Override
	public void update4Type(String agencyCode, String entityType, String entityId,
			Map<String, List<Map<String, String>>> tableValues, Map<String, Map<String, String>> formValues) throws Exception {
		if (ValidationUtil.isEmpty(tableValues)) {
			throw new DataValidateException("Request data can not be null.");
		}
		Map<String, GroupColumn> currentColumnMeta = null;
		for (String groupCode : tableValues.keySet()) {
			if (!ValidationUtil.isEmpty(tableValues.get(groupCode))) {
				currentColumnMeta = groupColumnDAO.findGroupColumnsMap(agencyCode, groupCode);
				for (Map<String, String> currentRow :tableValues.get(groupCode)) {
					String rowIndex = currentRow.get(ASIHelper.ROW_INDEX);
					// 取出行号.
					if (ValidationUtil.isEmpty(rowIndex)) {
						throw new BadRequestException(" Group :[" + groupCode + "] Row index not found.");
					}
					// 取出对此行的操作.
					String action = currentRow.get(ASIHelper.ACTION);
					//取出此行的ID
					String currentId = currentRow.get(ASIHelper.ID);
					currentId = !ValidationUtil.isEmpty(currentId) ? currentId : String.valueOf(sequenceGenerator.getNextValue());
					
					//验证该行数据
					currentRow = this.validateValues(currentRow, currentColumnMeta);
					
					if(!OPT_REMOVE.equals(action) && ValidationUtil.isEmpty(currentRow)) continue;
					
					Iterator<String> iterator = currentRow.keySet().iterator();
					Update update = new Update();
					while (iterator.hasNext()) {
						String key = iterator.next();
						update.set(key, currentRow.get(key));
					}
					//更新前设置特殊属性字段.
					update.set(ASIHelper.ID, currentId);
					update.set(ASIHelper.AGENCY_CODE, agencyCode);
					update.set(ASIHelper.GROUP_CODE, groupCode);
					update.set(ASIHelper.ENTITY_TYPE, entityType);
					update.set(ASIHelper.ENTITY_ID, entityId);
					update.set(ASIHelper.ROW_INDEX, rowIndex);

					if (!ValidationUtil.isEmpty(action)) {
						switch (action) {
							case OPT_ADD:
								mongoDAO.getMongoOp()
										.upsert(new Query(Criteria.where(ASIHelper.AGENCY_CODE).is(agencyCode)
												.and(ASIHelper.GROUP_CODE).is(groupCode)
												.and(ASIHelper.ENTITY_TYPE).is(entityType)
												.and(ASIHelper.ENTITY_ID).is(entityId)
												.and(ASIHelper.ROW_INDEX).is(rowIndex)),
												update,Map.class, buildTableName(entityType,groupCode));
								break;
							case OPT_UPDATE:
								mongoDAO.getMongoOp()
										.upsert(new Query(Criteria.where(ASIHelper.AGENCY_CODE).is(agencyCode)
												.and(ASIHelper.GROUP_CODE).is(groupCode)
												.and(ASIHelper.ENTITY_TYPE).is(entityType)
												.and(ASIHelper.ENTITY_ID).is(entityId)
												.and(ASIHelper.ROW_INDEX).is(rowIndex)), 
												update,Map.class, buildTableName(entityType, groupCode));
								break;
							case OPT_REMOVE:
								mongoDAO.getMongoOp()
										.remove(new Query(Criteria.where(ASIHelper.AGENCY_CODE).is(agencyCode)
												.and(ASIHelper.GROUP_CODE).is(groupCode)
												.and(ASIHelper.ENTITY_TYPE).is(entityType)
												.and(ASIHelper.ENTITY_ID).is(entityId)
												.and(ASIHelper.ROW_INDEX).is(rowIndex)),buildTableName(entityType, groupCode));
							break;
						}

					}

				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public Map getFormValue(String agencyCode, String groupCode,String entityType,String entityId) throws Exception {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<Map> getTableValue(String agencyCode, String groupCode,String entityType,
			String entityId) throws Exception {
		return mongoDAO.getMongoOp()
				.find(new Query(Criteria.where(ASIHelper.AGENCY_CODE).is(agencyCode)
						.and(ASIHelper.GROUP_CODE).is(groupCode)
						.and(ASIHelper.ENTITY_TYPE).is(entityType)
						.and(ASIHelper.ENTITY_ID).is(entityId)),
						Map.class,this.buildTableName(entityType, groupCode));
	}

}
