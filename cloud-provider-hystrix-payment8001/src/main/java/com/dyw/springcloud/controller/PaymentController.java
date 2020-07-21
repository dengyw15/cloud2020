package com.dyw.springcloud.controller;

import com.dyw.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String strPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id) {
        return paymentService.payment_ok(id);
    }

    @GetMapping("/payment/hystrix/timout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id) {
        return paymentService.payment_timeout(id);
    }
}
