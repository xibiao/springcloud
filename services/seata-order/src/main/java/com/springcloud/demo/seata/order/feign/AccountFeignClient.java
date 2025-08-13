package com.springcloud.demo.seata.order.feign;

import com.springcloud.demo.param.AccountParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "seata-account")
public interface AccountFeignClient {
    /**
     * 扣减账户余额
     */
    @PostMapping("/account/debit")
    String debit(@RequestBody AccountParam accountParam);
}
