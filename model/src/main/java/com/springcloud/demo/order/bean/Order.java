package com.springcloud.demo.order.bean;

import com.springcloud.demo.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;

    private BigDecimal totalAmount;

    private Long userId;

    private String nickName;

    private String address;

    private List<Product> productList;
}
