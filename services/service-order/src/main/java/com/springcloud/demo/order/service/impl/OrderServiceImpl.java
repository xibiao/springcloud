package com.springcloud.demo.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.demo.order.bean.Order;
import com.springcloud.demo.order.config.OrderProperties;
import com.springcloud.demo.order.feign.ProductFeignClient;
import com.springcloud.demo.order.service.OrderService;
import com.springcloud.demo.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private OrderProperties orderProperties;

    /**
     * 从Nacos配置中心获取配置信息
     *
     * @return 配置信息
     */
    @Override
    public String getConfig() {
        String s = "order.timeout=" + orderProperties.getTimeout()
                + ", order.auto-confirm=" + orderProperties.getAutoConfirm()
                + ", order.db-url=" + orderProperties.getDbUrl();
        log.info("The nacos config is: {}.", s);
        return s;
    }

    /**
     * 使用@SentinelResource注解定义一个sentinel资源，用于标记需要受保护的资源。
     * value：资源名称，在控制台配置规则的依据，该属性是必须的。
     * entryType：流量方向：IN(入口)/OUT(出口)。
     * blockHandler：处理BlockException的方法名。
     * fallback：业务异常时的降级方法名。
     * defaultFallback：默认降级方法名（无参或单Throwable参数）。
     * exceptionsToIgnore：忽略的异常类型（不触发fallback）。
     * 被@SentinelResource注解标记为资源的业务方法，如果违反资源的规则，会抛出BlockException异常，
     * 则按顺序依次使用blockHandler、fallback或defaultFallback指定的方法进行兜底回调，
     * 若这三个属性都没有指定兜底回调方法，则抛出BlockException异常信息。
     *
     * @param userId 用户id
     * @param productId 商品id
     * @return 订单信息
     */
    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long userId, Long productId) {
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("上海");
        order.setProductList(Collections.singletonList(product));
        return order;
    }

    public Order createOrderFallback(Long userId, Long productId, BlockException e) {
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(userId);
        order.setNickName(e.toString());
        return order;
    }
}
