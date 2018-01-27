package org.tyrest.core.rest.doc.swagger.core;

import java.util.List;
import java.util.Set;

import com.mangofactory.swagger.models.dto.Authorization;
import com.mangofactory.swagger.models.dto.Parameter;
import com.mangofactory.swagger.models.dto.ResponseMessage;
import com.mangofactory.swagger.models.dto.builder.OperationBuilder;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrestOperationBuilder.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrestOperationBuilder.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class TyrestOperationBuilder extends OperationBuilder {

	private String method;
	private String summary;
	private String notes;
	private String responseClass;
	private String nickname;
	private int position;
	private List<String> produces;
	private List<String> consumes;
	private List<String> protocol;
	private List<Authorization> authorizations;
	private List<Parameter> parameters;
	private Set<ResponseMessage> responseMessages;
	private String deprecated;
	private String apiLevel;
	private String funId;
	private String needAuth;

	public TyrestOperationBuilder method(String method) {
		this.method = method;
		return this;
	}

	public TyrestOperationBuilder summary(String summary) {
		this.summary = summary;
		return this;
	}

	public TyrestOperationBuilder notes(String notes) {
		this.notes = notes;
		return this;
	}

	public TyrestOperationBuilder responseClass(String responseClass) {
		this.responseClass = responseClass;
		return this;
	}

	public TyrestOperationBuilder nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public TyrestOperationBuilder position(int position) {
		this.position = position;
		return this;
	}

	public TyrestOperationBuilder produces(List<String> produces) {
		this.produces = produces;
		return this;
	}

	public TyrestOperationBuilder consumes(List<String> consumes) {
		this.consumes = consumes;
		return this;
	}

	public TyrestOperationBuilder protocol(List<String> protocol) {
		this.protocol = protocol;
		return this;
	}

	public TyrestOperationBuilder authorizations(List<Authorization> authorizations) {
		this.authorizations = authorizations;
		return this;
	}

	public TyrestOperationBuilder parameters(List<Parameter> parameters) {
		this.parameters = parameters;
		return this;
	}

	public TyrestOperationBuilder responseMessages(Set<ResponseMessage> responseMessages) {
		this.responseMessages = responseMessages;
		return this;
	}

	public TyrestOperationBuilder deprecated(String deprecated) {
		this.deprecated = deprecated;
		return this;
	}
	
	public TyrestOperationBuilder apiLevel(String apiLevel) {
		this.apiLevel = apiLevel;
		return this;
	}
	
	public TyrestOperationBuilder funId(String funId) {
		this.funId = funId;
		return this;
	}
	
	public TyrestOperationBuilder needAuth(String needAuth){
		this.needAuth = needAuth;
		return this;
	}

	public TyrestOperation build() {
		return new TyrestOperation(method, summary, notes, responseClass, nickname, position, produces, consumes, protocol,
				authorizations, parameters, responseMessages, deprecated,apiLevel,funId,needAuth);
	}
}

/*
 * $Log: av-env.bat,v $
 */