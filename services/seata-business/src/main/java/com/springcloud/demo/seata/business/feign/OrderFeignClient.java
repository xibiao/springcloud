package com.springcloud.demo.seata.business.feign;

import com.springcloud.demo.param.OrderParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "seata-order")
public interface OrderFeignClient {
    /**
     * 创建订单
     */
    @PostMapping("/order/create")
    String create(@RequestBody OrderParam orderParam);
}
