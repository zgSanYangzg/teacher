package org.tyrest.snapshot.group;

import org.tyrest.snapshot.SnapshotType;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotGroupType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnapshotGroupType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface SnapshotGroupType
{
	/**
	 * 
	 * 快照分组类型编号
	 *
	 * @return
	 */
	String getGroupCode();
	
	String getGroupName();
	
	
	
	String getSnapShotGroupHandler();
	
	
	
	SnapshotType[]  getSnapshotArray();
}

/*
*$Log: av-env.bat,v $
*/