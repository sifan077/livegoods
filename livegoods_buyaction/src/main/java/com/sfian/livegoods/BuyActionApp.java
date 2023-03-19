package com.sfian.livegoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BuyActionApp {
    public static void main(String[] args) {
        SpringApplication.run(BuyActionApp.class, args);
    }
}
