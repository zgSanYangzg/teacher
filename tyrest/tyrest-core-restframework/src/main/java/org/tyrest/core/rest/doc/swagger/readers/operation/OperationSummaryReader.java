package org.tyrest.core.rest.doc.swagger.readers.operation;

import com.wordnik.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

public class OperationSummaryReader implements RequestMappingReader {
  @Override
  public void execute(RequestMappingContext context) {
    HandlerMethod handlerMethod = context.getHandlerMethod();
    ApiOperation apiOperationAnnotation = context.getApiOperationAnnotation();
    TyrstOperation tyrestOperation = (TyrstOperation) handlerMethod.getMethodAnnotation(TyrstOperation.class);

    String summary = handlerMethod.getMethod().getName();
    if(null != tyrestOperation && StringUtils.hasText(tyrestOperation.description())){
    	summary = tyrestOperation.description();
    }else if (null != apiOperationAnnotation && StringUtils.hasText(apiOperationAnnotation.value())) {
      summary = apiOperationAnnotation.value();
    }
    context.put("summary", summary);
  }


}
