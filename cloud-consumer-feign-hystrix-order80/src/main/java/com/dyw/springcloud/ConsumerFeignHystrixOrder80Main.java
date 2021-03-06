package com.dyw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignHystrixOrder80Main {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignHystrixOrder80Main.class, args);
    }
}
