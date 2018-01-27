package org.tyrest.asi.service.core.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.tyrest.asi.face.service.enums.DataType;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.support.SpringContextHelper;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: ASIDataValidatorAdapter.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ASIDataValidatorAdapter.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class ASIDataValidatorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ASIDataValidatorAdapter.class);
	
	private static final String DEFAULT_BEAN_NAME_SUFIX = "Validator";
	
	public static boolean validate(DataType dataType,String value) throws DataValidateException{
		return getValidator(dataType).validate(value);
	}
	
	private static Validator getValidator(DataType dataType) throws DataValidateException{
		Validator validator = null;
		try {
			validator = (Validator) SpringContextHelper.getBean(dataType.name().concat(DEFAULT_BEAN_NAME_SUFIX));
		} catch (NoSuchBeanDefinitionException e) {
			logger.error("no Validator found for DataType {}",dataType.name());
			throw new DataValidateException("no Validator found for DataType " + dataType.name());
		}
		return validator;
	}
}

/*
*$Log: av-env.bat,v $
*/