package org.tyrest.opendata.push.umeng.ios;

import org.tyrest.opendata.push.umeng.IOSNotification;

import com.alibaba.fastjson.JSONObject;

public class IOSGroupcast extends IOSNotification {
	public IOSGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
