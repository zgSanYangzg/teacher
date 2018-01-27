package org.tyrest.core.rest.doc.swagger.readers.operation.parameter;

import com.mangofactory.swagger.models.Enums;
import com.mangofactory.swagger.models.dto.AllowableValues;
import com.wordnik.swagger.annotations.ApiParam;

import org.springframework.core.MethodParameter;
import org.tyrest.core.rest.doc.swagger.readers.Command;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

import java.lang.annotation.Annotation;

import static com.mangofactory.swagger.models.property.ApiModelProperties.*;

public class ParameterAllowableReader implements Command<RequestMappingContext> {

	@Override
	public void execute(RequestMappingContext context) {
		MethodParameter methodParameter = (MethodParameter) context.get("methodParameter");
		AllowableValues allowableValues = null;
		String allowableValueString = findAnnotatedAllowableValues(methodParameter);
		if (allowableValueString != null && !"".equals(allowableValueString)) {
			allowableValues = allowableValueFromString(allowableValueString);
		} else {
			if (methodParameter.getParameterType().isEnum()) {
				allowableValues = Enums.allowableValues(methodParameter.getParameterType());
			}
			if (methodParameter.getParameterType().isArray()) {
				allowableValues = Enums.allowableValues(methodParameter.getParameterType().getComponentType());
			}
		}
		context.put("allowableValues", allowableValues);
	}

	private String findAnnotatedAllowableValues(MethodParameter methodParameter) {
		Annotation[] methodAnnotations = methodParameter.getParameterAnnotations();
		if (null != methodAnnotations) {
			for (Annotation annotation : methodAnnotations) {
				if (annotation instanceof ApiParam) {
					return ((ApiParam) annotation).allowableValues();
				}
			}
		}
		return null;
	}
}
