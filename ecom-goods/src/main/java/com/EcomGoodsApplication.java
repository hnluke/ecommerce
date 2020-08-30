package com;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRabbit
@EnableCaching
public class EcomGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomGoodsApplication.class, args);
    }

}
