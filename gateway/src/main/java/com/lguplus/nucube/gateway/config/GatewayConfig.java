package com.lguplus.nucube.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("order-service", r -> r
                        .path("/order/**")
                        .filters(f -> f.rewritePath("/order/(?<segment>.*)", "/${segment}"))
                        .uri("lb://order-service")) // ✅ 로드밸런서 사용

                .route("product-service", r -> r
                        .path("/product/**")
                        .filters(f -> f.rewritePath("/product/(?<segment>.*)", "/${segment}"))
                        .uri("lb://product-service")) // ✅ 로드밸런서 사용

                .route("auth-service", r -> r
                        .path("/auth/**")
                        .filters(f -> f.rewritePath("/auth/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8093")) // Direct URL to auth-service

                .build();
    }
}
