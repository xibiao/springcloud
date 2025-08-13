package com.springcloud.demo.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FeignClient也可以调用没有在Nacos注册的第三方服务，
 * 由于第三方服务没有在Nacos上注册，所以没有服务名，value/name属性值随便写，
 * URL必须填写被调用的第三方服务IP(或域名)+port，
 * 方法上的@GetMapping、@PostMapping等注解填写被调用的第三方服务地址，
 * 如果被调用服务接口还有参数，需要在方法中添加参数。
 * 例如获取百度首页：Get https://www.baidu.com或者https://www.baidu.com/index.php
 */
@FeignClient(value = "third-service", url = "https://www.baidu.com")
public interface ThirdServiceFeignClient {
    @GetMapping("/index.php")
    String getBaiduFirstPage();
}
