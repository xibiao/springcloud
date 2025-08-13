package com.springcloud.demo.order.pojo;

import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowArgument;
import lombok.Data;

@Data
public class OrderParam implements ParamFlowArgument {
    private Long userId;

    private Long productId;

    @Override
    public Object paramFlowKey() {
        // 使用 userId 作为热点键
        return userId;
    }
}
