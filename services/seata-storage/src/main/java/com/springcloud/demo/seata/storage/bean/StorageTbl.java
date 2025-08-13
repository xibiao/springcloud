package com.springcloud.demo.seata.storage.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class StorageTbl implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String commodityCode;

    private Integer count;
}
