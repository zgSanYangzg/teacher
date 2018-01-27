package org.tyrest.core.rest.doc.swagger.readers.operation;

import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class OperationDeprecatedReader implements RequestMappingReader {
  @Override
  public void execute(RequestMappingContext context) {
    boolean isDeprecated = context.getHandlerMethod().getMethodAnnotation(Deprecated.class) != null;
    context.put("deprecated", String.valueOf(isDeprecated));
  }
}
