package com.dyw.springcloud.service.impl;

import com.dyw.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String payment_ok(Integer id) {
        return "OK Thread: " + Thread.currentThread().getName() + " It's OK, id=" + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "payment_timoutHandler",
            commandProperties =
                    {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String payment_timeout(Integer id) {

//        int age = 10 /0; //运行异常也会走到兜底的timeouthandler方法
        int time = 1;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Timeout Thread: " + Thread.currentThread().getName() + " timeout, id=" + id;
    }

    public String payment_timoutHandler(Integer id) {
        return "TimeoutHandler Thread: " + Thread.currentThread().getName() + " 系统繁忙，请稍后再试！  id=" + id;
    }
}
