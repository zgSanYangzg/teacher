package org.tyrest.systemctl.face.enums;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: StorageSpace.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  Description:用前缀代替七牛云的空间
 * 
 *  Notes:
 *  $Id: StorageSpace.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public enum StorageSpace {
	/**
	 * 头像
	 */
	AVATAR("avatar","http://7xus7z.com2.z0.glb.qiniucdn.com/"),
	/**
	 * 分类图标
	 */
	CATEGORYICON("categoryicon","http://7xus7z.com2.z0.glb.qiniucdn.com/"),
	/**
	 * 微信静态资源
	 */
	WEIXIN("weixin","http://o7s70rfog.bkt.clouddn.com/"),
	/**
	 * 通用
	 */
	COMMON("common","http://7xus7x.com2.z0.glb.qiniucdn.com/");
	
	private String spaceName;
	
	private String spaceUrl;
	
	private StorageSpace(String spaceName,String spaceUrl){
		this.spaceName = spaceName;
		this.spaceUrl = spaceUrl;
	}
	
	public static StorageSpace getInstanceByName(String name){
		StorageSpace result = null;
		for(StorageSpace e : StorageSpace.values()){
			if (e.name().equalsIgnoreCase(name)) {
				result = e;
			}
		}
		return result;
	}
	
	public String getSpaceName(){
		return this.spaceName;
	}
	
	public String getSpaceUrl(){
		return this.spaceUrl;
	}
}

/*
*$Log: av-env.bat,v $
*/