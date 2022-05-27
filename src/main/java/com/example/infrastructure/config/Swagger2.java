package com.example.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
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
public class Swagger2 {

    //    http://localhost:8088/swagger-ui.html     原路径
    //    http://localhost:8088/doc.html     原路径

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) // 指定api类型为swagger2
                .apiInfo(apiInfo())                    // 用于定义api文档汇总信息
                .select()
                // 为有@Api注解的Controller生成API文档
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                // 为指定包下controller生成API文档
                //.apis(RequestHandlerSelectors.basePackage("com.yy.controller"))
                //为有@ApiOperation注解的方法生成API文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())   // 所有controller
                .build();


    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("jinx平台接口api")        // 文档页标题
                .contact(new Contact("alianlyy",
                        "https://zljin.github.io/",
                        "leonard_zou@163.com"))        // 联系人信息
                .description("专为lingjin提供的api文档")  // 详细信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("https://zljin.github.io/") // 网站地址
                .build();
    }
}
