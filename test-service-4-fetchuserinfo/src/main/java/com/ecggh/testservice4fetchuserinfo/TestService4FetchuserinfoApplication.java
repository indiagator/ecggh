package com.ecggh.testservice4fetchuserinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestService4FetchuserinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestService4FetchuserinfoApplication.class, args);
    }

}
