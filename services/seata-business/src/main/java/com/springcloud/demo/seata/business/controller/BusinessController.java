package com.springcloud.demo.seata.business.controller;

import com.springcloud.demo.param.BusinessParam;
import com.springcloud.demo.seata.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 购买
     */
    @PostMapping("/purchase")
    public String purchase(@RequestBody BusinessParam businessParam) {
        businessService.purchase(businessParam.getUserId(), businessParam.getCommodityCode(), businessParam.getCount());
        return "business purchase success";
    }
}
