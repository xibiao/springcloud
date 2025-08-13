package com.springcloud.demo.seata.business.service.impl;

import com.springcloud.demo.param.OrderParam;
import com.springcloud.demo.param.StorageParam;
import com.springcloud.demo.seata.business.feign.OrderFeignClient;
import com.springcloud.demo.seata.business.feign.StorageFeignClient;
import com.springcloud.demo.seata.business.service.BusinessService;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private StorageFeignClient storageFeignClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Override
    @GlobalTransactional  // seata分布式全局事务注解
    public void purchase(String userId, String commodityCode, int count) {
        // 1. 扣减库存
        StorageParam storageParam = new StorageParam();
        storageParam.setCommodityCode(commodityCode);
        storageParam.setCount(count);
        storageFeignClient.deduct(storageParam);
        // 2. 创建订单
        OrderParam orderParam = new OrderParam();
        orderParam.setUserId(userId);
        orderParam.setCommodityCode(commodityCode);
        orderParam.setCount(count);
        orderFeignClient.create(orderParam);
    }
}
