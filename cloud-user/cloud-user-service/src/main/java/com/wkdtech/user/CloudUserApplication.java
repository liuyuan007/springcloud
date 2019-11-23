package com.wkdtech.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author bystander
 * @date 2018/9/29
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.wkdtech.user.mapper")
public class CloudUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudUserApplication.class);
    }
}
