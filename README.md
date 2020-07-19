

# 说明

* payment8001/8002 是注册在Eureka注册中心

server7001 关闭了服务自我保护    payment8001 设置心跳发送数据

* ribbon是在客户端的负载均衡，因此在本工程中是需要配置在consumer-Order80模块中

* 开始使用Ribbon+RestTemplate实现服务调用和负载均衡， 后可以使用OpenFeign直接实现，feign是用在消费端（client端）
* Feign客户端调用服务默认的超时时间是1s，超时会返回报错，对于长交易 需要手动设置Feign的超时时间

# maven依赖

## Eureka Clinent 导入的依赖

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

最开始错误地写成了,导致eureka client导入的jar包不全，服务一直无法注册

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-netflix-eureka-client</artifactId>
</dependency>
```

