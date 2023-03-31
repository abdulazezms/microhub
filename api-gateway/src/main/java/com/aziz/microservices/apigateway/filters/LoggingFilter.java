package com.aziz.microservices.apigateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class LoggingFilter implements GlobalFilter {
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Logging some information about the request.

        logger.info("Path: {} ", exchange.getRequest().getPath());
        logger.info("URI: {}", exchange.getRequest().getURI());
        logger.info("Remote Address: {}", exchange.getRequest().getRemoteAddress());
        logger.info("Local Address: {}", exchange.getRequest().getLocalAddress());
        logger.info("Cookies: {}", exchange.getRequest().getCookies());
        System.out.println("\n\n=============================\n\n");
        //Delegate to the next GatewayFilter in the chain.


        //Things like authentication for all the requests is the right place to be implemented here.
        return chain.filter(exchange);
    }
}
