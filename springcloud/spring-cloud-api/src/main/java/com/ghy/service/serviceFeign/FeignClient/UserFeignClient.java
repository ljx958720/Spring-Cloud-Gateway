package com.ghy.service.serviceFeign.FeignClient;

import com.ghy.service.serviceFeign.UserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
//降级方案
@FeignClient(value = "spring-cloud-service",fallback = UserFeignClient.UserFeignClientFallback.class)

public interface UserFeignClient extends UserService {
    @Component
    class UserFeignClientFallback implements UserFeignClient {

        @Override
        public String user() {
            return "请稍候重试";
        }

        @Override
        public String query() {
            return null;
        }

        @Override
        public String insert() {
            return null;
        }

        @Override
        public String user1() {
            return null;
        }
    }
}
