package com.ghy.user;

import com.ghy.service.serviceFeign.FeignClient.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController  {
    @Resource
    UserFeignClient userOpenFeign;

//    @HystrixCommand(commandProperties ={
//            @HystrixProperty(name="circuitBreaker.enabled",value ="true"),//熔断是否开启true表示开启
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "5"),//表示最小的请求次数
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "5000"),//时间窗口，这里设置5S
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50")//这是比例，这里设置50，表示异常率达到了触发
//    },fallbackMethod = "fallback")
    @GetMapping("/user")
    public String UserName(){

        return userOpenFeign.user();
    }

    //请求超时触发降级
    @HystrixCommand(fallbackMethod ="timeoutFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
    })
    @GetMapping("/hystrix")
    public String queryOrderTimeout(){
        return userOpenFeign.user();
    }

    @GetMapping("/query")
    public String query(){
        return userOpenFeign.query();
    }

    @PostMapping("/insert")
    public String insert(){
        return userOpenFeign.insert();
    }

    public String fallback(){
        return "系统正忙";
    }

    public String timeoutFallback(){
        return "请求超时";
    }
}
