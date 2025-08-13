package com.springcloud.demo.order.service;

import com.springcloud.demo.order.bean.Order;

public interface OrderService {
    Order createOrder(Long userId, Long productId);

    String getConfig();
}
