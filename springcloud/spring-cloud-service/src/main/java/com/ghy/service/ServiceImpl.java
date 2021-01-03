package com.ghy.service;

import com.ghy.service.serviceFeign.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceImpl implements UserService {

    @Value("${server.port}")
    private int port;

    public String user() {
        System.out.println(port);
        return "测试通过";
    }

    public String query() {
        return null;
    }

    public String insert() {
        return null;
    }

    public String user1() {
        return null;
    }
}
