package com.sifan.livegoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommentApp {
    public static void main(String[] args) {
        SpringApplication.run(CommentApp.class, args);
    }
}
