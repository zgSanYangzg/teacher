package org.tyrest.core.rest.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tyrest.core.rest.doc.swagger.configuration.SpringSwaggerConfig;
import org.tyrest.core.rest.doc.swagger.plugin.EnableSwagger;
import org.tyrest.core.rest.doc.swagger.plugin.SwaggerSpringMvcPlugin;

import com.mangofactory.swagger.models.dto.ApiInfo;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: SwaggerConfig.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SwaggerConfig.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Configuration
@EnableSwagger
public class SwaggerConfig
{
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * Required to autowire SpringSwaggerConfig
	 */
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
	{
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc framework - allowing for multiple swagger
	 * groups i.e. same code base multiple swagger resource listings.
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation()
	{
		SwaggerSpringMvcPlugin ssmp = new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(
							new ApiInfo("Tyrest接口说明以及规范文档.",
									"这里是Tyrest接口说明文档站点,在这里你可以点击API了解详细内容并且对它们进行测试.",
									"","","","")
						).build();
		return ssmp;
	}
}
