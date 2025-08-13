package com.springcloud.demo.seata.storage.mapper;

import com.springcloud.demo.seata.storage.bean.StorageTbl;

public interface StorageTblMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StorageTbl record);

    int insertSelective(StorageTbl record);

    StorageTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StorageTbl record);

    int updateByPrimaryKey(StorageTbl record);

    void deduct(String commodityCode, int count);
}
