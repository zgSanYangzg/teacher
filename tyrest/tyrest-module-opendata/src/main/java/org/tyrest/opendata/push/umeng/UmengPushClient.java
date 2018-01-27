package org.tyrest.opendata.push.umeng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.opendata.push.umeng.AndroidNotification.AfterOpenAction;
import org.tyrest.opendata.push.umeng.android.AndroidBroadcast;
import org.tyrest.opendata.push.umeng.android.AndroidCustomizedcast;
import org.tyrest.opendata.push.umeng.ios.IOSBroadcast;
import org.tyrest.opendata.push.umeng.ios.IOSCustomizedcast;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
@Component(value = "umengPushClient")
public class UmengPushClient implements InitializingBean {

	@Value("${umeng.push.appKey.andriod}")
	private String androidAppkey;

	@Value("${umeng.push.appMasterSecret.android}")
	private String androidAppMasterSecret;
	
	@Value("${umeng.push.appKey.ios}")
	private String iosAppkey;

	@Value("${umeng.push.appMasterSecret.ios}")
	private String iosAppMasterSecret;

	@Value("${umengProductionMode}")
	private boolean umengProductionMode;

	protected final String USER_AGENT = "Mozilla/5.0";

	protected HttpClient client = new DefaultHttpClient();

	protected static final String host = "http://msg.umeng.com";

	protected static final String uploadPath = "/upload";

	protected static final String postPath = "/api/send";
	
	private static final String ALIAS_TYPE = "userId";
	
	private static final String DEFAULT_UMENG_PUSH_DESCRIPTION = "I am description!";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (ValidationUtil.isEmpty(androidAppkey) 
				|| ValidationUtil.isEmpty(androidAppMasterSecret)
				|| ValidationUtil.isEmpty(iosAppkey)
				|| ValidationUtil.isEmpty(iosAppMasterSecret)) {
			throw new Exception("both appkey and appMasterSecret for ios and android platform is needed!");
		}
	}

	public void broadcast(String ticker, String title, String text, AfterOpenAction afterOpenAction, String openUrl,
			String openActivity, String openCustom, Map<String, String> extra) throws Exception {
		this.sendAndroidBroadcast(ticker, title, text, afterOpenAction, openUrl, openActivity, openCustom, extra);
		this.sendIOSBroadcast(ticker, extra);
	}
	
	public void multicast(List<String> aliases,String ticker, String title, String text, AfterOpenAction afterOpenAction,
			String openUrl,String openActivity, String openCustom, Map<String, String> extra) throws Exception{
		//如果要推送的alias小于50个,直接使用customizedcast,否则使用filecast
		if(aliases.size() <= 50){
			String commaJoinedAlias = StringUtil.join(aliases, ",");
			this.sendAndroidCustomizedcast(commaJoinedAlias, ticker, title, text, 
									afterOpenAction, openUrl, openActivity, openCustom, extra);
			this.sendIOSCustomizedcast(commaJoinedAlias, ticker, extra);
		}else{
			String fileAlias = StringUtil.join(aliases, "\n");
			this.sendAndroidCustomizedcastFile(fileAlias, ticker, title, text,
									afterOpenAction, openUrl, openActivity, openCustom, extra);
			this.sendIOSCustomizedcastFile(fileAlias, ticker, extra);
		}
	}

	private void sendAndroidBroadcast(String ticker, String title, String text, AfterOpenAction afterOpenAction,
			String openUrl, String openActivity, String openCustom, Map<String, String> extra) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(androidAppkey, androidAppMasterSecret);
		this.configAndroidNotification(broadcast, ticker, title, text, afterOpenAction, openUrl, openActivity,
				openCustom, extra);
		send(broadcast);
	}
	
	private void sendIOSBroadcast(String ticker,Map<String, String> extra) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(iosAppkey, iosAppMasterSecret);
		configIOSNotification(broadcast, ticker, extra);
		send(broadcast);
	}
	
	private void sendAndroidCustomizedcast(String commaJoinedAlias,String ticker, String title, 
			String text, AfterOpenAction afterOpenAction, String openUrl,String openActivity, 
			String openCustom, Map<String, String> extra) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(androidAppkey, androidAppMasterSecret);
		customizedcast.setAlias(commaJoinedAlias, ALIAS_TYPE);
		this.configAndroidNotification(customizedcast, ticker, title, text, afterOpenAction, openUrl, openActivity, openCustom, extra);
		send(customizedcast);
	}
	
	private void sendIOSCustomizedcast(String commaJoinedAlias,String ticker,Map<String, String> extra) throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(iosAppkey, iosAppMasterSecret);
		customizedcast.setAlias(commaJoinedAlias, ALIAS_TYPE);
		this.configIOSNotification(customizedcast, ticker, extra);
		send(customizedcast);
	}

	private void sendAndroidCustomizedcastFile(String fileAlias,String ticker, String title, 
			String text, AfterOpenAction afterOpenAction, String openUrl,String openActivity, 
			String openCustom, Map<String, String> extra) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(androidAppkey, androidAppMasterSecret);
		String fileId = uploadContents(androidAppkey, androidAppMasterSecret, fileAlias);
		customizedcast.setFileId(fileId, ALIAS_TYPE);
		this.configAndroidNotification(customizedcast, ticker, title, text, afterOpenAction, openUrl, openActivity, openCustom, extra);
		send(customizedcast);
	}
	
	private void sendIOSCustomizedcastFile(String fileAlias,String ticker,Map<String, String> extra) throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(iosAppkey, iosAppMasterSecret);
		String fileId = uploadContents(iosAppkey, iosAppMasterSecret, fileAlias);
		customizedcast.setFileId(fileId, ALIAS_TYPE);
		this.configIOSNotification(customizedcast, ticker, extra);
		send(customizedcast);
	}
	
	private void configAndroidNotification(AndroidNotification androidNotification, String ticker, String title,
			String text, AfterOpenAction afterOpenAction, String openUrl, String openActivity, String openCustom,
			Map<String, String> extra) throws Exception {
		if (ValidationUtil.isEmpty(afterOpenAction)) {
			afterOpenAction = AfterOpenAction.go_app;
		}
		switch (afterOpenAction) {
			case go_app:
				androidNotification.goAppAfterOpen();
				break;
			case go_activity:
				androidNotification.goActivityAfterOpen(openActivity);
				break;
			case go_url:
				androidNotification.goUrlAfterOpen(openUrl);
				break;
			case go_custom:
				androidNotification.goCustomAfterOpen(openCustom);
				break;
			default:
				break;
		}
		androidNotification.setTicker(ticker);
		androidNotification.setTitle(title);
		androidNotification.setText(text);
		androidNotification.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		if (!ValidationUtil.isEmpty(extra)) {
			for (String key : extra.keySet()) {
				androidNotification.setExtraField(key, extra.get(key));
			}
		}
		androidNotification.setProductionMode(umengProductionMode);
		androidNotification.setDescription(DEFAULT_UMENG_PUSH_DESCRIPTION);
	}
	
	private void configIOSNotification(IOSNotification iosNotification,String alter,Map<String,String> extra) throws Exception{
		iosNotification.setAlert(alter);
		iosNotification.setBadge(0);
		iosNotification.setSound("default");
		iosNotification.setProductionMode(umengProductionMode);
		if (!ValidationUtil.isEmpty(extra)) {
			for (String key : extra.keySet()) {
				iosNotification.setCustomizedField(key, extra.get(key));
			}
		}
		iosNotification.setDescription(DEFAULT_UMENG_PUSH_DESCRIPTION);
	}
	
	@Async
	private boolean send(UmengNotification msg) throws Exception {
		String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
		msg.setPredefinedKeyValue("timestamp", timestamp);
		String url = host + postPath;
		String postBody = msg.getPostBody();
		String sign = DigestUtils.md5Hex(("POST" + url + postBody + msg.getAppMasterSecret()).getBytes("utf8"));
		url = url + "?sign=" + sign;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
		StringEntity se = new StringEntity(postBody, "UTF-8");
		post.setEntity(se);
		// Send the post request and get the response
		HttpResponse response = client.execute(post);
		int status = response.getStatusLine().getStatusCode();
		System.out.println("Response Code : " + status);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		if (status == 200) {
			System.out.println("Notification sent successfully.");
		} else {
			System.out.println("Failed to send the notification!");
		}
		return true;
	}

	// Upload file with device_tokens to Umeng
	private String uploadContents(String appkey, String appMasterSecret, String contents) throws Exception {
		// Construct the json string
		JSONObject uploadJson = new JSONObject();
		uploadJson.put("appkey", appkey);
		String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
		uploadJson.put("timestamp", timestamp);
		uploadJson.put("content", contents);
		// Construct the request
		String url = host + uploadPath;
		String postBody = uploadJson.toString();
		String sign = DigestUtils.md5Hex(("POST" + url + postBody + appMasterSecret).getBytes("utf8"));
		url = url + "?sign=" + sign;
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);
		StringEntity se = new StringEntity(postBody, "UTF-8");
		post.setEntity(se);
		// Send the post request and get the response
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		// Decode response string and get file_id from it
		JSONObject respJson = JSONObject.parseObject(result.toString());
		String ret = respJson.getString("ret");
		if (!ret.equals("SUCCESS")) {
			throw new Exception("Failed to upload file");
		}
		JSONObject data = respJson.getJSONObject("data");
		String fileId = data.getString("file_id");
		// Set file_id into rootJson using setPredefinedKeyValue

		return fileId;
	}
}