package com.ghy.service.serviceFeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserService {
    @GetMapping("/user")
    String user();
    @GetMapping("/query")
    public String query();
    @PostMapping("/insert")
    public String insert();
    @GetMapping("/user1")
    String user1();
}
