package com.example.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        /*
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // Pre-Filter Logic
            log.info("Logging filter baseMessage: {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("Logging PRE Filter: Request URI: {}", exchange.getRequest().getURI());
            }

            // Proceed with the filter chain
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Post-Filter Logic
                if (config.isPostLogger()) {
                    log.info("Logging POST filter: Response Code: {}", exchange.getResponse().getStatusCode());
                }
            }));
        });
         */
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // Pre-Filter Logic
            log.info("Logging filter baseMessage: {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("Logging PRE Filter: Request URI: {}", exchange.getRequest().getURI());
            }

            // Proceed with the filter chain
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Post-Filter Logic
                if (config.isPostLogger()) {
                    log.info("Logging POST filter: Response Code: {}", exchange.getResponse().getStatusCode());
                }
            }));
        }, OrderedGatewayFilter.HIGHEST_PRECEDENCE); // 낮을수록 필터의 내부에서 실행됨

        return filter;
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
