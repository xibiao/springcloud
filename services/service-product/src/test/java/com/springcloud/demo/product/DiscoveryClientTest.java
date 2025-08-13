package com.springcloud.demo.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryClientTest {
    // Spring内置的服务发现组件，对于任意服务注册与发现组件（如Eureka和Nacos）都可以使用
    @Autowired
    private DiscoveryClient discoveryClient;

    // Nacos自己的服务发现组件，只能用于Nacos的服务注册与发现
    @Autowired
    private NacosServiceDiscovery  nacosServiceDiscovery;

    @Test
    public void discoveryClientTest(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("service=====" + service);
            // 获取ip:port
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip:port===" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }

    @Test
    public void nacosServiceDiscoveryTest() throws NacosException {
        List<String> services = nacosServiceDiscovery.getServices();
        for (String service : services) {
            System.out.println("service=====" + service);
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip:port===" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }
}
