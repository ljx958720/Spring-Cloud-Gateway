package com.ghy.service.profile;

public class ProfileDemo {
    private String profile;

    public ProfileDemo(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "profile='" + profile + '\'' +
                '}';
    }
}
