package com.dyw.springcloud.service;

public interface PaymentService {

    String payment_ok(Integer id);

    String payment_timeout(Integer id);
}
