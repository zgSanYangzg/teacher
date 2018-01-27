package org.tyrest.core.rest.doc.swagger.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tyrest.core.rest.doc.swagger.annotations.ApiIgnore;
import org.tyrest.core.rest.doc.swagger.core.SwaggerCache;

import com.mangofactory.swagger.models.dto.ApiListing;
import com.mangofactory.swagger.models.dto.ResourceListing;

@Controller
@RequestMapping(value = SwaggerConstants.DOCUMENTATION_BASE_PATH)
public class DefaultSwaggerController {

	@Autowired
	private SwaggerCache swaggerCache;

	@ApiIgnore
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ResourceListing> getResourceListing(
			@RequestParam(value = "group", required = false) String swaggerGroup) {
		return getSwaggerResourceListing(swaggerGroup);
	}
	
	private ResponseEntity<ResourceListing> getSwaggerResourceListing(String swaggerGroup) {
		ResponseEntity<ResourceListing> responseEntity = new ResponseEntity<ResourceListing>(HttpStatus.NOT_FOUND);
		ResourceListing resourceListing = null;

		if (null == swaggerGroup) {
			resourceListing = swaggerCache.getSwaggerApiResourceListingMap().values().iterator().next();
		} else {
			if (swaggerCache.getSwaggerApiResourceListingMap().containsKey(swaggerGroup)) {
				resourceListing = swaggerCache.getSwaggerApiResourceListingMap().get(swaggerGroup);
			}
		}
		if (null != resourceListing) {
			responseEntity = new ResponseEntity<ResourceListing>(resourceListing, HttpStatus.OK);
		}
		return responseEntity;
	}

	@ApiIgnore
	@ResponseBody
	@RequestMapping(value = "/{swaggerGroup}/{apiDeclaration}", method = RequestMethod.GET)
	public ResponseEntity<ApiListing> getApiListing(@PathVariable String swaggerGroup,
			@PathVariable String apiDeclaration) {
		return getSwaggerApiListing(swaggerGroup, apiDeclaration);
	}

	private ResponseEntity<ApiListing> getSwaggerApiListing(String swaggerGroup, String apiDeclaration) {
		ResponseEntity<ApiListing> responseEntity = new ResponseEntity<ApiListing>(HttpStatus.NOT_FOUND);
		Map<String, ApiListing> apiListingMap = swaggerCache.getSwaggerApiListingMap().get(swaggerGroup);
		if (null != apiListingMap) {
			ApiListing apiListing = apiListingMap.get(apiDeclaration);
			if (null != apiListing) {
				responseEntity = new ResponseEntity<ApiListing>(apiListing, HttpStatus.OK);
			}
		}
		return responseEntity;
	}
}
