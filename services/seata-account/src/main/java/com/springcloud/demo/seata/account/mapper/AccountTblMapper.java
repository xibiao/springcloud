package com.springcloud.demo.seata.account.mapper;

import com.springcloud.demo.seata.account.bean.AccountTbl;

public interface AccountTblMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountTbl record);

    int insertSelective(AccountTbl record);

    AccountTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountTbl record);

    int updateByPrimaryKey(AccountTbl record);

    void debit(String userId, int money);
}
