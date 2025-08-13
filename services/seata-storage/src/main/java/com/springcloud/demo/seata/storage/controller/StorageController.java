package com.springcloud.demo.seata.storage.controller;

import com.springcloud.demo.param.StorageParam;
import com.springcloud.demo.seata.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/deduct")
    public String deduct(@RequestBody StorageParam storageParam) {
        storageService.deduct(storageParam.getCommodityCode(), storageParam.getCount());
        return "storage deduct success";
    }
}
