package com.springcloud.demo.product.controller;

import com.springcloud.demo.product.bean.Product;
import com.springcloud.demo.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/query/{id}")
    public Product getProductById(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        System.out.println("Hello Product. X-Token:" + token);
        return productService.getProductById(id);
    }
}
