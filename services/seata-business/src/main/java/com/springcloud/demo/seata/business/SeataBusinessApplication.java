package com.springcloud.demo.seata.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 由于该服务不需要连接数据库，所以排除数据源自动配置类，否则服务启动失败
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class SeataBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataBusinessApplication.class, args);
    }
}
