package org.tyrest.core.rest.doc.swagger.scanners;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mangofactory.swagger.models.ModelProvider;
import com.mangofactory.swagger.models.dto.builder.ApiListingBuilder;
import com.mangofactory.swagger.models.dto.ApiDescription;
import com.mangofactory.swagger.models.dto.ApiListing;
import com.mangofactory.swagger.models.dto.Authorization;
import com.mangofactory.swagger.models.dto.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tyrest.core.rest.doc.swagger.authorization.AuthorizationContext;
import org.tyrest.core.rest.doc.swagger.configuration.SwaggerGlobalSettings;
import org.tyrest.core.rest.doc.swagger.core.CommandExecutor;
import org.tyrest.core.rest.doc.swagger.core.RequestMappingEvaluator;
import org.tyrest.core.rest.doc.swagger.core.ResourceGroupingStrategy;
import org.tyrest.core.rest.doc.swagger.ordering.ApiDescriptionLexicographicalOrdering;
import org.tyrest.core.rest.doc.swagger.paths.SwaggerPathProvider;
import org.tyrest.core.rest.doc.swagger.readers.ApiDescriptionReader;
import org.tyrest.core.rest.doc.swagger.readers.ApiModelReader;
import org.tyrest.core.rest.doc.swagger.readers.Command;
import org.tyrest.core.rest.doc.swagger.readers.MediaTypeReader;
import org.tyrest.core.rest.doc.swagger.readers.operation.RequestMappingReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;

public class ApiListingScanner {
	private static final Logger log = LoggerFactory.getLogger(ApiListingScanner.class);
	private static final String SWAGGER_VERSION = "1.2";

	private Map<ResourceGroup, List<RequestMappingContext>> resourceGroupRequestMappings;
	private SwaggerPathProvider swaggerPathProvider;
	private SwaggerGlobalSettings swaggerGlobalSettings;
	private AuthorizationContext authorizationContext;
	private final ModelProvider modelProvider;
	private Ordering<ApiDescription> apiDescriptionOrdering = new ApiDescriptionLexicographicalOrdering();
	private Collection<RequestMappingReader> customAnnotationReaders;
	private final RequestMappingEvaluator requestMappingEvaluator;
	private final String apiVersion;

	public ApiListingScanner(Map<ResourceGroup, List<RequestMappingContext>> resourceGroupRequestMappings,
			SwaggerPathProvider swaggerPathProvider, ModelProvider modelProvider,
			AuthorizationContext authorizationContext, Collection<RequestMappingReader> customAnnotationReaders,
			RequestMappingEvaluator requestMappingEvaluator, String apiVersion) {

		this.resourceGroupRequestMappings = resourceGroupRequestMappings;
		this.swaggerPathProvider = swaggerPathProvider;
		this.authorizationContext = authorizationContext;
		this.modelProvider = modelProvider;
		this.customAnnotationReaders = customAnnotationReaders;
		this.requestMappingEvaluator = requestMappingEvaluator;
		this.apiVersion = apiVersion;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, ApiListing> scan() {
		Map<String, ApiListing> apiListingMap = newHashMap();
		int position = 0;

		if (null == resourceGroupRequestMappings) {
			log.error("resourceGroupRequestMappings should not be null.");
		} else {
			for (Map.Entry<ResourceGroup, List<RequestMappingContext>> entry : resourceGroupRequestMappings
					.entrySet()) {

				ResourceGroup resourceGroup = entry.getKey();

				Set<String> produces = new LinkedHashSet<String>(2);
				Set<String> consumes = new LinkedHashSet<String>(2);
				Set<ApiDescription> apiDescriptions = newHashSet();

				List<Command<RequestMappingContext>> readers = newArrayList();
				readers.add(new MediaTypeReader());
				readers.add(new ApiDescriptionReader(swaggerPathProvider, customAnnotationReaders,
						requestMappingEvaluator));
				readers.add(new ApiModelReader(modelProvider));

				Map<String, Model> models = new LinkedHashMap<String, Model>();
				for (RequestMappingContext each : entry.getValue()) {

					CommandExecutor<Map<String, Object>, RequestMappingContext> commandExecutor = new CommandExecutor();
					each.put("authorizationContext", authorizationContext);
					each.put("swaggerGlobalSettings", swaggerGlobalSettings);
					each.put("currentResourceGroup", resourceGroup);

					Map<String, Object> results = commandExecutor.execute(readers, each);

					List<String> producesMediaTypes = (List<String>) results.get("produces");
					List<String> consumesMediaTypes = (List<String>) results.get("consumes");
					Map<String, Model> swaggerModels = (Map<String, Model>) results.get("models");
					if (null != swaggerModels) {
						models.putAll(swaggerModels);
					}
					produces.addAll(producesMediaTypes);
					consumes.addAll(consumesMediaTypes);

					List<ApiDescription> apiDescriptionList = (List<ApiDescription>) results.get("apiDescriptionList");
					apiDescriptions.addAll(apiDescriptionList);
				}

				List<Authorization> authorizations = new ArrayList<Authorization>();
				if (null != authorizationContext) {
					authorizations = authorizationContext.getScalaAuthorizations();
				}

				ArrayList sortedDescriptions = new ArrayList(apiDescriptions);
				Collections.sort(sortedDescriptions, this.apiDescriptionOrdering);

				String resourcePath = longestCommonPath(sortedDescriptions);

				ApiListing apiListing = new ApiListingBuilder().apiVersion(apiVersion).swaggerVersion(SWAGGER_VERSION)
						.basePath(swaggerPathProvider.getApplicationBasePath()).resourcePath(resourcePath)
						.produces(Lists.newArrayList(produces)).consumes(Lists.newArrayList(consumes))
						.protocol(new ArrayList<String>()).authorizations(authorizations).apis(sortedDescriptions)
						.models(models).description(null).position(position++).build();

				apiListingMap.put(resourceGroup.getGroupName(), apiListing);
			}
		}
		return apiListingMap;
	}

	private String longestCommonPath(ArrayList<ApiDescription> apiDescriptions) {
		List<String> commons = newArrayList();
		if (null == apiDescriptions || apiDescriptions.isEmpty()) {
			return null;
		}
		List<String> firstWords = urlParts(apiDescriptions.get(0));

		for (int position = 0; position < firstWords.size(); position++) {
			String word = firstWords.get(position);
			boolean allContain = true;
			for (int i = 1; i < apiDescriptions.size(); i++) {
				List<String> words = urlParts(apiDescriptions.get(i));
				if (words.size() < position + 1 || !words.get(position).equals(word)) {
					allContain = false;
					break;
				}
			}
			if (allContain) {
				commons.add(word);
			}
		}
		Joiner joiner = Joiner.on("/").skipNulls();
		return "/" + joiner.join(commons);
	}

	private List<String> urlParts(ApiDescription apiDescription) {
		return Splitter.on('/').omitEmptyStrings().trimResults().splitToList(apiDescription.getPath());
	}

	public SwaggerGlobalSettings getSwaggerGlobalSettings() {
		return swaggerGlobalSettings;
	}

	public void setSwaggerGlobalSettings(SwaggerGlobalSettings swaggerGlobalSettings) {
		this.swaggerGlobalSettings = swaggerGlobalSettings;
	}

	@Deprecated // As of 0.9.3 (not used)
	public void setResourceGroupingStrategy(ResourceGroupingStrategy resourceGroupingStrategy) {
	}

	public void setAuthorizationContext(AuthorizationContext authorizationContext) {
		this.authorizationContext = authorizationContext;
	}

	public void setApiDescriptionOrdering(Ordering<ApiDescription> apiDescriptionOrdering) {
		this.apiDescriptionOrdering = apiDescriptionOrdering;
	}
}
