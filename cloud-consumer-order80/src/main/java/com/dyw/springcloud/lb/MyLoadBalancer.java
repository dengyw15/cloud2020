package com.dyw.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component  //注意 需要注入
public class MyLoadBalancer implements IMyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement() {
        int curr, next;
        do {
            curr = atomicInteger.get();
            next = curr > Integer.MAX_VALUE ? 0 : curr + 1;
        } while (!atomicInteger.compareAndSet(curr, next));
        System.out.println("更新next=" +  next);
        return next;
//        return atomicInteger.getAndIncrement();  //也可以直接取
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {
        int serviceIndex = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(serviceIndex);
    }
}
