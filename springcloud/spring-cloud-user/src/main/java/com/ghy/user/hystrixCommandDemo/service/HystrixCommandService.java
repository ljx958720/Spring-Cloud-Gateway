package com.ghy.user.hystrixCommandDemo.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;


public class HystrixCommandService extends HystrixCommand<String>{
    int sum;
    RestTemplate restTemplate;
    public HystrixCommandService(int sum, RestTemplate restTemplate){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("spring-cloud-service")).
                andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withCircuitBreakerEnabled(true).//熔断是否开启true表示开启
                        withCircuitBreakerRequestVolumeThreshold(5))); //表示最小的请求次数
        this.sum=sum;
        this.restTemplate=restTemplate;
    }
    @Override
    protected String run() throws Exception {
        if(sum%2==0){
            return "没有异常";
        }
        //发起远程请求
        return restTemplate.getForObject("http://localhost:8080/user",String.class);
    }

    //重写getFallback方法，如果Hystrix触发了降级，那么将会执行fallback方法
    @Override
    protected String getFallback() {
        return "请求被降级";
    }
}
