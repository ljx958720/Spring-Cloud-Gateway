package com.ghy.user.hystrixCommandDemo.controller;

import com.ghy.user.hystrixCommandDemo.service.HystrixCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixCommandController {
      @Bean
       public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }
        @Autowired
        RestTemplate restTemplate;
        @GetMapping("/hystrix/{sum}")
        public String hystrixCommand(@PathVariable("sum")int sum){
            HystrixCommandService hystrixCommandService=new HystrixCommandService(sum,restTemplate);
            return  hystrixCommandService.execute();//执行
        }
    }
