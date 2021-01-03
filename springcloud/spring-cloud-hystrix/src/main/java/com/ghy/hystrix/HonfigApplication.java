package com.ghy.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@EnableHystrixDashboard //hystrix监控面板注解
@SpringBootApplication
public class HonfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HonfigApplication.class, args);
    }

}
