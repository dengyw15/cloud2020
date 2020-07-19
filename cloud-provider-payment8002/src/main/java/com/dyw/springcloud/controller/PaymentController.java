package com.dyw.springcloud.controller;

import com.dyw.springcloud.entities.CommonResult;
import com.dyw.springcloud.entities.Payment;
import com.dyw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String strPort;

    @Autowired
    EurekaDiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功, port=" + strPort, result);
        } else {
            return new CommonResult(444, "插入数据失败, port=" + strPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果" + result);

        if (Objects.nonNull(result)) {
            return new CommonResult(200, "查询成功, port=" + strPort, result);
        } else {
            return new CommonResult(444, "查询失败, port=" + strPort);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.stream().forEach(service -> {
            System.out.println(service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            instances.stream().forEach(serviceInstance -> {
                System.out.println("[" + service + "] serviceid= " + serviceInstance.getServiceId());
                System.out.println("[" + service + "] host= " + serviceInstance.getHost());
                System.out.println("[" + service + "] instanceId= " + serviceInstance.getInstanceId());
                System.out.println("[" + service + "] schema= " + serviceInstance.getScheme());
                System.out.println("[" + service + "] port= " + serviceInstance.getPort());
                System.out.println("[" + service + "] uri= " + serviceInstance.getUri());
            });
        });

        return discoveryClient;
    }

    //用于测试自定义ribbon轮询算法
    @GetMapping("/payment/myLb")
    public String getPortByMyLb() {
        return strPort;
    }

    //用于测试自定义OpenFeign超时时间
    @GetMapping("/payment/feign/timeout")
    public String paymentTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return strPort;
    }
}
