package org.tyrest.core.rest.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.ResourceForbiddenException;
import org.tyrest.core.foundation.utils.CommonUtil;
import org.tyrest.core.foundation.utils.DateUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.rest.constants.RestConstants;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.RequestUtil;
import org.tyrest.security.face.orm.entity.UserSession;
import org.tyrest.security.face.service.SecurityService;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: AuthInterceptor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AuthInterceptor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Autowired
	private SecurityService securityService;

	@Value("${internalAppKey}")
	private String internalAppKey;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(!(handler instanceof HandlerMethod))
			throw new DataNotFoundException(RestConstants.MESSAGE_OBJECT_NOTFOUND);
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		RequestContext.clean();

		String appkey = request.getHeader(CoreConstants.APPKEY);

		String token = request.getHeader(CoreConstants.TOKEN);

		String product = ValidationUtil.isEmpty(request.getHeader(CoreConstants.PRODUCT))
				? CoreConstants.PRODUCT_UNKNOWN : request.getHeader(CoreConstants.PRODUCT);

		String traceId = request.getHeader(CoreConstants.TRACEID);
		
		String requestIp = RequestUtil.getRemoteIp(request);
		
		String userAgent = request.getHeader(CoreConstants.USER_AGENT);
		
		setTraceId(traceId);
		RequestContext.setRequestIP(requestIp);
		RequestContext.setProduct(product);
		RequestContext.setUserAgent(userAgent);
		RequestContext.setSessionId(token);
		
		StringBuilder logInfo = new StringBuilder();
		logInfo.append("\n**********************************************************");
		logInfo.append("\n* AppKey :" + appkey);
		logInfo.append("\n* Token :" + token);
		logInfo.append("\n* Product :" + product);
		logInfo.append("\n* requestIp :" + requestIp);
		logInfo.append("\n* userAgent :" + userAgent);
		logInfo.append("\n* TraceId :" + traceId);
		logInfo.append("\n* Handler :" + handlerMethod.getBean().getClass());
		logInfo.append("\n* Method :" + handlerMethod.getMethod().getName());
		logInfo.append("\n* Access Time :" + DateUtil.getNow(DateUtil.Y_M_D_HMS));
		logInfo.append("\n**********************************************************");
		logger.info(logInfo.toString());
		
		if(!doAuth(request, response, handlerMethod, token, appkey, product))
			throw new ResourceForbiddenException(RestConstants.MESSAGE_RESOURCE_FOBBIDEN);
		return true;
	}

	@SuppressWarnings("unused")
	private boolean doAuthAppKey(String appKey) {
		if (ValidationUtil.isEmpty(internalAppKey)) {
			return true;
		} else {
			return internalAppKey.equals(appKey);
		}

	}

	private void setUser2ThreadLocal(UserSession session) {

		RequestContext.setExeUserId(session.getUserId().toString());

		RequestContext.setAgencyCode(session.getAgencyCode());

		RequestContext.setUserType(UserType.getUserType(session.getUserType()));

	}

	private boolean doAuth(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod,
			String token, String appKey, String product) throws Exception {
		boolean authed = false;
		TyrstOperation tyrestOperation = handlerMethod.getMethodAnnotation(TyrstOperation.class);
		UserSession session = null;
		// 如果 没有忽略验证的注解,证明这个资源需要验证.
		if (ValidationUtil.isEmpty(tyrestOperation) || tyrestOperation.needAuth()) {
			session = securityService.getOrRefreshSession(token, product);
			if(session.isRefresh()){
				// 将新的refreshToken放入response header中,让客户端去刷新token
				response.setHeader(CoreConstants.REFRESH_TOKEN, session.getSsoSessionId());
			}
			authed = AuthAdapter.getAuthAdapter(
					UserType.getUserType(session.getUserType())).doAuth(handlerMethod,appKey);
		} else {
			authed = true;
		}

		if (!ValidationUtil.isEmpty(session)) {
			setUser2ThreadLocal(session);
		}
		return authed;
	}


	/**
	 * 设置请求的跟踪ID
	 * @param traceId
	 */
	private void setTraceId(String traceId) throws Exception {
		if (ValidationUtil.isEmpty(traceId)) {
			traceId = CommonUtil.getUUID();
		}
		RequestContext.setTraceId(traceId);
	}

}