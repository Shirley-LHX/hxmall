package com.shirley.hxmall.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenApi() {
        final String loginToken = "BearerAuth";
        return new OpenAPI().info(new Info().title("Simple Boot API")
                        .description("SpringBoot基础框架")
                        .version("v1.0.0")).externalDocs(new ExternalDocumentation()
                        .description("SpringBoot基础框架")
                        .url("http://127.0.0.1:8088"));
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder().group("用户管理系统模块")
                .pathsToMatch("/user/**")
                .build();
    }


    @Bean
    public GroupedOpenApi indexApi() {
        return GroupedOpenApi.builder().group("首页管理系统模块")
                .pathsToMatch("/index/**")
                .build();
    }

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder().group("商品管理系统模块")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi shopCartApi() {
        return GroupedOpenApi.builder().group("购物车管理系统模块")
                .pathsToMatch("/shopcart/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userAddrApi() {
        return GroupedOpenApi.builder().group("用户地址管理系统模块")
                .pathsToMatch("/useraddr/**")
                .build();
    }

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder().group("订单管理系统模块")
                .pathsToMatch("/order/**")
                .build();
    }



}
