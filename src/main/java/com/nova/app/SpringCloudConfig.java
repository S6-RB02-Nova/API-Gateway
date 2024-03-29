package com.nova.app;

import com.nova.app.logic.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/auth/**")
                .filters(f -> f.filter(filter))
                .uri("lb://authapi"))
            .route(r -> r.path("/query/**")
                .filters(f -> f.filter(filter))
                .uri("lb://queryapi"))
            .route(r -> r.path("/command/**")
                .filters(f -> f.filter(filter))
                .uri("lb://commandapi"))
            .build();
    }
}
