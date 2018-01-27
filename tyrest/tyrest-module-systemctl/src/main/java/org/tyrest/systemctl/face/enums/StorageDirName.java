package org.tyrest.systemctl.face.enums;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: StorageDirName.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  Description:存储的目录名
 * 
 *  Notes:
 *  $Id: StorageDirName.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum StorageDirName {

	/**
	 * 安卓目录
	 */
	ANDROID("android"), /**
						 * ios 2x目录
						 */
	IOS2X("ios2x"), /**
					 * ios 3x目录
					 */
	IOS3X("ios3x");

	private String dirName;

	private StorageDirName(String dirName) {
		this.dirName = dirName;
	}

	public static StorageDirName getInstanceByName(String name) {
		StorageDirName result = null;
		for (StorageDirName e : StorageDirName.values()) {
			if (e.name().equalsIgnoreCase(name)) {
				result = e;
			}
		}
		return result;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

}

/*
 * $Log: av-env.bat,v $
 */