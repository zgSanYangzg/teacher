package org.tyrest.core.rest.doc.swagger.readers.operation;

import com.wordnik.swagger.annotations.ApiOperation;

import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class OperationHiddenReader implements RequestMappingReader {
  @Override
  public void execute(RequestMappingContext context) {

    HandlerMethod handlerMethod = context.getHandlerMethod();
    boolean isHidden = false;
    ApiOperation methodAnnotation = handlerMethod.getMethodAnnotation(ApiOperation.class);
    if (null != methodAnnotation) {
      isHidden = methodAnnotation.hidden();
    }
    context.put("isHidden", isHidden);
  }
}
