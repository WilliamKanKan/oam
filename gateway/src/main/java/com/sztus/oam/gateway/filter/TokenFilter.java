package com.sztus.oam.gateway.filter;

import com.sztus.oam.lib.cache.core.SimpleRedisRepository;
import com.sztus.oam.lib.core.type.RedisKeyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;


@Component
@Slf4j
@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private SimpleRedisRepository simpleRedisRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        if (!isExcludedPath(path)) {
            String token = exchange.getRequest().getHeaders().getFirst("Access-Token");
            String tokenKey = simpleRedisRepository.generateKey(RedisKeyType.ACCOUNT, token);
            String accountStr = simpleRedisRepository.get(tokenKey);
            if (accountStr == null) {
                log.info("Token is empty...");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    private boolean isExcludedPath(String path) {
        return path.contains("/login") || path.contains("/register") || path.contains("/products/search") || path.contains("/admin")|| path.contains("/upload");
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

