package com.springcloud.demo.product.service.impl;

import com.springcloud.demo.product.bean.Product;
import com.springcloud.demo.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        // 模拟从数据库查询数据
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("100"));
        product.setProductName("三国演义");
        product.setNum(2);
        return product;
    }
}
