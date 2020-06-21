package com.learn.spring.mybatisservice.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                /*.select()
                .apis(RequestHandlerSelectors.basePackage("com.learn.spring.mybatisservice.controller.MybatisController"))
                .paths(PathSelectors.any())
                .build();*/
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs")
                .description("swagger2 RESTful APIs")
                .termsOfServiceUrl("www.saurav.cn/")
                .contact(new Contact("Saurav-Kumar", "www.saurav.cn/", "sauravstarsk@gmail.com"))
                .version("v1")
                .build();
    }
}
