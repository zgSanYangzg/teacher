package org.tyrest.core.rest.doc.swagger.core;

import com.google.common.base.Function;
import com.google.common.base.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.scanners.ResourceGroup;

import java.util.Set;

import static com.google.common.base.Strings.*;
import static com.google.common.collect.Sets.*;
import static org.springframework.util.StringUtils.*;
import static org.tyrest.core.rest.doc.swagger.core.StringUtils.*;

@Component
public class ClassOrApiAnnotationResourceGrouping implements ResourceGroupingStrategy {
  private static final Logger LOG = LoggerFactory.getLogger(ClassOrApiAnnotationResourceGrouping.class);

  public ClassOrApiAnnotationResourceGrouping() {
  }

  @Override
  public String getResourceDescription(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
    Class<?> controllerClass = handlerMethod.getBeanType();
    String group = splitCamelCase(controllerClass.getSimpleName(), " ");
    return extractAnnotation(controllerClass, descriptionOrValueExtractor()).or(group);
  }

  @Override
  public Integer getResourcePosition(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
    Class<?> controllerClass = handlerMethod.getBeanType();
    TyrestResource apiAnnotation = AnnotationUtils.findAnnotation(controllerClass, TyrestResource.class);
    if (null != apiAnnotation && hasText(apiAnnotation.value())) {
      return apiAnnotation.position();
    }
    return 0;
  }

  @Override
  public Set<ResourceGroup> getResourceGroups(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
    String group = getClassOrTyrestResourceAnnotationValue(handlerMethod).toLowerCase()
            .replaceAll(" ", "-")
            .replaceAll("/", "");
    LOG.info("Group for method {} was {}", handlerMethod.getMethod().getName(), group);
    Integer position = getResourcePosition(requestMappingInfo, handlerMethod);
    return newHashSet(new ResourceGroup(group.toLowerCase(), position));
  }

  private String getClassOrTyrestResourceAnnotationValue(HandlerMethod handlerMethod) {
    Class<?> controllerClass = handlerMethod.getBeanType();
    String group = splitCamelCase(controllerClass.getSimpleName(), " ");

    return extractAnnotation(controllerClass, valueExtractor()).or(group);
  }

  private Optional<String> extractAnnotation(Class<?> controllerClass,
      Function<TyrestResource, Optional<String>> annotationExtractor) {

    TyrestResource apiAnnotation = AnnotationUtils.findAnnotation(controllerClass, TyrestResource.class);
    return annotationExtractor.apply(apiAnnotation);
  }

  private Function<TyrestResource, Optional<String>> descriptionOrValueExtractor() {
    return new Function<TyrestResource, Optional<String>>() {
      @Override
      public Optional<String> apply(TyrestResource input) {
        return descriptionExtractor().apply(input).or(valueExtractor().apply(input));
      }
    };
  }

  private Function<TyrestResource, Optional<String>> valueExtractor() {
    return new Function<TyrestResource, Optional<String>>() {
      @Override
      public Optional<String> apply(TyrestResource input) {
        if (null != input) {
          String stripSlashes = input.value().replace("/", "");
          return Optional.fromNullable(emptyToNull(stripSlashes));
        }
        return Optional.absent();
      }
    };
  }

  private Function<TyrestResource, Optional<String>> descriptionExtractor() {
    return new Function<TyrestResource, Optional<String>>() {
      @Override
      public Optional<String> apply(TyrestResource input) {
        if (null != input) {
          return Optional.fromNullable(emptyToNull(input.description()));
        }
        return Optional.absent();
      }
    };
  }
}
