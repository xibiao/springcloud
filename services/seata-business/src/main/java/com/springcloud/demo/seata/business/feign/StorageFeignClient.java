package com.springcloud.demo.seata.business.feign;

import com.springcloud.demo.param.StorageParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "seata-storage")
public interface StorageFeignClient {
    /**
     * 扣减库存
     */
    @PostMapping("/storage/deduct")
    String deduct(@RequestBody StorageParam storageParam);
}
