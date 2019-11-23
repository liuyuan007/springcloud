package com.wkdtech.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CloudAuthApplication {

    public static void main(String[] args){
        SpringApplication.run(CloudAuthApplication.class,args);
    }
}
