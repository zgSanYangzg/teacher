package org.tyrest.notification.util;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: Constants.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Constants.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class Constants {
	/**
	 * 消息推送目标类型-商家
	 */
	public static final String MESSAGEPUSH_TARGETTYPE_AGENCY = "agency";
	/**
	 * 消息推送目标类型-公网app用户
	 */
	public static final String MESSAGEPUSH_TARGETTYPE_PUBLIC = "public";
	/**
	 * 消息推送目标类型-所有类型
	 */
	public static final String MESSAGEPUSH_TARGETTYPE_ALL = "all";
	/**
	 * 消息类型-通知,用于只读类型的消息
	 */
	public static final String MESSAGEPUSH_MESSAGETYPE_NOTIFICATION = "notification";
	/**
	 * 消息类型-自定义消息
	 */
	public static final String MESSAGEPUSH_MESSAGETYPE_MESSAGE = "message";
	/**
	 * 消息类型-触发器，用于告知目标请求数据
	 */
	public static final String MESSAGEPUESH_MESSAGETYPE_TRIGGER = "trigger";
	
	/**
	 * 消息推送异常信息提示
	 */
	public static final String MESSAGE_YUNBAPUSH_METHOD_TARGET_NOTMATCH = "推送方法和推送目标不匹配!";
	
	public static final String NOTIFICATION_KEY_EVENTCODE = "eventCode";
	public static final String NOTIFICATION_KEY_MESSAGEPROFILE = "messageProfile";
	public static final String NOTIFICATION_KEY_TYPE = "type";
	public static final String NOTIFICATION_KEY_ID = "id";
	public static final String NOTIFICATION_KEY_ORDERSN = "orderSn";
	
}
