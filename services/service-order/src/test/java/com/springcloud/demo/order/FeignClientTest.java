package com.springcloud.demo.order;

import com.springcloud.demo.order.feign.ThirdServiceFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeignClientTest {
    @Autowired
    private ThirdServiceFeignClient thirdServiceFeignClient;
    @Test
    public void testThirdServiceFeignClient(){
        String data = thirdServiceFeignClient.getBaiduFirstPage();
        System.out.println("data====" + data);
    }
}
