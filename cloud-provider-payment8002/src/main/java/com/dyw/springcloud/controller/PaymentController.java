package com.dyw.springcloud.controller;

import com.dyw.springcloud.entities.CommonResult;
import com.dyw.springcloud.entities.Payment;
import com.dyw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String strPort;

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
}
