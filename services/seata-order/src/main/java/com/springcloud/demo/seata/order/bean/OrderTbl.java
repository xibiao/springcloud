package com.springcloud.demo.seata.order.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class OrderTbl implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;
}
