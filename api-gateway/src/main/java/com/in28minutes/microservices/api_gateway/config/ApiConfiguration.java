package com.in28minutes.microservices.api_gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiConfiguration {

    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Buildable<Route>> routeFunction =
                (p) -> p.path("/get")
                        .filters(
                                f ->      f.addRequestHeader("MyHeader", "MyUri")
                                        .addRequestHeader("Param", "ParamValue")
                        ).uri("http://httpbin.org:80");
        Function<PredicateSpec, Buildable<Route>> currencyExchange =
                p -> p.path("/currency-exchange/**").uri("lb://currency-exchange-service");

        Function<PredicateSpec, Buildable<Route>> currencyConversion=
                p -> p.path("/currency-conversion/**").uri("lb://currency-conversion-service");

        Function<PredicateSpec, Buildable<Route>> currencyConversionFeign=
                p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service");

        Function<PredicateSpec, Buildable<Route>> currencyConversionNew=
                p -> p.path("/currency-conversion-new/**")
                        .filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion-service");




        return builder.routes()
                .route(routeFunction)
                .route(currencyExchange)
                .route(currencyConversion)
                .route(currencyConversionFeign)
                .route(currencyConversionNew)
                .build();
    }
}
