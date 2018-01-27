package org.tyrest.asi.service.core.processor;

import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.utils.StringUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ASIHelper.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIHelper.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ASIHelper {
	public static final String ID = "_id";

	public static final String ROW_INDEX = "ROW_INDEX";

	public static final String ACTION = "ACTION";

	public static final String SPLIT_REGEX = "-";

	public static final String PROCESSORNAME_SUBFIX = "Processor";

	public static final String ENTITY_ID = "entityId";

	public static final String ENTITY_TYPE = "entityType";
	
	public static final String AGENCY_CODE = "AGENCY_CODE";

	public static final String GROUP_CODE = "GROUP_CODE";

	
	public static String[] getASIID(String asiID) throws Exception {

		return null;
	}

	/**
	 * Builder id for sub group model.
	 *
	 * @param groupName
	 *            the group name
	 * @param subGroupName
	 *            the sub group name
	 * @type subgroupType
	 * @return the string
	 */
	public static String encodeId(String groupName, String subGroupName) {
		return StringUtil.encodePK(groupName) + SPLIT_REGEX + StringUtil.encodePK(subGroupName);
	}

	public static String[] decodeId(String id) throws Exception {

		String[] idArray = null;
		if (ValidationUtil.isEmpty(id) || id.indexOf(SPLIT_REGEX) < 1) {
			throw new BadRequestException("[id]=" + id + " is invalid.");
		} else {
			idArray = id.split(SPLIT_REGEX);
			if (ValidationUtil.isEmpty(idArray) || idArray.length != 2) {
				throw new BadRequestException("[id]=" + id + " is invalid.");
			}
		}
		idArray[0] = StringUtil.decodePK(idArray[0]);
		idArray[1] = StringUtil.decodePK(idArray[1]);
		return idArray;
	}
}
