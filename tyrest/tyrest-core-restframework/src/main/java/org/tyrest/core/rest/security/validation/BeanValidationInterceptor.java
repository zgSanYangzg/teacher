package org.tyrest.core.rest.security.validation;

import cc.fozone.validation.BasicValidateService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.metamodel.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseModel;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: BeanValidationInterceptor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:表单Model校验
 *  TODO
 * 
 *  Notes:
 *  $Id: BeanValidationInterceptor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Aspect
@Order(value = 1000)
@Component
public class BeanValidationInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(BeanValidationInterceptor.class);
	
	@Autowired
	private BasicValidateService validationService;

	@Pointcut("execution(* org.tyrest.resource.*.*Resource*.*(..))")
	public void needBeanValidation() {
	}

	@Around("needBeanValidation()")
	public Object validate(ProceedingJoinPoint pjp) throws Throwable {
		Method currentMethod = ((MethodSignature) pjp.getSignature()).getMethod();
		BeanValidation beanValidation = currentMethod.getAnnotation(BeanValidation.class);
		// 如果resource方法上有BeanValidation注解,说明需要表单校验
		if (!ValidationUtil.isEmpty(beanValidation)) {
			Object[] args = pjp.getArgs();
			if (!ValidationUtil.isEmpty(args)) {
				Object bean = null;
				for(Object arg : args){
					if(arg instanceof BaseModel){
						bean = arg;
						break;
					}
				}
				if(ValidationUtil.isEmpty(bean))
					throw new ValidationException("can't find the model that need validation,please make sure the model extends BaseModel!");
				Map<String,String> validationResult = validationService.validate(bean, currentMethod.getName());
				if(!ValidationUtil.isEmpty(validationResult)){
					throw new DataValidateException(formatValidationResult(validationResult));
				}
			}
		}
		return pjp.proceed();
	}

	private static String formatValidationResult(Map<String, String> map) {
		String returnStr = "";
		Set<String> set = map.keySet();
		for (String str : set) {
			returnStr += map.get(str) + ",";
		}
		if (map.size() > 0) {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}
}

/*
 * $Log: av-env.bat,v $
 */