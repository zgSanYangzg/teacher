package org.tyrest.core.rest.doc.swagger.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wordnik.swagger.annotations.Authorization;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrestResource.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrestResource.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TyrestResource {
	/**
	 * indicates the module which the resource with this annotation belongs to
	 * TODO.
	 * @return
	 */
	String module();
	/**
     * The 'path' that is going to be used to host the API Declaration of the
     * resource.
     * <p/>
     * For JAX-RS resources, this would normally have the same value as the {@code @Path}
     * on the resource, but can be any other value as well. It will serve as the path
     * where the documentation is hosted.
     * <p/>
     * For Servlets, this path has to be the path serving the Servlet.
     * <p/>
     * If the value isn't preceded with a slash, one would be added to it.
     */
    String value();

    /**
     * Corresponds to the `description` field of the Resource Listing API operation.
     * <p/>
     * This should be a short description of the resource.
     */
    String description() default "";

    /**
     * Corresponds to the `basePath` field of the API Declaration.
     * <p/>
     * The `basePath` is derived automatically by Swagger. This property allows
     * overriding the default value if needed.
     *
     * @since 1.3.7
     */
    String basePath() default "";

    /**
     * Optional explicit ordering of this API resource in the Resource Listing.
     */
    int position() default 0;

    /**
     * Corresponds to the `produces` field of the API Declaration.
     * <p/>
     * Takes in comma-separated values of content types.
     * For example, "application/json, application/xml" would suggest this API Resource
     * generates JSON and XML output.
     * <p/>
     * For JAX-RS resources, this would automatically take the value of the {@code @Produces}
     * annotation if such exists. It can also be used to override the {@code @Produces} values
     * for the Swagger documentation.
     */
    String produces() default "";

    /**
     * Corresponds to the `consumes` field of the API Declaration.
     * <p/>
     * Takes in comma-separated values of content types.
     * For example, "application/json, application/xml" would suggest this API Resource
     * accepts JSON and XML input.
     * <p/>
     * For JAX-RS resources, this would automatically take the value of the {@code @Consumes}
     * annotation if such exists. It can also be used to override the {@code @Consumes} values
     * for the Swagger documentation.
     */
    String consumes() default "";

    /**
     * This property is currently not in use.
     */
    String protocols() default "";

    /**
     * Corresponds to the `authorizations` field of the API Declaration.
     * <p/>
     * Takes in a list of the required authorizations for this API Resource.
     * This may be overridden by specific operations.
     *
     * @see Authorization
     */
    Authorization[] authorizations() default @Authorization("");

    /**
     * Hides the api.
     *
     * @since 1.3.8
     */
    boolean hidden() default false;
}

/*
*$Log: av-env.bat,v $
*/