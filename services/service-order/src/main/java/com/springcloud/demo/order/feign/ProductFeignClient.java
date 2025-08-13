package com.springcloud.demo.order.feign;

import com.springcloud.demo.order.feign.fallback.ProductFeignClientFallback;
import com.springcloud.demo.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @FeignClient是Spring Cloud提供的一个声明式HTTP客户端注解，主要用于简化微服务之间的远程调用。
 * 通过简单的接口定义和注解配置，即可实现服务间的HTTP调用，开发者无需手动编写HTTP请求代码。
 * 多种参数配置‌：
 * value/name：指定要调用的服务名称
 * url：直接指定服务URL，绕过服务发现，使用该方式调用没有在Nacos上注册的第三方服务
 * fallback：定义调用失败时的降级处理类
 * configuration：自定义Feign客户端配置
 */
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
    /**
     * 调用"/product/{id}"接口发送请求获取结果
     * @GetMapping、@PostMapping、@PutMapping等是Spring提供的处理HTTP请求的注解，
     * 1.当它们与@RestController注解一起使用时，表示接受其他服务发送过来的请求。
     * 2.当它们与@FeignClient注解一起使用时，表示给其他服务发送请求。
     *
     * @param id 商品id
     * @return 商品信息
     */
    @GetMapping("/product/query/{id}")
    Product getProductById(@PathVariable(value = "id") Long id);
}
