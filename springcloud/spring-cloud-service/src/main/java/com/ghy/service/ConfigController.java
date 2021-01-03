package com.ghy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope//当springconfig配置中心配置刷新的时候，会自动刷新当前类的注入的字段
@RestController
public class ConfigController {


    @Value("${name}")
    private String tex;

    @GetMapping("/name")
    public String config(){
        return tex;
    }

}
