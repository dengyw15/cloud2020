package com.dyw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerZKOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerZKOrder80.class, args);
    }
}
