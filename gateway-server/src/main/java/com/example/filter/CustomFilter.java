package com.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    public GatewayFilter apply(Config config) {
        // Custom Pre Filter.
        // 활용예시: 요청에서 JWT를 추출하고 인증을 수행시키도록 할 수 있음

        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter: request uri -> {}", request.getId());
            // Custom Post Filter.
            // 활용예시: 에러 코드에 따라서 에러 응답을 처리하도록 할 수 있음
            // Suppose we can call error response handler based on error code.
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom POST filter: response code -> {}", response.getStatusCode());
            }));

        });
    }

    public static class Config {
        // Put the configuration properties here
        // e.g., private String someProperty;
    }

}
