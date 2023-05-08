	package com.zwash.config;
	
	
	import org.springframework.context.annotation.Bean;
	import springfox.documentation.builders.ApiInfoBuilder;
	import springfox.documentation.service.ApiInfo;
	import springfox.documentation.service.Contact;
	import springfox.documentation.spi.DocumentationType;
	import springfox.documentation.spring.web.plugins.Docket;
	import springfox.documentation.swagger2.annotations.EnableSwagger2;
	
	
	@EnableSwagger2
	public class SwaggerConfig {
	    

	    @Bean
		public Docket postsApi() {
			return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
					.apiInfo(metaData()).select().paths(null).build();
		}

	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Tech Interface - Spring Boot Swagger Configuration")
	                .description("\"Swagger configuration for application \"")
	                .version("1.1.0")
	                .license("Apache 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                .contact(new Contact("Tech Interface", "https://www.youtube.com/channel/UCMpJ8m1w9t7EFcF9x8rs02A", "info@techinterface.com"))
	                .build();
	    }
	}
