package org.tyrest.core.foundation.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: HttpClientHelper.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: HttpClientHelper.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@SuppressWarnings("deprecation")
public class HttpClientHelper {

	HttpClient httpClient;

	public HttpClientHelper() {
		httpClient = new DefaultHttpClient();

	}

	public void closeConn() {
		if (this.httpClient != null) {
			httpClient.getConnectionManager().closeExpiredConnections();
			httpClient = null;
		}
	}

	public Document html2Doc(String html) {
		return Jsoup.parse(html);
	}

	/**
	 * 
	 * TODO. 发送post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws java.io.IOException
	 */
	public String post(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpPost post = postForm(url, params);

		body = invoke(httpclient, post);

		// httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 
	 * TODO. 发送post请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("rawtypes")
	public String post(String url, Map<String, String> params, Map<String, String> header)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpPost post = postForm(url, params);

		if (header != null) {
			Set set = header.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = header.get(key);
				post.setHeader(key, value);

			}
		}

		body = invoke(httpclient, post);

		// httpclient.getConnectionManager().shutdown();

		return body;
	}


	public String post(String url, String jsonString)
			throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpPost post = new HttpPost(url);


		post.setHeader("Content-type","application/json; charset=utf-8");
		post.setHeader("Accept", "application/json");
		post.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
		body = invoke(httpclient, post);
		// httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 发送get请求 TODO.
	 * 
	 * @param url
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws java.io.IOException
	 */
	public String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpGet get = new HttpGet(url);

		try {
			body = invoke(httpclient, get);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 发送get请求 TODO.
	 * 
	 * @param url
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("rawtypes")
	public String get(String url, Map<String, String> header) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		HttpGet get = new HttpGet(url);

		if (header != null) {
			Set set = header.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = header.get(key);
				get.setHeader(key, value);
			}
		}

		try {
			body = invoke(httpclient, get);
		} catch (Exception e) {

			e.printStackTrace();
		}

		// httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * 发送get请求 TODO.
	 * 
	 * @param url
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("rawtypes")
	public String get(String url, Map<String, String> header, Map<String, String> params) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		url += this.parseMap2Param(params);

		HttpGet get = new HttpGet(url);

		if (header != null) {
			Set set = header.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = header.get(key);
				get.setHeader(key, value);
			}
		}

		body = invoke(httpclient, get);

		return body;
	}

	private String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost)
			throws ClientProtocolException, IOException {

		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);

		return body;
	}

	private String paseResponse(HttpResponse response) throws ParseException, IOException {
		HttpEntity entity = response.getEntity();

		String body = EntityUtils.toString(entity);

		return body;
	}

	private HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;

		response = httpclient.execute(httpost);
		return response;
	}

	/**
	 * 填充postForm TODO.
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	private HttpPost postForm(String url, Map<String, String> params) throws UnsupportedEncodingException {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		return httpost;
	}

	/**
	 * title:post请求
	 * 
	 * @param postUrl
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String post(URL postUrl, String content) throws Exception {
		String line = null;
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

		while ((line = reader.readLine()) != null) {
			return line;
		}
		reader.close();
		connection.disconnect();

		return line;

	}

	@SuppressWarnings({ "resource", "unused" })
	public String downloadImge(String url, String path, String imageName) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String filepath = null;
		String filename = "";
		try {
			filename = url.substring(url.lastIndexOf("/") + 1);
			HttpGet get = new HttpGet(url);
			HttpResponse re = httpclient.execute(get);
			if (re.getStatusLine().getStatusCode() == 200) {
				re.getEntity().getContent();
				File storeFile = new File("E:\\yichewangimg\\" + filename + "");
				if (storeFile.exists()) {
					return filename;
				}
				InputStream in = re.getEntity().getContent();
				FileOutputStream output = new FileOutputStream(storeFile);
				// 得到网络资源的字节数组,并写入文件
				byte[] buffer = new byte[1024];
				int j = 0;
				while ((j = in.read(buffer)) != -1) {
					output.write(buffer, 0, j);
				}
				output.flush();
				in.close();
				output.close();
				filepath = storeFile.getAbsolutePath();
			} else {
				return null;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		return filename;
	}

	public String parseMap2Param(Map<String, String> map) {
		String params = "?";
		if (!ValidationUtil.isEmpty(map)) {
			for (String p : map.keySet()) {
				params += p + "=" + map.get(p) + "&";
			}
		}

		return params;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static void main(String args[]) {
		String url = "http://localhost:8080/apis/1/auth/public";
		Map param = new HashMap();
		Map header = new HashMap();

		header.put("Content-Type", "application/json");

		param.put("product", "TEST");
		param.put("password", "123456");
		param.put("agency", "A1");
		param.put("userId", "13259780722");
		String str = "";

		HttpClientHelper hht = new HttpClientHelper();
		try {
			str = hht.post(url, param, header);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

/*
 * $Log: av-env.bat,v $
 */