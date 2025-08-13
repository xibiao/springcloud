package com.springcloud.demo.seata.order.service;

import com.springcloud.demo.seata.order.bean.OrderTbl;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param userId        用户id
     * @param commodityCode 商品编码
     * @param count    商品数量
     */
    OrderTbl create(String userId, String commodityCode, int count);
}
