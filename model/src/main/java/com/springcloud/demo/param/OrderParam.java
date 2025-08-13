package com.springcloud.demo.param;

import lombok.Data;

@Data
public class OrderParam {
    // 用户id
    private String userId;
    // 商品编码
    private String commodityCode;
    // 商品数量
    private int count;
}
