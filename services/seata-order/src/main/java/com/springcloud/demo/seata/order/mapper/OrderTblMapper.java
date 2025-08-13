package com.springcloud.demo.seata.order.mapper;

import com.springcloud.demo.seata.order.bean.OrderTbl;

public interface OrderTblMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderTbl record);

    int insertSelective(OrderTbl record);

    OrderTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderTbl record);

    int updateByPrimaryKey(OrderTbl record);
}
