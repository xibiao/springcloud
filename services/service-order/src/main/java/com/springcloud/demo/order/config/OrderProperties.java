package com.springcloud.demo.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 使用@ConfigurationProperties代替@Value+@RefreshScope，配置批量绑定
 * Nacos配置的属性是：order.timeout、order.auto-confirm和order.db-url
 */
@Component
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {
    private String timeout;

    private String autoConfirm;

    private String dbUrl;
}
