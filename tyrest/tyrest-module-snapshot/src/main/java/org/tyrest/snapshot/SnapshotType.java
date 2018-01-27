package org.tyrest.snapshot;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SnapshotType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface SnapshotType
{
	public Class<? extends BaseSnapshot> getSnptClass();

	public String getSnapshotType();
	
	/**
	 * 是否可以为空 true可以为空 false不可为空
	 * @return
	 */
	public Boolean getNullAdble();
}

/*
*$Log: av-env.bat,v $
*/