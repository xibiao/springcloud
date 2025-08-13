package com.springcloud.demo.seata.order.service.impl;

import com.springcloud.demo.param.AccountParam;
import com.springcloud.demo.seata.order.bean.OrderTbl;
import com.springcloud.demo.seata.order.feign.AccountFeignClient;
import com.springcloud.demo.seata.order.mapper.OrderTblMapper;
import com.springcloud.demo.seata.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderTblMapper orderTblMapper;

    @Autowired
    AccountFeignClient accountFeignClient;

    @Override
    @Transactional
    public OrderTbl create(String userId, String commodityCode, int count) {
        // 1. 计算订单价格
        int orderMoney = 10 * count;
        // 2. 扣减账户余额
        AccountParam accountParam = new AccountParam();
        accountParam.setUserId(userId);
        accountParam.setMoney(orderMoney);
        accountFeignClient.debit(accountParam);
        // 3. 保存订单
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setUserId(userId);
        orderTbl.setCommodityCode(commodityCode);
        orderTbl.setCount(count);
        orderTbl.setMoney(orderMoney);
        orderTblMapper.insert(orderTbl);
        // 模拟异常
//        int i = 10 / 0;
        return orderTbl;
    }
}
