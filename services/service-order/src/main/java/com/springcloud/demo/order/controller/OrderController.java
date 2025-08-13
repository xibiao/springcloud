package com.springcloud.demo.order.controller;
import java.math.BigDecimal;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springcloud.demo.common.Result;
import com.springcloud.demo.order.bean.Order;
import com.springcloud.demo.order.pojo.OrderParam;
import com.springcloud.demo.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * POST请求接收JSON数据时使用简单类型（Integer、String等）不能自动填充数据
     * 必须要封装成实体类或者使用Map接收，而且使用Map接收时还要注明泛型
     *
     * @param map 请求参数
     * @return 订单信息
     */
    @PostMapping("/create")
    public Result createOrder(@RequestBody Map<String, Object> map,  HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        System.out.println("Hello Order. X-Token:" + token);
        Long userId = Long.parseLong(String.valueOf(map.get("userId")));
        Long productId = Long.parseLong(String.valueOf(map.get("productId")));
        Order order = orderService.createOrder(userId, productId);
        return Result.ok(order);
    }

    /**
     * 秒杀场景创建订单
     *
     * @param orderParam 请求参数
     * @return 订单信息
     */
    @PostMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Result seckill(@RequestBody OrderParam orderParam) {
        Order order = orderService.createOrder(orderParam.getUserId(), orderParam.getProductId());
        order.setId(Long.MAX_VALUE);
        return Result.ok(order);
    }

    public Result seckillFallback(OrderParam orderParam, Throwable exception) {
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(orderParam.getUserId());
        order.setNickName("异常信息：" + exception.getClass());
        return Result.ok(order);
    }

    @GetMapping("/config")
    public Result getConfig() {
        return Result.ok(orderService.getConfig());
    }
}
