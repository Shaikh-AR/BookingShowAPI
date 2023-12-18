package org.ty.CloneAPIBookMyShow.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class ApplicationConfig extends WebMvcConfigurationSupport{
	
	@Bean
	public Docket getDocket() {
		
		Contact contact = new Contact("Abdul", "AR Company", "Abdul@Gmail.com");
		List<VendorExtension> extension = new ArrayList<>();
		ApiInfo apiInfo = new ApiInfo("CloneBookMyShowApi", "It is Used to Book Movie Ticket", "1.0 first","1 years of free service", contact, "www.infosys.com","www.infosys.com",extension);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("org.ty.CloneAPIBookMyShow")).build().apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
}




