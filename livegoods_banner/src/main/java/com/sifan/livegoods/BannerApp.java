package com.sifan.livegoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BannerApp {
    public static void main(String[] args) {
        SpringApplication.run(BannerApp.class);
    }
}
