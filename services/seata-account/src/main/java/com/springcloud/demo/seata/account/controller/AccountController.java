package com.springcloud.demo.seata.account.controller;

import com.springcloud.demo.param.AccountParam;
import com.springcloud.demo.seata.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @PostMapping("/debit")
    public String debit(@RequestBody AccountParam accountParam) {
        accountService.debit(accountParam.getUserId(), accountParam.getMoney());
        return "account debit success";
    }
}
