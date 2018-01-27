package org.tyrest.core.rest.doc.swagger.readers.operation;

import com.wordnik.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class OperationNotesReader implements RequestMappingReader {
  @Override
  public void execute(RequestMappingContext context) {

    HandlerMethod handlerMethod = context.getHandlerMethod();
    String notes = handlerMethod.getMethod().getName();
    ApiOperation methodAnnotation = handlerMethod.getMethodAnnotation(ApiOperation.class);
    if (null != methodAnnotation && StringUtils.hasText(methodAnnotation.notes())) {
      notes = methodAnnotation.notes();
    }
    context.put("notes", notes);
  }
}
