package com.arkeup.lencify.gestion_brevets_mcs.commun.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.arkeup.lencify.gestion_brevets_mcs.infrastructure"))
				.paths(PathSelectors.any()).build()
				.globalOperationParameters(Arrays.asList(new ParameterBuilder().name("Authorization")
						.defaultValue("Bearer {{Token}}").description("Authorization header")
						.modelRef(new ModelRef("Token")).parameterType("header").required(true).build()));
	}

}
