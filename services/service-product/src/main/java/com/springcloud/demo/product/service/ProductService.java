package com.springcloud.demo.product.service;

import com.springcloud.demo.product.bean.Product;

public interface ProductService {
    Product getProductById(Long id);
}
