package com.employee.management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private static final String BASIC_AUTH = "basicAuth";
    private static final String BEARER_AUTH = "Bearer";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.employee.management")).paths(PathSelectors.any()).build().apiInfo(apiInfo())
                .securitySchemes(securitySchemes()).securityContexts(List.of(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Welcome!!" +" Apurv " +
                "Employee Management API.",
                "ManagementApi for IT Employees",
                "2.5",
                "Terms of Service",
                new Contact(
                        "Portfolio","https://github.com/ApurvMishra2209/Portfolio",
                        "apurv3666@gmail.com"),"Apache Licence Version 2.0"
                ,"https://www.apache.org/license.html", Collections.emptyList());
    }

    private List<SecurityScheme> securitySchemes() {
        return List.of(new BasicAuth(BASIC_AUTH), new ApiKey(BEARER_AUTH, "Authorization", "header"));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(List.of(basicAuthReference(), bearerAuthReference())).forPaths(PathSelectors.ant("/products/**")).build();
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference(BASIC_AUTH, new AuthorizationScope[0]);
    }

    private SecurityReference bearerAuthReference() {
        return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
    }
}


