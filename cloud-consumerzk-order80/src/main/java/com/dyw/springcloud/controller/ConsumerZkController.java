package com.dyw.springcloud.controller;

import com.dyw.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerZkController {

    public static final String URL_PREFIX = "http://cloud-payment-service";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumerZk/discovery")
    public String discovery() {
        return restTemplate.getForObject(URL_PREFIX + "/payment8004/zk/discovery", String.class);
    }
}
