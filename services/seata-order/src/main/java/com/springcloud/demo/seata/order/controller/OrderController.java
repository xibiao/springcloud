package com.springcloud.demo.seata.order.controller;

import com.springcloud.demo.param.OrderParam;
import com.springcloud.demo.seata.order.bean.OrderTbl;
import com.springcloud.demo.seata.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public String create(@RequestBody OrderParam orderParam) {
        OrderTbl tbl = orderService.create(orderParam.getUserId(), orderParam.getCommodityCode(), orderParam.getCount());
        return "order create success = 订单id：[" + tbl.getId() + "]";
    }
}
