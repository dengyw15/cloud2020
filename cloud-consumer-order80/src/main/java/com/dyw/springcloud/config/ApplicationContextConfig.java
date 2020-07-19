package com.dyw.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced  //用于测试自定义ribbon轮询算法MyLoadBalancer，暂时注释
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
