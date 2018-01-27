package org.tyrest.core.rest.doc.swagger.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.utils.PropertyUtil;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.rest.doc.swagger.controllers.SwaggerConstants;
import org.tyrest.core.rest.doc.swagger.core.SwaggerCache;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.model.OperationModel;
import org.tyrest.security.face.service.OperationService;

import com.mangofactory.swagger.models.dto.ApiDescription;
import com.mangofactory.swagger.models.dto.ApiListing;
import com.mangofactory.swagger.models.dto.ApiListingReference;
import com.mangofactory.swagger.models.dto.ResourceListing;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: TyrestWebModuleInitializer.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: TyrestWebModuleInitializer.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Component
public class TyrestWebModuleInitializer {
	
	public static final String zkroot_documentation = "/documentation";
	public static final char splitor = '/';
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SwaggerCache swaggerCache;
	
	@Autowired
	private OperationService operationService;
	
	public void initialize() throws Exception{
		this.registerModule();
		this.registerOperations();
	}
	
	/**
	 * TODO.注册模块,将此微服务模块注册到zookeeper
	 * @param applicationContext
	 */
	private void registerModule(){
		String docRegisteration = PropertyUtil.get("docRegisteration");
		if(CoreConstants.COMMON_Y.equals(docRegisteration)){
			WebApplicationContext webAppContext = (WebApplicationContext)applicationContext;
			ServletContext currentServletContext = webAppContext.getServletContext();
			String displayName = currentServletContext.getServletContextName();
			String contextPath = currentServletContext.getContextPath();
			
			String zkServer = PropertyUtil.get("zookeeper.url");
			String docName = splitor + PropertyUtil.get("docName");
			String docBase = PropertyUtil.get("docBase");
	        ZkClient zkClient = new ZkClient(zkServer,40000,40000,new SerializableSerializer());
	        
	        if(!zkClient.exists(zkroot_documentation)){
	        	zkClient.createPersistent(zkroot_documentation);
	        }
	        
	        if(!zkClient.exists(zkroot_documentation + docName)){
	        	zkClient.createPersistent(zkroot_documentation + docName,docBase);
	        }else{
	        	zkClient.writeData(zkroot_documentation + docName,docBase);
	        }
	        
	        if(!zkClient.exists(zkroot_documentation + docName + contextPath)){
	        	zkClient.createPersistent(zkroot_documentation + docName + contextPath,displayName);
	        }else{
	        	zkClient.writeData(zkroot_documentation + docName + contextPath, displayName);
	        }
	        
	        zkClient.close();
		}
	}
	/**
	 * TODO.将该模块的所有操作注册到数据库中
	 * @throws Exception 
	 */
	private void registerOperations() throws Exception{
		List<OperationModel> result = new ArrayList<OperationModel>();
		String currentResourceCode = null;
		String currentResourceName = null;
		
		//#1.获取所有的Resource概要
		ResourceListing resourceListing = swaggerCache.getSwaggerApiResourceListingMap().values().iterator().next();
		
		for(ApiListingReference apiListingReference : resourceListing.getApis()){
			currentResourceCode = apiListingReference.getPath().replaceAll("\\/"+SwaggerConstants.DEFAULT_SWAGGER_GROUP+"\\/","");
			currentResourceName = apiListingReference.getDescription();
			result.addAll(this.scanTyrestOperations(swaggerCache,currentResourceCode, currentResourceName));
		}
		
		operationService.syncOperations(result);
	}
	
	private List<OperationModel> scanTyrestOperations(SwaggerCache swaggerCache,String resourceCode,String resourceName){
		List<OperationModel> result = new ArrayList<OperationModel>();
		OperationModel currentResource = null;
		
		Map<String, ApiListing> apiListingMap = swaggerCache.getSwaggerApiListingMap().get(SwaggerConstants.DEFAULT_SWAGGER_GROUP);
		if (!ValidationUtil.isEmpty(apiListingMap)) {
			ApiListing apiListing = apiListingMap.get(resourceCode);
			if (!ValidationUtil.isEmpty(apiListing)) {
				List<ApiDescription> apiDescriptions = apiListing.getApis();
				if(!ValidationUtil.isEmpty(apiDescriptions)){
					org.tyrest.core.rest.doc.swagger.core.TyrestOperation currentOperation = null;
					for(ApiDescription apiDescription : apiDescriptions){
						if(!ValidationUtil.isEmpty(apiDescription.getOperations())){
							currentOperation = (org.tyrest.core.rest.doc.swagger.core.TyrestOperation)apiDescription.getOperations().get(0);
							currentResource = new OperationModel();
							
							currentResource.setReqUrl(apiDescription.getPath());
							currentResource.setResourceCode(resourceCode);
							currentResource.setResourceName(resourceName);
							currentResource.setResType(SecurityConstants.RES_TYPE_API);
							currentResource.setOprateCode(currentOperation.getNickname());
							currentResource.setOprateDescription(currentOperation.getSummary());
							currentResource.setLevelCode(currentOperation.getApiLevel());
							currentResource.setReqMode(currentOperation.getMethod());
							currentResource.setFuncId(currentOperation.getFunId());
							currentResource.setIgnoreAuth(Boolean.valueOf(currentOperation.getNeedAuth())
									? CoreConstants.COMMON_N : CoreConstants.COMMON_Y);
							result.add(currentResource);
						}
					}
				}
			}
		}
		return result;
	}
}

/*
*$Log: av-env.bat,v $
*/