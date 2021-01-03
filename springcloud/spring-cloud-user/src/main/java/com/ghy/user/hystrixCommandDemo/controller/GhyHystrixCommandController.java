package com.ghy.user.hystrixCommandDemo.controller;

import com.ghy.service.serviceFeign.FeignClient.UserFeignClient;
import com.ghy.user.hystrixCommandDemo.comment.GhyHystrix;
import com.ghy.user.hystrixCommandDemo.service.HystrixCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class GhyHystrixCommandController {
    @Resource
    UserFeignClient userOpenFeign;

       @GhyHystrix(fallback = "fallback",timeout = 3000)
        @GetMapping("/user1")
        public String hystrixCommand(){

           return userOpenFeign.user1();
        }
    public String fallback(){
        return "别想了，过不去了";
    }
    }
