package org.tyrest.core.rest.doc.swagger.readers.operation;

import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class OperationNicknameReader implements RequestMappingReader {
  @Override
  public void execute(RequestMappingContext context) {
    context.put("nickname", context.getHandlerMethod().getMethod().getName());
  }
}
