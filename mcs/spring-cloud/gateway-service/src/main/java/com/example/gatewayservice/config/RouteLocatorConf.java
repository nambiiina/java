package com.example.gatewayservice.config;

import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConf {
//    @Bean
    public RouteLocator staticRouteLocator(RouteLocatorBuilder builder) {
        /**
         * Static route
         */
//        return builder.routes()
//                .route("r1", r -> r.path("/customers/**")
//                    .uri("http://localhost:8081"))
//                .route("r2", r -> r.path("/products/**")
//                    .uri("http://localhost:8082"))
//                .build();
        /**
         * lb Static route
         */
        return builder.routes()
                .route("r1", r -> r.path("/customers/**")
                    .uri("lb://CUSTOMER-SERVICE"))
                .route("r2", r -> r.path("/products/**")
                    .uri("lb://INVENTORY-SERVICE"))
                .build();
    }

    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient reactiveDiscoveryClient, DiscoveryLocatorProperties discoveryLocatorProperties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
    }
}
