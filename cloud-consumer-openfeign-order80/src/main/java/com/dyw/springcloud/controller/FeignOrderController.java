package com.dyw.springcloud.controller;

import com.dyw.springcloud.entities.CommonResult;
import com.dyw.springcloud.entities.Payment;
import com.dyw.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignOrderController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentTimeOut() {
        return paymentService.paymentTimeOut();
    }
}
