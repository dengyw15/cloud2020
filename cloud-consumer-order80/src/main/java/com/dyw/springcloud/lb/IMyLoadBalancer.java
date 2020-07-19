package com.dyw.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IMyLoadBalancer {

    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList);
}
