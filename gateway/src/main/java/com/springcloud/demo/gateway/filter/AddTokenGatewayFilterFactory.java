package com.springcloud.demo.gateway.filter;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class AddTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<AddTokenGatewayFilterFactory.Config> {
    public AddTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String value = UUID.randomUUID().toString();
                if ("request".equals(config.getValue())) {
                    ServerHttpRequest request = exchange.getRequest();
                    request = request.mutate().header(config.getName(), value).build();
                    return chain.filter(exchange.mutate().request(request).build());
                } else if ("response".equals(config.getValue())) {
                    return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                        ServerHttpResponse response = exchange.getResponse();
                        HttpHeaders headers = response.getHeaders();
                        headers.add(config.getName(), value);
                    }));
                } else {
                    return chain.filter(exchange);
                }
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // 列举自定义网关过滤器可以使用的参数，需要与静态内部类Config中的属性名一致
        return Arrays.asList(GatewayFilter.NAME_KEY, GatewayFilter.VALUE_KEY);
    }


    @Validated
    public static class Config {
        @NotEmpty
        protected String name;

        @NotEmpty
        protected String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
