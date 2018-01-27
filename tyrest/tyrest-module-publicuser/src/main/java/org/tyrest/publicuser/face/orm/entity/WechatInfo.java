package org.tyrest.publicuser.face.orm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: WechatInfo.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: WechatInfo.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "public_user_wechat_info")
public class WechatInfo extends BaseEntity
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



	@Column(name = "USER_ID", unique = true)
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "MOBILE")
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "OPEN_ID", unique = true, nullable = false)
	public String getOpenId() {
		return this.openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Column(name = "COUNTRY")
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "SUBSCRIBE")
	public String getSubscribe() {
		return this.subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	
	@Column(name = "SUBSCRIBE_TIME")
	public Date getSubscribeTime() {
		return this.subscribeTime;
	}
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	
	@Column(name = "ORIGINAL_OPEN_ID")
	public String getOriginalOpenId() {
		return this.originalOpenId;
	}
	public void setOriginalOpenId(String originalOpenId) {
		this.originalOpenId = originalOpenId;
	}
	
	@Column(name = "NICK_NAME")
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "AVATAR_URL")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}





	@Column(name = "QRCODE_TICKET")
	public String getQrcodeTicket() {
		return this.qrcodeTicket;
	}
	public void setQrcodeTicket(String qrcodeTicket) {
		this.qrcodeTicket = qrcodeTicket;
	}
	
	@Column(name = "TEMP_QRCODE_TICKET")
	public String getTempQrcodeTicket() {
		return this.tempQrcodeTicket;
	}
	public void setTempQrcodeTicket(String tempQrcodeTicket) {
		this.tempQrcodeTicket = tempQrcodeTicket;
	}
	
	@Column(name = "TEMP_QRCODE_DEADLINE")
	public Date getTempQrcodeDeadline() {
		return this.tempQrcodeDeadline;
	}
	public void setTempQrcodeDeadline(Date tempQrcodeDeadline) {
		this.tempQrcodeDeadline = tempQrcodeDeadline;
	}

	@Column(name = "UNION_ID", unique = true, nullable = false)
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
}

