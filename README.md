

# 说明

payment8001/8002 是注册在Eureka注册中心

server7001 关闭了服务自我保护    payment8001 设置心跳发送数据

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

