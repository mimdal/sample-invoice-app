package com.github.mimdal.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("INVOICE App. APIs").version("1.0")
				.contact(new Contact("mohamad dehghan", "https://github.com/mimdal",
						"mohamed.dehghan@g000gle.mail"))
				.build();
	}

	@Bean
	public Docket configureControllerPackageAndConverters() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.github.mimdal.api.web")).build()
				.apiInfo(apiInfo());
	}

}