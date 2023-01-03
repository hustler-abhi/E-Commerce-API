package com.masai.configure;
//
//import java.util.Collections;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
 
@Configuration
public class SwaggerConfigure {
	
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();

	}

	public ApiInfo getInfo() {

		 return new ApiInfo("E-commerce :Backend Application","This is Individual project develop by :-  Abhishek Kumar ", "2.0", "terms & condition apply",new springfox.documentation.service.Contact("Abhishek Kumar", "Myshop.com", "hustlerabhishek895@gmail.com"), null, null, Collections.emptyList());
	}

}
