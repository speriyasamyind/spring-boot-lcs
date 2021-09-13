package com.example.lcs.config;

import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Predicates;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	

	  @Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
	      .paths(PathSelectors.any()).build().apiInfo(apiInfo())
	      .directModelSubstitute(LocalTime.class, String.class).useDefaultResponseMessages(false);
	  }

	  ApiInfo apiInfo() {
		    return new ApiInfoBuilder().title("API Specification for Longest Common SubStrings").version("1.0.0")
		      .contact(new Contact("Selvaraj Periyasamy","https://www.linkedin.com/in/selvarajperiyasamy/", "sselvacs007@gmail.com"))
		      .build();
		  }
	  
	  @Bean
	  UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1)
	      .defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false)
	      .docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null).operationsSorter(OperationsSorter.ALPHA)
	      .showExtensions(true).tagsSorter(TagsSorter.ALPHA)
	      .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).validatorUrl(null).build();
	  }
}
