package org.tyrest.core.foundation.constants;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: ErrorCodeConstants.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ErrorCodeConstants.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class ErrorCodeConstants {
	public static final String FID_AUTH_FAILD_401 = "fid_unauthorized";
	public static final String NO_PERMISSON_401 = "no_permission";
	public static final String BAD_REQUEST_400 = "bad_request";
	public static final String DATA_VALIDATION_ERROR_400 = "data_validation_error";
	public static final String INVALID_URI_400 = "invalid_uri";
	public static final String RECORD_HAS_EXISTED_400 = "record_has_existed";
	public static final String UNAUTHORIZED_401 = "unauthorized";
	public static final String EDMS_SERVER_UNAVAILABLE_401 = "edms_server_unavailable";
	public static final String NO_TOKEN_401 = "no_token";
	public static final String TOKEN_EXPIRED_401 = "token_expired";
	public static final String USER_LOCKED_401 = "user_account_locked";
	public static final String INVALID_PASSWORD_401 = "invalid_userid_or_password";
	public static final String USER_ACCOUNT_UNAVAILABLE_401 = "user_account_unavailable";
	public static final String USER_ACCOUNT_NO_MAPPING_401 = "user_account_no_aa_mapping";
	public static final String USER_ACCOUNT_NOT_EXIST_401 = "user_account_not_exist";
	public static final String USER_ACCOUNT_NOT_ACTIVE_401 = "user_account_inactive";
	public static final String USER_ACCOUNT_DISABLED_401 = "user_account_disabled";
	public static final String FORBIDDEN_403 = "forbidden";
	public static final String NOT_FOUND_404 = "resource_not_found";
	public static final String INTERNAL_SERVER_ERROR_500 = "internal_server_error";
	public static final String EMSE_500 = "emse_error";
	public static final String CONFIGURATION_ERROR_500 = "configuration_error";

	public static final String DUPLICATE_409 = "duplicate_data";

	public static final String OPERATE_PART_SUCCESS_206 = "partial_success";

	public static final String USER_NOT_FOUND_CODE = "USER_NOT_FOUND";
	public static final String USER_LOOKED_CODE = "USER_LOOKED";
	public static final String USERID_OR_PASSWORD_NOT_VALID_CODE = "USERID_OR_PASSWORD_NOT_VALID";
	public static final String USER_INFO_ERROR_CODE = "USER_INFO_ERROR";

	public static final String TOKEN_OTHER_PLACE_CODE = "TOKEN_OTHER_PLACE";
	public static final String TOKEN_EXPIRED_CODE = "TOKEN_EXPIRED";
	public static final String TOKEN_NOT_FOUND_CODE = "TOKEN_NOT_FOUND";
	public static final String TOKEN_REQUIRED_CODE = "TOKEN_REQUIRED";
}

/*
 * $Log: av-env.bat,v $
 */