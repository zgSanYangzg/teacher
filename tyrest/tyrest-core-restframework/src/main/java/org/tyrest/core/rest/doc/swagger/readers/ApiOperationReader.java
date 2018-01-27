package org.tyrest.core.rest.doc.swagger.readers;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.tyrest.core.rest.doc.swagger.authorization.AuthorizationContext;
import org.tyrest.core.rest.doc.swagger.configuration.SwaggerGlobalSettings;
import org.tyrest.core.rest.doc.swagger.core.CommandExecutor;
import org.tyrest.core.rest.doc.swagger.core.TyrestOperation;
import org.tyrest.core.rest.doc.swagger.core.TyrestOperationBuilder;
import org.tyrest.core.rest.doc.swagger.ordering.OperationPositionalOrdering;
import org.tyrest.core.rest.doc.swagger.readers.operation.DefaultResponseMessageReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.TyrestOperationReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationAuthReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationDeprecatedReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationHiddenReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationHttpMethodReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationImplicitParameterReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationImplicitParametersReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationNicknameReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationNotesReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationPositionReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationResponseClassReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.OperationSummaryReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.RequestMappingReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.parameter.OperationParameterReader;
import org.tyrest.core.rest.doc.swagger.scanners.RequestMappingContext;

import com.google.common.collect.Lists;
import com.mangofactory.swagger.models.dto.Authorization;
import com.mangofactory.swagger.models.dto.Operation;
import com.mangofactory.swagger.models.dto.Parameter;
import com.mangofactory.swagger.models.dto.ResponseMessage;

public class ApiOperationReader implements Command<RequestMappingContext> {

	private static final Set<RequestMethod> allRequestMethods = new LinkedHashSet<RequestMethod>(
			Arrays.asList(RequestMethod.values()));

	public static final OperationPositionalOrdering OPERATION_POSITIONAL_ORDERING = new OperationPositionalOrdering();
	private Collection<RequestMappingReader> customAnnotationReaders;

	public ApiOperationReader(Collection<RequestMappingReader> customAnnotationReaders) {
		this.customAnnotationReaders = customAnnotationReaders == null ? Lists.<RequestMappingReader> newArrayList()
				: customAnnotationReaders;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(RequestMappingContext outerContext) {

		RequestMappingInfo requestMappingInfo = outerContext.getRequestMappingInfo();
		HandlerMethod handlerMethod = outerContext.getHandlerMethod();
		SwaggerGlobalSettings swaggerGlobalSettings = (SwaggerGlobalSettings) outerContext.get("swaggerGlobalSettings");
		AuthorizationContext authorizationContext = (AuthorizationContext) outerContext.get("authorizationContext");
		String requestMappingPattern = (String) outerContext.get("requestMappingPattern");
		RequestMethodsRequestCondition requestMethodsRequestCondition = requestMappingInfo.getMethodsCondition();
		Set<ResponseMessage> responseMessages = newHashSet();
		List<Operation> operations = newArrayList();
		TyrestOperation operation = null;

		Set<RequestMethod> requestMethods = requestMethodsRequestCondition.getMethods();
		Set<RequestMethod> supportedMethods = requestMethods == null || requestMethods.isEmpty() ? allRequestMethods
				: requestMethods;

		List<RequestMappingReader> commandList = newArrayList();
		commandList.add(new OperationHiddenReader());
		commandList.add(new OperationAuthReader());
		commandList.add(new OperationHttpMethodReader());
		commandList.add(new OperationSummaryReader());
		commandList.add(new OperationNotesReader());
		commandList.add(new OperationResponseClassReader());
		commandList.add(new OperationNicknameReader());
		commandList.add(new OperationPositionReader());
		commandList.add(new OperationParameterReader());
		commandList.add(new OperationImplicitParametersReader());
		commandList.add(new OperationImplicitParameterReader());
		commandList.add(new OperationParameterRequestConditionReader());
		commandList.add(new MediaTypeReader());
		commandList.add(new DefaultResponseMessageReader());
		commandList.add(new OperationDeprecatedReader());
		commandList.add(new TyrestOperationReader());
		commandList.addAll(customAnnotationReaders);

		// Setup response message list

		Integer currentCount = 0;
		Boolean isHidden;
		for (RequestMethod httpRequestMethod : supportedMethods) {
			CommandExecutor<Map<String, Object>, RequestMappingContext> commandExecutor = new CommandExecutor();

			RequestMappingContext operationRequestMappingContext = new RequestMappingContext(requestMappingInfo,
					handlerMethod);
			operationRequestMappingContext.put("currentCount", currentCount);
			operationRequestMappingContext.put("currentHttpMethod", httpRequestMethod);
			operationRequestMappingContext.put("swaggerGlobalSettings", swaggerGlobalSettings);
			operationRequestMappingContext.put("authorizationContext", authorizationContext);
			operationRequestMappingContext.put("requestMappingPattern", requestMappingPattern);
			operationRequestMappingContext.put("responseMessages", responseMessages);

			Map<String, Object> operationResultMap = commandExecutor.execute(commandList,
					operationRequestMappingContext);
			currentCount = (Integer) operationResultMap.get("currentCount");

			List<String> producesMediaTypes = (List<String>) operationResultMap.get("produces");
			List<String> consumesMediaTypes = (List<String>) operationResultMap.get("consumes");
			List<Parameter> parameterList = (List<Parameter>) operationResultMap.get("parameters");
			List<Authorization> authorizations = (List<Authorization>) operationResultMap.get("authorizations");
			isHidden = (Boolean) operationResultMap.get("isHidden");

			if (!isHidden) {
				operation = new TyrestOperationBuilder()
						.method((String) operationResultMap.get("httpRequestMethod"))
						.summary((String) operationResultMap.get("summary"))
						.notes((String) operationResultMap.get("notes"))
						.responseClass((String) operationResultMap.get("responseClass"))
						.nickname((String) operationResultMap.get("nickname"))
						.position((Integer) operationResultMap.get("position"))
						.produces(producesMediaTypes)
						.consumes(consumesMediaTypes)
						.protocol(new ArrayList<String>(0))
						.authorizations(authorizations)
						.parameters(parameterList)
						.responseMessages((Set) operationResultMap.get("responseMessages"))
						.deprecated((String) operationResultMap.get("deprecated"))
						.apiLevel((String) operationResultMap.get("apiLevel"))
						.funId((String) operationResultMap.get("funId"))
						.needAuth((String)operationResultMap.get("needAuth")).build();
				operations.add(operation);
			}
		}
		Collections.sort(operations, OPERATION_POSITIONAL_ORDERING);
		outerContext.put("operations", operations);
	}

}
