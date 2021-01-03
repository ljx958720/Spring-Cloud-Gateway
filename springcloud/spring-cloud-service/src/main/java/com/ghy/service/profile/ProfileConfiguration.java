package com.ghy.service.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class ProfileConfiguration {

    @Profile("dev")
    @Bean
    public ProfileDemo profileServiceDev(){
        return new ProfileDemo("dev");
    }

    @Profile("tet")
    @Bean
    public ProfileDemo profileServicePrd(){
        return new ProfileDemo("tet");
    }
}
