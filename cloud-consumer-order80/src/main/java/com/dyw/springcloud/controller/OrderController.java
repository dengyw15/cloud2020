package com.dyw.springcloud.controller;

import cn.hutool.core.util.ReUtil;
import com.dyw.springcloud.entities.CommonResult;
import com.dyw.springcloud.entities.Payment;
import com.dyw.springcloud.lb.IMyLoadBalancer;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";  //单机
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";  //在Eureka中注册的服务名

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IMyLoadBalancer myLoadBalancer;

    @Autowired
    EurekaDiscoveryClient discoveryClient;

    @PostMapping("/consumer/create")
    public CommonResult create(Payment payment) {
        log.info("插入数据" + payment);
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/myLb")
    public String getPortByMyLb () {
        List<ServiceInstance> services = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = myLoadBalancer.getServiceInstance(services);
        return restTemplate.getForObject(instance.getUri() + "/payment/myLb", String.class);
    }
}
