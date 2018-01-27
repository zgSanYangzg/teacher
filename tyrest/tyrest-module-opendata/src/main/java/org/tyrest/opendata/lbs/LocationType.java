package org.tyrest.opendata.lbs;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LocationType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum LocationType {
	coordinate(1),address(2);
	
	private int value;
	private LocationType(int value){
		this.value = value;
	}
	
	public int value(){
		return this.value;
	}
}

/*
*$Log: av-env.bat,v $
*/