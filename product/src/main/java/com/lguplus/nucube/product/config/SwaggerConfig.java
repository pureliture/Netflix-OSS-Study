package com.lguplus.nucube.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description("MSA 학습용 상품/장바구니 API 문서입니다.")
                        .version("v1.0.0")
                        .contact(new Contact().name("MSA Dev").email("msa@lguplus.com")));
    }
}