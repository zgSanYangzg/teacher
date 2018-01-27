package org.tyrest.core.foundation.enumeration;

public enum TyrestClientType {
	IOS,
	Android,
	Wechat,
	Web,
	Postman,
	Sdk;
	
	public static TyrestClientType getInstanceByName(String name){
		for(TyrestClientType f: TyrestClientType.values()){
			if(f.name().equals(name)){
				return f;
			}
		}
		return null;	
	}
}
