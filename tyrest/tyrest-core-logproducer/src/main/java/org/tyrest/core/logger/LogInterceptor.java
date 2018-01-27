package org.tyrest.core.logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.utils.DateUtil;
import org.tyrest.core.foundation.utils.JsonDateValueProcessor;
import org.tyrest.core.foundation.utils.JsonLongValueProcessor;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseModel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: LogInterceptor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:日志记录的切面应该嵌套在最外层，以免影响到其他的业务AOP(value值越小，优先级越高)
 *  TODO
 * 
 *  Notes:
 *  $Id: LogInterceptor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Aspect
@Order(value = 1)
@Component
public class LogInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	private static final JsonConfig jsonConfig = new JsonConfig();
	
	static{
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new JsonLongValueProcessor());
	}

	@Pointcut("execution(* org.tyrest.resource.*.*Resource*.*(..))")
	public void recordLog() {
	}

	@SuppressWarnings("rawtypes")
	@Around("recordLog()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = null;
		ResponseModel responseModel = null;
		try {
			retVal = pjp.proceed();
			if(retVal instanceof ResponseModel){
				responseModel = (ResponseModel)retVal;
			} 
		} catch (Exception e) {
			responseModel = ResponseHelper.internal_server_error(e.getMessage());
			throw e;
		}finally{
			doRecordLog(pjp, responseModel);
		};

		return retVal;
	}
	
	@SuppressWarnings("rawtypes")
	private void doRecordLog(ProceedingJoinPoint pjp,ResponseModel responseModel){
		Long startTime = System.currentTimeMillis();
		Method invokeMethod = ((MethodSignature) pjp.getSignature()).getMethod();
		Class<?> declaringClass = invokeMethod.getDeclaringClass();
		Object[] args = pjp.getArgs();
		String parameters = "";

		if (convertToJson(args)) {
			parameters = JSONArray.fromObject(args, jsonConfig).toString();
		}
		try {
			StringBuilder logInfo = new StringBuilder();
			logInfo.append("\n***************************************************************************************\n");
			logInfo.append("执行时间: " + ((System.currentTimeMillis() - startTime)) + "耗秒.\n");
			logInfo.append("执行人Id: " + RequestContext.getExeUserId() + ".\n");
			logInfo.append("执行日期: " + DateUtil.getNow(DateUtil.ymd_HMS) + ".\n");
			logInfo.append("方法:" + declaringClass + "." + invokeMethod + " || 请求参数:" + parameters + "\n");
			logInfo.append("最大可用内存:" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M.\n");
			logInfo.append("空  闲   内  存:" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M.\n");
			logInfo.append("当前可用内存:" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M\n");
			logInfo.append("***************************************************************************************");
			logger.info(logInfo.toString());
			
			this.doInternalRecordLog(invokeMethod,parameters,responseModel);
		} catch (Exception e) {
			logger.error("Log error:Class :" + declaringClass.getName() + ".Method:" + invokeMethod.getName() + ".Args:"
					+ parameters + "========", e);
		}finally{
			RequestContext.clean();
		}
	}

	/**
	 * 
	 * 判断是否可以转换为json字符串
	 *
	 * @param args
	 * @return
	 */
	private boolean convertToJson(Object[] args) {
		boolean flag = true;
		if (!ValidationUtil.isEmpty(args)) {
			for (Object obj : args) {
				if (obj instanceof MultipartFile[] || obj instanceof MultipartFile) {
					flag = false;
				}

				if (obj instanceof HttpServletRequest) {
					flag = false;
				}
			}
		}

		return flag;
	}

	/**
	 * TODO.将日志实体发送到JMS,由logger应用负责记录日志
	 * 
	 * @param callee
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private void doInternalRecordLog(Method callee,String parameters,ResponseModel responseModel) throws Exception {
		TyrestResource resource = callee.getDeclaringClass().getAnnotation(TyrestResource.class);
		TyrstOperation operation = callee.getAnnotation(TyrstOperation.class);
		Unloggable unLoggable = callee.getAnnotation(Unloggable.class);
		RequestMethod httpMethod = callee.getAnnotation(RequestMapping.class).method()[0];
		// #1先判断该API是否需要记录日志,如果不需要记录日志则直接返回
		if (!ValidationUtil.isEmpty(unLoggable) || httpMethod.equals(RequestMethod.GET) ) return;
		// #2如果该API需要记录日志,则解析日志的实体类型
		String logEntityType = callee.getDeclaringClass().getSimpleName().replaceAll("Resource.+", "") + "Model";
		LogEntityEnum logEntityEnum = LogEntityEnum.getEntityEnum(logEntityType);
		
		List<LogModel> logModels = new ArrayList<LogModel>();
		LogModel newLog = null;
		for(Object  logEntity : this.parseLogEntities(responseModel)){
			newLog = new LogModel();
			if (!ValidationUtil.isEmpty(logEntityEnum)) {
				JSONObject logEntityJSON = JSONObject.fromObject(logEntity);
				Map<String, String> entityKeyMapping = logEntityEnum.getKeyMapping();
				newLog.setSearchKey1(this.getJSONString(logEntityJSON, entityKeyMapping.get("searchKey1")));
				newLog.setSearchKey2(this.getJSONString(logEntityJSON, entityKeyMapping.get("searchKey2")));
				newLog.setSearchKey3(this.getJSONString(logEntityJSON, entityKeyMapping.get("searchKey3")));
				newLog.setSearchKey4(this.getJSONString(logEntityJSON, entityKeyMapping.get("searchKey4")));
				newLog.setSearchKey5(this.getJSONString(logEntityJSON, entityKeyMapping.get("searchKey5")));
				newLog.setSearchKey6(this.getJSONString(logEntityJSON, entityKeyMapping.get("searchKey6")));
			}
			newLog.setCaller(RequestContext.getExeUserId());
			newLog.setModule(resource.module());
			newLog.setResource(resource.description() + "(" + resource.value() + ")");
			newLog.setOperation(operation.name());
			newLog.setParameters(parameters);
			newLog.setApiLevel(operation.ApiLevel().toString());
			newLog.setHttpMethod(httpMethod.name());
			newLog.setModel(logEntityType);
			newLog.setDescription(operation.description());
			newLog.setRecDate(new Date());
			newLog.setLogContent(JSONObject.fromObject(logEntity,jsonConfig).toString());
			if(responseModel.getStatus() == HttpStatus.OK.value()){
				newLog.setResult(LogConstants.SUCCESS);
			}else{ 
				newLog.setResult(LogConstants.FAILURE);
				newLog.setErrorMessage(responseModel.getMessage());
			}
			logModels.add(newLog);
		}
		kafkaProducer.send(JSONArray.fromObject(logModels,jsonConfig).toString());
	}
	/*
	 * 从JSON中获取指定字段的值
	 */
	private String getJSONString(JSONObject sourceJSON,String targetField){
		String result = "";
		if(!ValidationUtil.isEmpty(sourceJSON) 
				&& !ValidationUtil.isEmpty(targetField)
				&& sourceJSON.containsKey(targetField)){
			result = sourceJSON.getString(targetField);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> parseLogEntities(ResponseModel responseModel){
		List<Object> logEntities = new ArrayList<Object>();
		Object requestResult = responseModel.getResult();
		if(!ValidationUtil.isEmpty(requestResult)){
			Object currentLogEntity = null;
			if(requestResult instanceof List){
				List<Object> resultList = (List)requestResult;
				for(Object source : resultList){
					currentLogEntity = this.parseLogEntity(source);
					if(!ValidationUtil.isEmpty(currentLogEntity)){
						logEntities.add(currentLogEntity);
					}
				}
			}else{
				currentLogEntity = this.parseLogEntity(requestResult);
				if(!ValidationUtil.isEmpty(currentLogEntity)){
					logEntities.add(currentLogEntity);
				}
			}
		}else{
			logEntities.add(new BaseModel());
		}
		return logEntities;
	}
	
	private Object parseLogEntity(Object source){
		Object result = source;
		if(!(source instanceof BaseModel) 
				&& !(source instanceof Map)){
			result = new BaseModel(null, null, null, null, null, null, null, source.toString());
			
		}
		return result;
	}
}
/*
 * $Log: av-env.bat,v $
 */