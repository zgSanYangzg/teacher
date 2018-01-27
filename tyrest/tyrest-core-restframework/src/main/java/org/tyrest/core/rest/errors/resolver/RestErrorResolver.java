package org.tyrest.core.rest.errors.resolver;

import org.springframework.web.context.request.ServletWebRequest;
import org.tyrest.core.rest.errors.RestError;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: RestErrorResolver.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RestErrorResolver.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public interface RestErrorResolver {

    /**
     * Returns a {@code RestError} instance to render as the response body based on the given exception.
     *
     * @param request current {@link org.springframework.web.context.request.ServletWebRequest} that can be used to obtain the source request/response pair.
     * @param handler the executed handler, or <code>null</code> if none chosen at the time of the exception
     *                (for example, if multipart resolution failed)
     * @param ex      the exception that was thrown during handler execution
     * @return a resolved {@code RestError} instance to render as the response body or <code>null</code> for default
     *         processing
     */
    RestError resolveError(ServletWebRequest request, Object handler, Exception ex);
}
