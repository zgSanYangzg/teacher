package org.tyrest.core.rest.doc.swagger.readers.operation.parameter;

import com.fasterxml.classmate.ResolvedType;

import org.springframework.web.multipart.MultipartFile;
import org.tyrest.core.rest.doc.swagger.configuration.SwaggerGlobalSettings;
import org.tyrest.core.rest.doc.swagger.readers.Command;
import org.tyrest.core.rest.doc.swagger.readers.operation.ResolvedMethodParameter;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

import static com.mangofactory.swagger.models.ResolvedTypes.*;

public class ParameterDataTypeReader implements Command<RequestMappingContext> {

  @Override
  public void execute(RequestMappingContext context) {
    ResolvedMethodParameter methodParameter = (ResolvedMethodParameter) context.get("resolvedMethodParameter");
    SwaggerGlobalSettings swaggerGlobalSettings = (SwaggerGlobalSettings) context.get("swaggerGlobalSettings");
    ResolvedType parameterType = methodParameter.getResolvedParameterType();
    parameterType = swaggerGlobalSettings.getAlternateTypeProvider().alternateFor(parameterType);
    //Multi-part file trumps any other annotations
    if (MultipartFile.class.isAssignableFrom(parameterType.getErasedType())) {
      context.put("dataType", "File");
    } else {
      context.put("dataType", responseTypeName(parameterType));
    }
  }

}
