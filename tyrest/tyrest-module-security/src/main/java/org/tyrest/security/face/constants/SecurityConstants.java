package org.tyrest.security.face.constants;

/**
 * <pre>
 * 
 *  freeapis
 *  File: CoreConstants.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: CoreConstants.java 31101200-9 2014-10-14 16:43:51Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年7月11日		wuqiang		Initial.
 *
 * </pre>
 */
public class SecurityConstants {
	
	public static final String TEST_DATA_OPERATTYPE_DELETE = "delete";

	public static final String TEST_DATA_OPERATTYPE_UPDATE = "update";

	public static final String VIPNO_PROFIX = "UBS";

	public static final String VIPNO_CREATE = "VIPNO_CREATE";

	public static final String VIPNO_CREATE_KEY = "vipNoCreateKey";

	public static final String VIPNO_LENGTH = "10";
	
	public static final String RES_TYPE_API = "API";
	
	public static final String RES_TYPE_WEB = "WEB";
	
	public static final String MODULE_ROOT_CODE = "root";
	
	public static final String MODULE_ROOT_PCODE = "0";
	
	public static final String MODULE_PORT_SYS = "SYS";
	
	public static final String MODULE_PORT_AGENCY = "AGENCY";

	public static final String DEFAULT_USER_PASSWORD = "a123456";
	
	public static final String splitor = "--";
	
	public static final String CACHE_KEY_PREFIX_SESSION = "SESSION";
	
	public static final String MESSAGE_TOKEN_REQUIRED = "请求没有包含认证信息,请联系管理员.";

	public static final String MESSAGE_PRODUCT_REQUIRED = "请求必须包含设备信息.";

	public static final String MESSAGE_TOKEN_NOT_FOUND = "无效的Token.";

	public static final String MESSAGE_TOKEN_OTHER_PLACE = "用户在它处登录.";

	public static final String MESSAGE_RESOURCE_FOBBIDEN = "没有访问权限.";

	public static final String MESSAGE_LOGINID_ALREADY_EXIST = "账号已存在!";

	public static final String MESSAGE_AUTHINFO_NOT_VALID = "认证信息校验失败!";
	
	public static final String MESSAGE_USER_NOT_FOUND = "USER_NOT_FOUND--当前账号不存在";
	
	public static final String MESSAGE_USER_LOCKED = "USER_LOCKED--当前账号已经禁用,请联系管理员";
	
	public static final String MESSAGE_PASSWORD_ERROR = "PASSWORD_ERROR--密码错误,输入密码不匹配";
	
	public static final String MESSAGE_SMSCODE_ERROR = "SMSCODE_ERROR--验证码错误,请重新获取";

	public static final String MESSAGE_LOGINID_NOT_VALID = "登录账号不合法!";
	
	public static final String MESSAGE_LOGIN_PASSWORD_REQUIRED = "登录密码不能为空";

	public static final String MESSAEG_IDENTIFYINGCODE_ERROR = "验证码有误";

	/**
	 * 系统所有资源缓存key
	 */
	public static final String ALL_OPERATION_RESOURCE = "all_operation_resource";

	public static final String  APPROVED_CANNOT_AGAIN = "已审核,不能再次进行操作";

	public static final String IDCARD_UNMATCHING = "身份证信息不匹配!";

	public static final String MESSAGE_IDDISCERN_FAILED = "身份证识别失败,请上传清晰的并且有效的身份证！";
}