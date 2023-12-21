package com.spring.template.silver.app.usecase.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@AllArgsConstructor
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .securitySchemes(securityScheme())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.spring.template.silver.app.entrypoint.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Demo Spring template Service")
        .description("Demo Spring template Service.")
        .version("1.0")
        .termsOfServiceUrl("Terms of service")
        .contact(new Contact("Silver Neko", "", ""))
        .license("License of API")
        .licenseUrl("API license URL")
        .build();
  }

  private List<SecurityScheme> securityScheme() {
    List<SecurityScheme> securitySchemes = new ArrayList<>();
    securitySchemes.add(new ApiKey("Bearer", "Authorization", "header"));
    return securitySchemes;
  }

}