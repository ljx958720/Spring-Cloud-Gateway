package com.ghy.user;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SmartLifecycleDemo {
    public void start() {
        System.out.println("start");
    }

    public void stop() {
        System.out.println("stop");
    }

    public boolean isRunning() {
        System.out.println("isRunning");
        return false;
    }
}
