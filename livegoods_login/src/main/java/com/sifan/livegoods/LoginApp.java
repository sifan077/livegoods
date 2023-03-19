package com.sifan.livegoods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LoginApp {
    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class);
    }
}
