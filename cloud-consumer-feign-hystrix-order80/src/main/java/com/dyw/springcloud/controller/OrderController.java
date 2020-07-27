package com.dyw.springcloud.controller;

import com.dyw.springcloud.service.OrderService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "payment_timoutHandler",
            commandProperties =
                    {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
                    })
    public String payment_timeout(@PathVariable("id") Integer id) {
        return orderService.payment_timeout(id);
    }

    public String payment_timoutHandler(Integer id) {
        return "TimeoutHandler Thread: " + Thread.currentThread().getName() + " 系统繁忙，请稍后再试！  id=" + id;
    }
}
