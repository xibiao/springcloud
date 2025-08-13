package com.springcloud.demo.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void loadBalancerTest() {
//        List<ServiceInstance> services = discoveryClient.getInstances("service-product");
        for (int i = 0; i < 10; i++) {
            ServiceInstance instance = loadBalancerClient.choose("service-product");
            System.out.println("ip:port=" + instance.getHost() + ":" + instance.getPort());
        }
    }
}
