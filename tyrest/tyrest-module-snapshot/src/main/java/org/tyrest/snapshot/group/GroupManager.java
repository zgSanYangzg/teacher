package org.tyrest.snapshot.group;

import java.util.Map;

import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.support.SpringContextHelper;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.snapshot.SnapshotType;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: GroupManager.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: GroupManager.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class GroupManager
{
	/**
	 * 即时快照 
	 *
	 * @param snapshotGroupType 快照分组类型
	 * @param businessCode		业务标识code
	 * @param mainSnapshotNbrs	快照主键集合Map
	 * @throws Exception 
	 */
	public static void snapshot (SnapshotGroupType  snapshotGroupType,String businessCode,Map<SnapshotType,Long> mainSnapshotNbrs) throws Exception
	 {
		 //保存即时快照
		 //保存分组信息
		SnapshotGroupHandler<?> snapshotGroupHandler = getSnapshotGroupHandler(snapshotGroupType);
		snapshotGroupHandler.snapshot(snapshotGroupType, businessCode, mainSnapshotNbrs);
		 
	 }

	 
	 /**
	 * 封装主业务对象，
	 *
	 * @param snapshotGroupType 快照分组类型
	 * @param businessCode		业务标识code
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T assemble (SnapshotGroupType  snapshotGroupType,String businessCode) throws Exception
	{
		SnapshotGroupHandler<T> snapshotGroupHandler = (SnapshotGroupHandler<T>) getSnapshotGroupHandler(snapshotGroupType);
		return snapshotGroupHandler.assemble(snapshotGroupType, businessCode);
	}
	
	/**
	 * 获取快照处理类
	 * @param snapshotGroupType
	 * @return
	 * @throws BusinessException
	 */
	private static SnapshotGroupHandler<?> getSnapshotGroupHandler(SnapshotGroupType snapshotGroupType)
			throws BusinessException {
		SnapshotGroupHandler<?> snapshotGroupHandler = SpringContextHelper.getBean(snapshotGroupType.getSnapShotGroupHandler());
		if(ValidationUtil.isEmpty(snapshotGroupHandler)){
			throw new BusinessException("快照处理类未找到。");
		}
		return snapshotGroupHandler;
	}
	
	/**
	 * 私有化类
	 */
	private GroupManager(){
		
	}
	 
}

/*
*$Log: av-env.bat,v $
*/