package com.dyw.springcloud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.zookeeper.discovery.ZookeeperDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment8004")
public class Payment8004Controller {

    @Value("${service.port}")
    String strPort;

    @Autowired
    ZookeeperDiscoveryClient discoveryClient;

    @GetMapping("/zk/discovery")
    public String zkDiscovery() {
        return discoveryClient.description() + "===" + strPort + "===" + UUID.randomUUID();
    }
}
