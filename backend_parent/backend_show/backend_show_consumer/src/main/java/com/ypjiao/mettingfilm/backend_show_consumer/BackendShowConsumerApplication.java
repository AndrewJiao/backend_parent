package com.ypjiao.mettingfilm.backend_show_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class BackendShowConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendShowConsumerApplication.class, args);
    }

}
