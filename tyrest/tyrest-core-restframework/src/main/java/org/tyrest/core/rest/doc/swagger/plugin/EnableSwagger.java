package org.tyrest.core.rest.doc.swagger.plugin;

import org.springframework.context.annotation.Import;
import org.tyrest.core.rest.doc.swagger.configuration.SpringSwaggerConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates that Swagger support should be enabled.
 *
 * This should be applied to a Spring java config and should have an accompanying '@Configuration' annotation.
 *
 * Loads all required beans defined in @see SpringSwaggerConfig
 *
 * @see org.tyrest.core.rest.doc.swagger.plugin.SwaggerSpringMvcPlugin
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Documented
@Import(SpringSwaggerConfig.class)
public @interface EnableSwagger {
}
