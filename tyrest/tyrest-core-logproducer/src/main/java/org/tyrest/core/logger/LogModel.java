package org.tyrest.core.logger;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: LogModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LogModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Document(indexName="tyrest-log")
public class LogModel {
	private String id;
	// searchKey1-6表示不同实体映射过来的查询key
	private String searchKey1;
	private String searchKey2;
	private String searchKey3;
	private String searchKey4;
	private String searchKey5;
	private String searchKey6;
	private String caller;
	private String module;
	private String resource;
	private String operation;
	private String parameters;
	private String apiLevel;
	private String httpMethod;
	private Date recDate;
	private String description;
	private String model;
	// 日志实体
	private String logContent;
	private String result;
	private String errorMessage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchKey1() {
		return searchKey1;
	}

	public void setSearchKey1(String searchKey1) {
		this.searchKey1 = searchKey1;
	}

	public String getSearchKey2() {
		return searchKey2;
	}

	public void setSearchKey2(String searchKey2) {
		this.searchKey2 = searchKey2;
	}

	public String getSearchKey3() {
		return searchKey3;
	}

	public void setSearchKey3(String searchKey3) {
		this.searchKey3 = searchKey3;
	}

	public String getSearchKey4() {
		return searchKey4;
	}

	public void setSearchKey4(String searchKey4) {
		this.searchKey4 = searchKey4;
	}

	public String getSearchKey5() {
		return searchKey5;
	}

	public void setSearchKey5(String searchKey5) {
		this.searchKey5 = searchKey5;
	}

	public String getSearchKey6() {
		return searchKey6;
	}

	public void setSearchKey6(String searchKey6) {
		this.searchKey6 = searchKey6;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getApiLevel() {
		return apiLevel;
	}

	public void setApiLevel(String apiLevel) {
		this.apiLevel = apiLevel;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

}

/*
 * $Log: av-env.bat,v $
 */