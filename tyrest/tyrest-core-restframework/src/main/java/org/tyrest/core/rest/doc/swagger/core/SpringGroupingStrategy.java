package org.tyrest.core.rest.doc.swagger.core;

import com.google.common.base.Optional;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.scanners.ResourceGroup;

import java.util.Set;

import static com.google.common.base.Strings.*;
import static com.google.common.collect.Sets.*;
import static java.util.Arrays.*;
import static org.tyrest.core.rest.doc.swagger.core.StringUtils.*;

/**
 * TODO - fix or remove
 * There are a lot of cases this strategy does not cover.
 * - request mappings of varying path depths,
 * - Paths beginning with path variables
 * - Controllers without top level request mappings
 */
public class SpringGroupingStrategy implements ResourceGroupingStrategy {
  @Override
  public Set<ResourceGroup> getResourceGroups(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
    return groups(handlerMethod);
  }

  @Override
  public String getResourceDescription(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
    return getDescription(handlerMethod);
  }

  @Override
  public Integer getResourcePosition(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
    return 0;
  }

  private Set<ResourceGroup> groups(HandlerMethod handlerMethod) {
    Class<?> controllerClass = handlerMethod.getBeanType();
    String defaultGroup = String.format("%s", splitCamelCase(controllerClass.getSimpleName(), "-"));

    Optional<RequestMapping> requestMapping
            = Optional.fromNullable(AnnotationUtils.findAnnotation(controllerClass, RequestMapping.class));
    if (requestMapping.isPresent()) {
      Set<ResourceGroup> groups = newHashSet();
      //noinspection ConstantConditions
      for (String groupFromReqMapping : asList(requestMapping.get().value())) {
        if (!isNullOrEmpty(groupFromReqMapping)) {
          String groupName = maybeChompLeadingSlash(firstPathSegment(groupFromReqMapping));
          groups.add(new ResourceGroup(groupName));
        }
      }
      if (groups.size() > 0) {
        return groups;
      }
    }
    return newHashSet(new ResourceGroup(maybeChompLeadingSlash(defaultGroup.toLowerCase())));
  }

  private String getDescription(HandlerMethod handlerMethod) {
    Class<?> controllerClass = handlerMethod.getBeanType();
    String description = splitCamelCase(controllerClass.getSimpleName(), " ");

    TyrestResource apiAnnotation = AnnotationUtils.findAnnotation(controllerClass, TyrestResource.class);
    if (null != apiAnnotation) {
      String descriptionFromAnnotation = Optional.fromNullable(emptyToNull(apiAnnotation.value()))
              .or(apiAnnotation.description());
      if (!isNullOrEmpty(descriptionFromAnnotation)) {
        return descriptionFromAnnotation;
      }
    }
    return description;
  }
}
