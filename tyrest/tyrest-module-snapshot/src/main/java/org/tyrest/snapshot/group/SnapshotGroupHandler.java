package org.tyrest.snapshot.group;

import java.util.List;
import java.util.Map;

import org.tyrest.snapshot.SnapshotType;
import org.tyrest.snapshot.face.orm.entity.SnapshotGroup;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotGroupHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnapshotGroupHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface SnapshotGroupHandler<M>
{
	/**
	 * 即时快照 
	 *
	 * @param snapshotGroupType 快照分组类型
	 * @param businessCode		业务标识code
	 * @param mainSnapshotNbrs	快照类型对应的主键Map
	 */
	List<SnapshotGroup>  snapshot (SnapshotGroupType  snapshotGroupType,String businessCode,Map<SnapshotType,Long> mainSnapshotNbrs)throws Exception;
	 
	 
	 
	 /**
		 * 封装主业务对象，
		 *
		 * @param snapshotGroupType 快照分组类型
		 * @param businessCode		业务标识code
		 */
	M assemble (SnapshotGroupType  snapshotGroupType,String businessCode)throws Exception;
}

/*
*$Log: av-env.bat,v $
*/