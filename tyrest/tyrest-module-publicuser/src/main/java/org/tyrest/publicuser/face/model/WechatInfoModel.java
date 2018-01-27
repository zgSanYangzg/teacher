package org.tyrest.publicuser.face.model;

import java.util.Date;

import org.tyrest.core.mysql.BaseModel;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: WechatInfoModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WechatInfoModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class WechatInfoModel extends BaseModel
{
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String mobile;
	private String openId;
	private String country;
	private String province;
	private String city;
	private String sex;
	private String subscribe;
	private Date subscribeTime;
	private String originalOpenId;
	private String nickName;
	private String avatarUrl;
	private String qrcodeTicket;
	private String tempQrcodeTicket;
	private Date tempQrcodeDeadline;
	private String unionId;


	private String templateId;//模板消息Id



	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getOpenId() {
		return this.openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSubscribe() {
		return this.subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	
	public Date getSubscribeTime() {
		return this.subscribeTime;
	}
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	
	public String getOriginalOpenId() {
		return this.originalOpenId;
	}
	public void setOriginalOpenId(String originalOpenId) {
		this.originalOpenId = originalOpenId;
	}
	
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getQrcodeTicket() {
		return this.qrcodeTicket;
	}
	public void setQrcodeTicket(String qrcodeTicket) {
		this.qrcodeTicket = qrcodeTicket;
	}
	
	public String getTempQrcodeTicket() {
		return this.tempQrcodeTicket;
	}
	public void setTempQrcodeTicket(String tempQrcodeTicket) {
		this.tempQrcodeTicket = tempQrcodeTicket;
	}
	
	public Date getTempQrcodeDeadline() {
		return this.tempQrcodeDeadline;
	}
	public void setTempQrcodeDeadline(Date tempQrcodeDeadline) {
		this.tempQrcodeDeadline = tempQrcodeDeadline;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}




	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


}

