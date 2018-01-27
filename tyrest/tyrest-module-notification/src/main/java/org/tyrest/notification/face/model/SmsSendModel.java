package org.tyrest.notification.face.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 
 *  freeapis
 *  File: SmsSendModel.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:JMS短信消息对象
 *  TODO
 * 
 *  Notes:
 *  $Id: SmsSendModel.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年3月17日		wuqiang		Initial.
 *
 * </pre>
 */
public class SmsSendModel implements Serializable {

	private static final long serialVersionUID = -1676093680389991356L;

	private String templateCode;// 短信模板编码,和params组合形成一条完整的短信
	private Map<String, String> params;// 短信模板变量值
	private String smsContent;// 短信内容,适合非模板的短信
	private List<String> mobiles;// 手机号列表

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public List<String> getMobiles() {
		return mobiles;
	}

	public void setMobiles(String... mobiles) {
		this.mobiles = Arrays.asList(mobiles);
	}

}

/*
 * $Log: av-env.bat,v $
 */