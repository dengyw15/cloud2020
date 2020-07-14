package com.dyw.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Ribbon 自定义的负载均衡规则不能放在@ComponentScan所扫描的当前包和子包下

@SpringBootApplication 注解下包含@ComponentScan，因此自定义负载规则不能放在@SpringBootApplication的包下
 */
@Configuration
public class MyRule {

    @Bean
    public IRule myCusRule() {
        return new RandomRule();
    }
}
