package com.dyw.springcloud.controller;

import com.dyw.springcloud.service.OrderService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id) {
        return orderService.payment_ok(id);
    }

    @GetMapping("/consumer/payment/hystrix/timout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id) {
        return orderService.payment_timeout(id);
    }
}
