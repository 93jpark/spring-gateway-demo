package com.example.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class FilterConfig {

    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                //first-service라는 값이 요청이 오면 이동 작업
                .route(r-> r.path("/first-service/**")
                        //필터 적용해서, 필터 객체에 request header를 추가(key-value)
                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                //response Header 반환
                                .addResponseHeader("first-response", "first-response-header"))
                        //uri로 이동
                        .uri("http://localhost:8081"))
                //second-service라는 값이 요청이 오면 이동 작업
                .route(r-> r.path("/second-service/**")
                        //필터 적용해서, 필터 객체에 request header를 추가(key-value)
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                //response Header 반환
                                .addResponseHeader("second-response", "second-response-header"))
                        //uri로 이동
                        .uri("http://localhost:8082"))
                .build();
    }

}
