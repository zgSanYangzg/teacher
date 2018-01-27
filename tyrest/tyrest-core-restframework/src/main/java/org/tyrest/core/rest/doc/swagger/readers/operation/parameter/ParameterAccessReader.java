package org.tyrest.core.rest.doc.swagger.readers.operation.parameter;

import com.wordnik.swagger.annotations.ApiParam;

import org.springframework.core.MethodParameter;
import org.tyrest.core.rest.doc.swagger.readers.Command;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class ParameterAccessReader implements Command<RequestMappingContext> {
  @Override
  public void execute(RequestMappingContext context) {
    MethodParameter methodParameter = (MethodParameter) context.get("methodParameter");
    ApiParam apiParam = methodParameter.getParameterAnnotation(ApiParam.class);
    String access = "";
    if (null != apiParam) {
      access = apiParam.access();
    }
    context.put("paramAccess", access);
  }
}
