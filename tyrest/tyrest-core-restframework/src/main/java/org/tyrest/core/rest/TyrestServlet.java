package org.tyrest.core.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.tyrest.core.foundation.context.RequestContext;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrestServlet.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrestServlet.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class TyrestServlet extends DispatcherServlet
{

	private static final Logger logger = LoggerFactory.getLogger(TyrestServlet.class);

	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("Tyrst doService......Begin");
		try {
			super.doService(request, response);
		} finally {
			RequestContext.clean();
		}
		logger.info("Tyrst doService......Done.");
	}

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		logger.info("Tyrst doDispatch......Begin");	
		super.doDispatch(request, response);
		logger.info("Tyrst doDispatch......Done.");
	}
}

/*
 * $Log: av-env.bat,v $
 */