package org.tyrest.core.rest.doc.swagger.readers.operation.parameter;

import com.wordnik.swagger.annotations.ApiParam;

import org.springframework.core.MethodParameter;
import org.tyrest.core.rest.doc.swagger.readers.Command;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class ParameterMultiplesReader implements Command<RequestMappingContext> {
  @Override
  public void execute(RequestMappingContext context) {
    MethodParameter methodParameter = (MethodParameter) context.get("methodParameter");
    ApiParam apiParam = methodParameter.getParameterAnnotation(ApiParam.class);

    Boolean allowMultiple = Boolean.FALSE;
    Class<?> parameterType = methodParameter.getParameterType();
    if (null != apiParam && !(parameterType != null
            && parameterType.isArray() && parameterType.getComponentType().isEnum())) {
      allowMultiple = apiParam.allowMultiple();
    } else {
      allowMultiple = parameterType.isArray()
              || Iterable.class.isAssignableFrom(parameterType);
    }
    context.put("allowMultiple", allowMultiple);
  }
}
