package com.dyw.springcloud.service.impl;

import com.dyw.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String payment_ok(Integer id) {
        return "OK Thread: " + Thread.currentThread().getName() + " It's OK, id=" + id;
    }

    @Override
    public String payment_timeout(Integer id) {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Timeout Thread: " + Thread.currentThread().getName() + " timeout, id=" + id;
    }
}
