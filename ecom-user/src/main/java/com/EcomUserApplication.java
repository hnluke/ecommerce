package com;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient      // 开启服务发现功能
@EnableAsync
@EnableRabbit
public class EcomUserApplication {
    // 可以通过RestTemplate用http的方式访问远程模块的Controller
    @LoadBalanced       // 使用负载均衡机制
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EcomUserApplication.class, args);
    }

}
