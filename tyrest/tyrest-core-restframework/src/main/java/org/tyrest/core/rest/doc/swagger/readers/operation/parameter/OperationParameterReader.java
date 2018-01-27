package org.tyrest.core.rest.doc.swagger.readers.operation.parameter;

import com.mangofactory.swagger.models.alternates.AlternateTypeProvider;
import com.mangofactory.swagger.models.dto.AllowableValues;
import com.mangofactory.swagger.models.dto.Parameter;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.HandlerMethod;
import org.tyrest.core.rest.doc.swagger.configuration.SwaggerGlobalSettings;
import org.tyrest.core.rest.doc.swagger.core.CommandExecutor;
import org.tyrest.core.rest.doc.swagger.readers.Command;
import org.tyrest.core.rest.doc.swagger.readers.operation.HandlerMethodResolver;
import org.tyrest.core.rest.doc.swagger.readers.operation.ResolvedMethodParameter;
import org.tyrest.core.rest.doc.swagger.readers.operation.SwaggerParameterReader;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.*;

public class OperationParameterReader extends SwaggerParameterReader {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Collection<? extends Parameter> readParameters(final RequestMappingContext context) {
		HandlerMethod handlerMethod = context.getHandlerMethod();
		SwaggerGlobalSettings swaggerGlobalSettings = (SwaggerGlobalSettings) context.get("swaggerGlobalSettings");
		HandlerMethodResolver handlerMethodResolver = new HandlerMethodResolver(
				swaggerGlobalSettings.getTypeResolver());
		AlternateTypeProvider alternateTypeProvider = swaggerGlobalSettings.getAlternateTypeProvider();

		List<ResolvedMethodParameter> methodParameters = handlerMethodResolver.methodParameters(handlerMethod);
		List<Parameter> parameters = newArrayList();

		List<Command<RequestMappingContext>> commandList = newArrayList();
		commandList.add(new ParameterAllowableReader());
		commandList.add(new ParameterDataTypeReader());
		commandList.add(new ParameterTypeReader());
		commandList.add(new ParameterDefaultReader());
		commandList.add(new ParameterDescriptionReader());
		commandList.add(new ParameterMultiplesReader());
		commandList.add(new ParameterNameReader());
		commandList.add(new ParameterRequiredReader());

		ModelAttributeParameterExpander expander = new ModelAttributeParameterExpander(alternateTypeProvider);
		for (ResolvedMethodParameter methodParameter : methodParameters) {

			if (!shouldIgnore(methodParameter, swaggerGlobalSettings.getIgnorableParameterTypes())) {

				RequestMappingContext parameterContext = new RequestMappingContext(context.getRequestMappingInfo(),
						handlerMethod);

				parameterContext.put("methodParameter", methodParameter.getMethodParameter());
				parameterContext.put("resolvedMethodParameter", methodParameter);
				parameterContext.put("swaggerGlobalSettings", swaggerGlobalSettings);

				CommandExecutor<Map<String, Object>, RequestMappingContext> commandExecutor = new CommandExecutor();

				commandExecutor.execute(commandList, parameterContext);

				Map<String, Object> result = parameterContext.getResult();

				if (!shouldExpand(methodParameter)) {
					Parameter parameter = new com.mangofactory.swagger.models.dto.builder.ParameterBuilder()
							.name((String) result.get("name")).description((String) result.get("description"))
							.defaultValue((String) result.get("defaultValue"))
							.required((Boolean) result.get("required"))
							.allowMultiple((Boolean) result.get("allowMultiple"))
							.dataType((String) result.get("dataType"))
							.allowableValues((AllowableValues) result.get("allowableValues"))
							.parameterType((String) result.get("paramType"))
							.parameterAccess((String) result.get("paramAccess")).build();
					parameters.add(parameter);
				} else {
					expander.expand("", methodParameter.getResolvedParameterType().getErasedType(), parameters);
				}
			}
		}
		return parameters;
	}

	@SuppressWarnings("rawtypes")
	private boolean shouldIgnore(final ResolvedMethodParameter parameter, final Set<Class> ignorableParamTypes) {
		if (null != ignorableParamTypes && !ignorableParamTypes.isEmpty()) {

			if (ignorableParamTypes.contains(parameter.getMethodParameter().getParameterType())) {
				return true;
			}
			for (Annotation annotation : parameter.getMethodParameter().getParameterAnnotations()) {
				if (ignorableParamTypes.contains(annotation.annotationType())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean shouldExpand(final ResolvedMethodParameter parameter) {
		for (Annotation annotation : parameter.getMethodParameter().getParameterAnnotations()) {
			if (ModelAttribute.class == annotation.annotationType()) {
				return true;
			}
		}
		return false;
	}
}
