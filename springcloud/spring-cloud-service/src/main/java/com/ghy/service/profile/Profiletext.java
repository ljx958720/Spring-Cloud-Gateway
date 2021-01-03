package com.ghy.service.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Profiletext {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfiguration.class);
        context.refresh();
        System.out.println(context.getBean(ProfileDemo.class));
    }
}
