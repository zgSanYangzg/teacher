package org.tyrest.opendata.lbs;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: CoordinateType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CoordinateType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum CoordinateType {
	gps(1),autonavi(2),baidu(3);
	
	private int value;
	private CoordinateType(int value){
		this.value = value;
	}
	
	public int value(){
		return this.value;
	}
}

/*
*$Log: av-env.bat,v $
*/