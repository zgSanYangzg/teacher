package org.tyrest.core.rest.doc.swagger.core;

import java.util.List;
import java.util.Set;

import com.mangofactory.swagger.models.dto.Authorization;
import com.mangofactory.swagger.models.dto.Operation;
import com.mangofactory.swagger.models.dto.Parameter;
import com.mangofactory.swagger.models.dto.ResponseMessage;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrestOperation.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrestOperation.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class TyrestOperation extends Operation {

	private final String apiLevel;
	private final String funId;
	private final String needAuth;

	public TyrestOperation(String method, String summary, String notes, String responseClass, String nickname,
			int position, List<String> produces, List<String> consumes, List<String> protocol,
			List<Authorization> authorizations, List<Parameter> parameters, Set<ResponseMessage> responseMessages,
			String deprecated, String apiLevel, String fundId, String needAuth) {

		super(method, summary, notes, responseClass, nickname, position, produces, consumes, protocol, authorizations,
				parameters, responseMessages, deprecated);

		this.apiLevel = apiLevel;
		this.funId = fundId;
		this.needAuth = needAuth;
	}

	public String getApiLevel() {
		return apiLevel;
	}

	public String getFunId() {
		return funId;
	}

	public String getNeedAuth() {
		return this.needAuth;
	}
}

/*
 * $Log: av-env.bat,v $
 */