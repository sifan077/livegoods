package com.sifan.livegoods;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableCaching
public class DetailsApp {
    public static void main(String[] args) {
        SpringApplication.run(DetailsApp.class, args);
    }
}
